package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Bank.DateTimeThread;
import Bank.LoginInfo;
import Server.Login;
import UI.InSide.AdministrationUI;
import UI.Interface.UI;
import UI.OutSide.MainInterface;

/**
 * �򿪳���Ļ�����¼����
 * @author overlord
 *
 */
public class LoginUI extends JFrame implements UI{
	/*
	 * ���������
	 * ���������
	 * ��ť����¼�������¼���˳�
	 * ��¼����������
	 */
	JTextField accountF;
	JPasswordField passwordF;
	JButton login,admin,exit;
	byte count=1;
	/**
	 * ������ã�
	 * ��������
	 * ��Ӽ�����
	 * ��ӹرմ��ڷ���
	 */
	public LoginUI(){
		create();
		listen();
		closeWindow();
	}
	/**
	 * ������¼����
	 */
	@Override
	public void create() {
		setTitle("��¼ATM_Rebuild");
		setRootPaneCheckingEnabled(true);
		setLayout(null);
		setBounds(300, 200, 500, 300);
		getContentPane().setBackground(c);
		setResizable(false);
		
		JLabel welcome=new JLabel("��ӭ�������е�¼ϵͳ��");
		welcome.setFont(new Font("��������", 0, 24));
		welcome.setBounds(120, 20, 300, 50);
		add(welcome);
		
		JLabel accountL=new JLabel("�˺ţ�");
		JLabel passwordL=new JLabel("���룺");
		accountL.setBounds(100, 80, 60, 20);
		passwordL.setBounds(100, 120, 60, 20);
		add(accountL);
		add(passwordL);
		
		accountF=new JTextField();
		passwordF=new JPasswordField();
		accountF.setBounds(160, 80, 180, 20);
		passwordF.setBounds(160, 120, 180, 20);
		add(accountF);
		add(passwordF);
		
		login=new JButton("��¼");
		admin=new JButton("�����̨");
		exit=new JButton("�˳�");
		login.setBounds(100, 160, 90, 20);
		admin.setBounds(190, 160, 90, 20);
		exit.setBounds(280, 160, 100, 20);
		add(login);
		add(admin);
		add(exit);
		
		setVisible(true);
	}
	/**
	 * ��ӽ���Ԫ�ؼ�����
	 */
	@Override
	public void listen() {
		
		/**
		 * ��ӵ�¼��ť��������
		 * ����򵯳���ʾ����ʾ��¼���
		 * �ɹ�����������Ӧ�������棬���������ĿҪ����������
		 * ��3�ε�¼ʧ�ܣ����˳�����
		 */
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Login log=new Login(accountF.getText(), passwordF.getPassword(), count);
				JOptionPane.showMessageDialog(getContentPane(), log.showMessage());
				if(JOptionPane.OK_OPTION==0){
					switch(log.getSituation()){
					case 1:{
						dispose();
						new DateTimeThread(new MainInterface(new LoginInfo(accountF.getText()).getInfo()));
						return;
					}
					case 2:{
						System.exit(0);
					}
					default:{
						count++;
						clear();
					}
					}
				}
			}
		});
		
		/**
		 * �������Ա��¼
		 */
		admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ac=accountF.getText();
				String pw=new String(passwordF.getPassword());
				String hint="����Ա��¼ʧ�ܣ�";
				byte judge=0;
				if(ac.equals("admin")&&pw.equals("admin")){
					hint="����Ա��¼�ɹ�";
					judge=1;
				}
				JOptionPane.showMessageDialog(getContentPane(), hint);
				if(JOptionPane.OK_OPTION==0){
					if(judge==1){
						dispose();
						new DateTimeThread(new AdministrationUI());
					} else clear();
				}
			}
		});
		
		/**
		 * ���exit�˳�����
		 */
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * ��Ӵ��ڹر��˳����������
	 */
	@Override
	public void closeWindow() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	/**
	 * �����д��Ŀ
	 */
	private void clear(){
		accountF.setText("");
		passwordF.setText("");
	}
	/**
	 * �ӿڷ�������ʵ��
	 */
	public void openMainUI(){}
}
