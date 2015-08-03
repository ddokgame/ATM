package Server.Surface;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import Database.DBUtil;

/**
 * ����
 * @author overlord
 *
 */
public class SaveMoney {
	/*
	 * ��¼��Ϣ
	 * ������ͣ��������
	 * �����
	 */
	private String[] info;
	private byte type,situation=0;
	private String money;
	
	/**
	 * ���췽��
	 * @param info	��¼��Ϣ
	 * @param money	�����
	 * @param knowType	�������
	 */
	public SaveMoney(String[] info,String money,byte knowType){
		this.info=info;
		type=knowType;
		this.money=money;
	}

	public String save(){
		if(!judgeLegal()){
			situation=1;
			return "�����ֻ����100����������";
		}
		BigDecimal money=new BigDecimal(this.money);
		BigDecimal balance=new BigDecimal(info[typeIndex()]).add(money);
		String sql="update account set "+typeSQL()+"="+balance+" where account_Id="+info[0]+";";
		DBUtil db=new DBUtil();
		int upLine=db.intExecute(sql);
		db.close();
		if(upLine>0){
			situation=2;
			return "�ɹ�����"+typeString()+"��� "+this.money+" Ԫ";
		}
		return "���ʧ�ܣ�";
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
