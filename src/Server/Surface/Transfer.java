package Server.Surface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Bank.LoginInfo;
import Database.DBUtil;

/**
 * ת�˹���
 * @author overlord
 *
 */
public class Transfer {
	/*
	 * ��ǰ�˻���Ϣ
	 * ����ת���˻�����1������2��ת�˽��
	 * ִ�����
	 */
	String[] info;
	String account1,account2,money;
	byte situation=0;
	/**
	 * ��һ�����Ŵ���Ĺ��췽��
	 * @param account1	ת����˻�����
	 */
	public Transfer(String account1){
		this.account1=account1;
	}
	/**
	 * ������ȡ����Ĺ��췽��
	 * @param info	��ǰ�˻���Ϣ
	 * @param account1	Ŀ�꿨��1
	 * @param account2	Ŀ�꿨��2
	 * @param money		ת�˽��
	 */
	public Transfer(String[] info, String account1, String account2,String money) {
		this.info = info;
		this.account1 = account1;
		this.account2 = account2;
		this.money = money;
	}
	/**
	 * ִ��ת�˲���������Ӧ�����ֵ����������ʾ
	 * @return	������ʾ����
	 */
	public String transfer(){
		if(!judgeAccountExist()){
			situation=1;
			return "������Ŀ����˻������ڣ����������룡";
		}
		if(!account2.equals(account1)){
			situation=2;
			return "�������ת��Ŀ�꿨�Ų�һ�£����������룡";
		}
		if(!judgeInputMoney()){
			situation=3;
			return "������Ľ���ʽ����ֻ����������λС�����ڵ����֣�";
		}
		BigDecimal money=new BigDecimal(this.money);
		BigDecimal balance=new BigDecimal(info[3]).subtract(money);
		if(balance.signum()==-1){
			situation=4;
			return "�˻����ڴ��㣬��������д��";
		}
		if(accountOut(balance)&&accountIn(money)){
			situation=5;
			return "ת�˳ɹ���";
		}
		return "ת��ʧ�ܣ�";
	}
	/**
	 * ��ת���˻�ִ�����ݿ���
	 * @param balance	ת��������
	 * @return	�Ƿ�ִ��
	 */
	private boolean accountOut(BigDecimal balance){
		boolean bol=false;
		DBUtil db=new DBUtil();
		String sql="update account set current_balance="+balance+" where "
				+ "account_Id="+info[0]+";";
		int upLine=db.intExecute(sql);
		db.close();
		if(upLine==1)
			bol=true;
		return bol;
	}
	/**
	 * ��ת���˻�ִ�����ݿ���
	 * @param money	ת��Ľ��
	 * @return	�Ƿ�ִ��
	 */
	private boolean accountIn(BigDecimal money){
		boolean bol=false;
		String[] info=new LoginInfo(account1).getInfo();
		BigDecimal balance=new BigDecimal(info[3]).add(money);
		DBUtil db=new DBUtil();
		String sql="update account set current_balance="+balance+" where "
				+ "account_Id="+info[0]+";";
		int upLine=db.intExecute(sql);
		db.close();
		if(upLine==1)
			bol=true;
		return bol;
	}
	
	/**
	 * ��ȡִ�����
	 * @return	ִ����������ִ���
	 */
	public byte getSituation(){
		return situation;
	}
	/**
	 * �жϽ�Ǯ�����Ƿ�Ϸ�
	 * @return	�Ƿ�Ϸ�
	 */
	public boolean judgeInputMoney(){
		String reg="^([1-9]+\\d*)?([1-9]+\\d*\\.\\d{1,2})?(0\\.\\d{1,2})?$";
		return Pattern.matches(reg, money);
	}
	/**
	 * �ж�������˻�����1�Ƿ����
	 * @return	�Ƿ����
	 */
	public boolean judgeAccountExist(){
		boolean bol=false;
		String sql="select * from account where account='"+account1+"';";
		DBUtil db=new DBUtil();
		try {
			if(db.rsExecute(sql).next())
				bol=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
		return bol;
	}
}
