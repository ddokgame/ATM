package UI.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ������ʱ�����
 * @author overlord
 *
 */
public class DateUI extends JFrame{
	//��ʾ����ʱ���ǩ
	public JLabel date;
	
	/**
	 * ���һ��ʱ���ǩ
	 */
	public void create(){
		date=new JLabel("aaaaaaaaa");
		add(date);
	}

	/**
	 * ����date����ʾ����
	 * @param s	������ʾ����
	 */
	public void setDate(String s) {
		date.setText(s);
	}
}
