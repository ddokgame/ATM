package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Database.DBUtil;

/**
 * ��¼����
 * @author overlord
 *
 */
public class Login {
	/*
	 * ���ţ�����
	 * ��¼�����ļ�������ִ���������
	 */
	String account, password;
	byte count, situation = 0;
	public Login(String account, char[] password, byte count) {
		this.account = account;
		this.password = new String(password);
		this.count = count;
	}

	public Login() {
	}

	/**
	 * �жϵ�¼�Ƿ�ɹ�
	 * @return	�Ƿ�ɹ�
	 */
	private boolean judgeSuccess() {
		boolean bol = false;
		if(!Pattern.matches("^\\d+$", account))
			return bol;
		String sql = "select * from account where account=" + account
				+ " and password=" + password + ";";
		DBUtil db = new DBUtil();
		ResultSet rs = db.rsExecute(sql);
		try {
			if (rs.next())
				bol = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
		return bol;
	}
	
	public boolean judgeSuccess(String account){
		boolean bol = false;
		if(!Pattern.matches("^\\d+$", account))
			return bol;
		String sql = "select * from account where account=" + account+";";
		DBUtil db = new DBUtil();
		ResultSet rs = db.rsExecute(sql);
		try {
			if (rs.next())
				bol = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
		return bol;
	}

	/**
	 * �õ�������ʾ��Ҫ��ʾ������
	 * @return	��ʾ������
	 */
	public String showMessage() {
		if (judgeSuccess()) {
			situation = 1;
			return "��¼�ɹ�";
		}
		if (count == 3) {
			situation = 2;
			return "����3�Σ��Զ��˳�����";
		}
		return "��¼��Ϣ��������������";
	}

	/**
	 * ��ȡִ���������
	 * @return	ִ�����
	 */
	public byte getSituation() {
		return situation;
	}

}
