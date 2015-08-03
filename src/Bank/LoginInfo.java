package Bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBUtil;

/**
 * ���ݵ�¼���룬��ȡ�˻���Ϣ
 * @author overlord
 *
 */
public class LoginInfo {
	/*
	 * ��ѯ������͵��˻���Ϣ
	 * �����˻���Ϣ���ַ�������
	 */
	private ResultSet rs;
	private String[] s;
	
	/**
	 * �޲ι��췽��
	 */
	public LoginInfo(){
	}
	/**
	 * ���췽���������ݿⲢ�õ��˻�������Ϣ����Ϊ�ַ������飬֮��ر����ݿ�����
	 * @param account	����
	 */
	public LoginInfo(String account){
		DBUtil db=new DBUtil();
		String sql="select a.account_Id,account,password,current_balance,fixed_balance,name,sex from account as a inner join person_info as p"
				+ " on a.account_Id=p.account_ID where a.account="+account+";";
		rs=db.rsExecute(sql);
		s=new String[7];
		try {
			rs.next();
			for(int i=0;i<7;i++)
				s[i]=rs.getString(i+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		s=knowSex(s);
		db.close();
	}
	/**
	 * ��ȡ�˻���Ϣ
	 * @param account_ID	�˻����ڲ�ID
	 * @return info		�˻���Ϣ
	 */
	public String[] getInfo(String account_ID){
		String[] info=new String[7];
		DBUtil db=new DBUtil();
		String sql="select a.account_Id,account,password,current_balance,fixed_balance,name,sex from account as a inner join person_info as p"
				+ " on a.account_Id=p.account_ID where a.account_Id="+account_ID+";";
		rs=db.rsExecute(sql);
		try {
			rs.next();
			for(int i=0;i<7;i++)
				info[i]=rs.getString(i+1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		info=knowSex(info);
		db.close();
		return info;
	}
	/**
	 * ��Ϣ�ֱ�Ϊ��
	 * [�˻�ID�����ţ����룬���ڴ����ڴ��������Ա�]
	 * @return	������Ϣ���ַ�������
	 */
	public String[] getInfo(){
		return s;
	}
	/**
	 * ʶ�������ִ�����Ա���Ϣ
	 */
	private String[] knowSex(String[] info){
		if(info[6].equals("1"))
			info[6]="����";
		else info[6]="Ůʿ";
		return info;
	}
}
