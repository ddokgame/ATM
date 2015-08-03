package UI.InSide;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Bank.DateTimeThread;
import UI.LoginUI;
import UI.Interface.AdminGeneral_UI;

/**
 * ��̨����������
 * @author overlord
 *
 */
public final class AdministrationUI extends AdminGeneral_UI {
	//��ϸ���ʻ������������ת�������������������ص�¼����
	JButton detail,manage,typeChange,open,delete,backup;
	/**
	 * ���ô�������ĸ��๹�췽��
	 */
	public AdministrationUI(){
		super();
	}
	/**
	 * ����һ���������
	 */
	public void create(){
		super.create();
		setBounds(300, 200, 800, 600);
		
		date.setBounds(600, 0, 200, 20);
		
		detail=new JButton("��       ϸ");
		manage=new JButton("�ʻ�����");
		typeChange=new JButton("�������ת��");
		open=new JButton("��       ��");
		delete=new JButton("��       ��");
		backup=new JButton("���ص�¼");
		
		JLabel title=new JLabel("Ҫ����ʲô��̨����?");
		title.setFont(new Font("��������", 0, 24));
		title.setBounds(290, 30, 300, 60);
		add(title);
		
		detail.setBounds(0, 100, 250, 60);
		manage.setBounds(0, 270, 250, 60);
		typeChange.setBounds(0, 450, 250, 60);
		open.setBounds(550, 100, 250, 60);
		delete.setBounds(550, 270, 250, 60);
		backup.setBounds(550, 450, 250, 60);
		
		add(detail);
		add(manage);
		add(typeChange);
		add(open);
		add(delete);
		add(backup);
	}
	
	public void listen(){
		
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new DeleteUI());
			}
		});
		backup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LoginUI();
			}
		});
	}
}
