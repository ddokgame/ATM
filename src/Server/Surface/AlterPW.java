package Server.Surface;

import java.util.regex.Pattern;

import Database.DBUtil;

/**
 * �޸����빦��
 * @author overlord
 *
 */
public class AlterPW {
	/*
	 * ��¼��Ϣ
	 * ԭ���룬������1��������2
	 * ִ�����
	 */
	private String[] info;
	private String old,new1,new2;
	private byte situation=0;
	/**
	 * �޲ι��췽��
	 */
	public AlterPW(){
	}
	/**
	 * ���췽����ȡ���룬������Ϊ����
	 * @param info	��¼��Ϣ
	 * @param old	ԭ����
	 * @param new1	������1
	 * @param new2	������2
	 */
	public AlterPW(String[] info, char[] old, char[] new1, char[] new2) {
		this.info = info;
		this.old = new String(old);
		this.new1 = new String(new1);
		this.new2 = new String(new2);
	}

	/**
	 * ִ���޸������������ִ�������ֵ����������ʾ����
	 * @return	������ʾ����
	 */
	public String alter(){
		if(!old.equals(info[2])){
			situation=1;
			return "ԭ��������������������룡";
		}
		if(!judgeLegal(new1)){
			situation=2;
			return "ֻ����3��16λ�Ĵ��������룬���������룡";
		}
		if(!new1.equals(new2)){
			situation=3;
			return "����������������벻һ�£����������룡";
		}
		String sql="update account set password='"+new1+"' where account_Id="+info[0]+";";
		DBUtil db=new DBUtil();
		int upLine=db.intExecute(sql);
		db.close();
		if(upLine==1){
			situation=4;
			return "�����޸ĳɹ���";
		}
		return "�����޸�ʧ��";
	}


	/**
	 * �ж�����ĺϷ���
	 * @param pw	���������
	 * @return	�Ƿ�Ϸ�
	 */
	public boolean judgeLegal(String pw){
		String reg="^\\d{3,16}$";
		return Pattern.matches(reg, pw);
	}
	/**
	 * ��ȡ�������
	 * @return	�������
	 */
	public byte getSituation(){
		return situation;
	}
}
