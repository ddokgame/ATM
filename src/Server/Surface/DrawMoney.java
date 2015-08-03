package Server.Surface;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import Database.DBUtil;

/**
 * ȡ���
 * @author overlord
 *
 */
public class DrawMoney {
	/*
	 * ��¼��Ϣ
	 * ȡ�����ͣ��������
	 * ȡ����
	 */
	private String[] info;
	private byte type,situation=0;
	private String money;
	
	/**
	 * ���췽��
	 * @param info	��¼��Ϣ
	 * @param money	ȡ����
	 * @param knowType	ȡ������
	 */
	public DrawMoney(String[] info,String money,byte knowType){
		this.info=info;
		type=knowType;
		this.money=money;
	}
	/**
	 * ִ�в��������ڵ����Ի����з��ص���Ϣ��
	 * �ж�����Ϸ���
	 * �ж�����Ƿ����
	 * ִ��������䲢�ж��Ƿ�ɹ�
	 * ����δ֪������ʾ
	 * @return	������ʾ
	 */
	public String draw(){
		if(!judgeLegal()){
			situation=1;
			return "ȡ����ֻ����100����������";
		}
		BigDecimal money=new BigDecimal(this.money);
		BigDecimal balance=new BigDecimal(info[typeIndex()]).subtract(money);
		if(balance.signum()==-1){
			situation=3;
			return "�������㣬���������룡";
		}
		String sql="update account set "+typeSQL()+"="+balance+" where account_Id="+info[0]+";";
		DBUtil db=new DBUtil();
		int upLine=db.intExecute(sql);
		db.close();
		if(upLine>0){
			situation=2;
			return "�ɹ�ȡ��"+typeString()+"���"+this.money+"Ԫ";
		}
		return "ȡ��ʧ�ܣ�";
	}
	
	/**
	 * ��ȡ�������
	 * @return	�������
	 */
	public byte getSituation(){
		return situation;
	}
	
	/**
	 * �ж�������ĺϷ���
	 * @return	�жϽ��
	 */
	private boolean judgeLegal(){
		String reg="^[1-9][0-9]*0{2}$";
		return Pattern.matches(reg, money);
	}
	
	/**
	 * �����ڵ�¼��Ϣ�У�������͵��±�
	 * @return	�����±�
	 */
	private int typeIndex(){
		int index=3;
		if(type==1)
			index=4;
		return index;
	}
	/**
	 * ���������ݿ��У�������͵�����
	 * @return	��������
	 */
	private String typeSQL(){
		String type="current_balance";
		if(this.type==1)
			type="fixed_balance";
		return type;
	}
	/**
	 * ���ش�����͵�����
	 * @return	��������
	 */
	private String typeString(){
		String type="����";
		if(this.type==1)
			type="����";
		return type;
	}
}
