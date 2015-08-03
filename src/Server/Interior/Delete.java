package Server.Interior;

import java.util.regex.Pattern;

import Bank.LoginInfo;
import Database.DBUtil;
import Server.Login;

public class Delete {
	//��һ�ο��ţ��ڶ��ο���
	String account1,account2;
	//ִ���������
	byte situation;

	/**
	 * ���췽�����뿨��
	 * @param account1	��һ�ο���
	 * @param account2	�ڶ��ο���
	 */
	public Delete(String account1, String account2) {
		this.account1 = account1;
		this.account2 = account2;
	}
	/**
	 * ִ��ɾ������ִ��������븳ֵ�����ص���������
	 * @return	����������
	 */
	public String delete(){
		if(!Pattern.matches("^\\d+$",account1)){
			situation=1;
			return "������Ŀ��Ų��Ϸ������������룡";
		}
		if(!account1.equals(account2)){
			situation=2;
			return "����������Ŀ��Ų�һ�£����������룡";
		}
		if(!new Login().judgeSuccess(account1)){
			situation=3;
			return "������Ŀ����޴��˻������������룡";
		}
		String[] info=new LoginInfo(account1).getInfo();
		DBUtil db=new DBUtil();
		String sql1="delete from account where account_Id="+info[0]+";";
		String sql2="delete from person_info where account_ID="+info[0]+";";
		int upLine=db.intExecute(sql1);
		upLine=upLine+db.intExecute(sql2);
		db.close();
		if(upLine==2){
			situation=4;
			return "ɾ������Ϊ"+account1+"���˻��ɹ���";
		}
		return "����ʧ�ܣ�";
	}
	/**
	 * ��ȡִ���������
	 * @return	ִ�����
	 */
	public byte getSituation(){
		return situation;
	}

}
