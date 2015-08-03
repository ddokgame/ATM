package UI.Interface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * ͨ�ú�̨С�ʹ���
 * ��Ҫ���õ���Ŀ��
 * setTitle();Title.setText();date.setBounds();
 * @author overlord
 *
 */
public class SmallAdmin_UI extends AdminGeneral_UI {
	/*
	 * ��ť��ȷ�ϣ�����������
	 * ��������
	 */
	public JButton submit,backup;
	public JLabel title;

	/**
	 * ���ø��๹�췽��
	 * ��ӷ��������水ť�¼�
	 */
	public SmallAdmin_UI(){
		super();
		backupButton();
	}
	/**
	 * ����һ��С������
	 * ����С������λ��
	 * ��ӱ��������Ʊ�ǩ
	 * ��������ʱ����ʾλ��
	 * ��� �ύ������ ��ť
	 */
	public void create(){
		super.create();
		setBounds(300, 200, 500, 400);
		
		title=new JLabel("");
		title.setFont(new Font("΢���ź�", 1, 24));
		title.setBounds(200, 40, 200, 30);
		add(title);
		
		date.setBounds(300, 0, 200, 20);
		
		submit=new JButton("ȷ��");
		backup=new JButton("����������");
		submit.setBounds(70, 270, 170, 20);
		backup.setBounds(240, 270, 180, 20);
		add(submit);
		add(backup);
	}
	
	/**
	 * ��ӷ��ذ�ť������
	 * ����������openMainUI()����
	 */
	public void backupButton(){
		backup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMainUI();
			}
		});
	}
}
