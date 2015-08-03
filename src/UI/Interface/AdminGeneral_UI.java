package UI.Interface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import Bank.DateTimeThread;
import UI.InSide.AdministrationUI;

/**
 * ��̨����ͨ�ý���
 * @author overlord
 *
 */
public class AdminGeneral_UI extends DateUI implements UI {
	
	/**
	 * ���湹�췽����
	 * ��������
	 * ��ʾ����
	 * ��ӽ���Ԫ�ؼ�����
	 * ��ӹرմ����˳����򷽷�
	 */
	public AdminGeneral_UI(){
		create();
		setVisible(true);
		listen();
		closeWindow();
	}
	
	/**
	 * ����һ����������
	 */
	@Override
	public void create() {
		super.create();
		setRootPaneCheckingEnabled(true);
		setLayout(null);
		getContentPane().setBackground(c);
		setResizable(false);
		
		JLabel account=new JLabel("����Ա");
		account.setBounds(10, 0, 150, 20);
		add(account);
		
	}

	/**
	 * ����Ԫ�صļ�����
	 */
	@Override
	public void listen() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ����رմ����˳�����ļ�����
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
	 * �رյ�ǰ���棬����һ���������
	 */
	@Override
	public void openMainUI() {
		dispose();
		new DateTimeThread(new AdministrationUI());
	}

}
