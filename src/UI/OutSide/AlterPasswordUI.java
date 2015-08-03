package UI.OutSide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Server.Surface.AlterPW;
import UI.Interface.SmallWindow_UI;

/**
 * �޸��������
 * @author overlord
 *
 */
public final class AlterPasswordUI extends SmallWindow_UI{
	/*
	 * �������������
	 * �������ѱ�ǩ
	 */
	JPasswordField old,new1,new2;
	JLabel alert1,alert2;

	/**
	 * ���ø���Ĺ��췽��
	 * @param info	��¼��Ϣ
	 */
	public AlterPasswordUI(String[] info) {
		super(info);
	}
	
	public void create(){
		super.create();
		setTitle("�޸�����");
		title.setText("�޸�����");
		
		JLabel hint1=new JLabel("������ԭ���룺");
		JLabel hint2=new JLabel("�����������룺");
		JLabel hint3=new JLabel("���ٴ����������룺");
		hint1.setBounds(80, 130, 130, 20);
		hint2.setBounds(80, 170, 130, 20);
		hint3.setBounds(80, 210, 130, 20);
		add(hint1);
		add(hint2);
		add(hint3);
		
		alert1=new JLabel();
		alert1.setForeground(Color.red);
		alert1.setBounds(430, 130, 70, 20);
		add(alert1);
		
		alert2=new JLabel();
		alert2.setForeground(Color.red);
		alert2.setBounds(430, 170, 70, 20);
		add(alert2);
		
		old=new JPasswordField();
		new1=new JPasswordField();
		new2=new JPasswordField();
		old.setBounds(210, 130, 210, 20);
		new1.setBounds(210, 170, 210, 20);
		new2.setBounds(210, 210, 210, 20);
		add(old);
		add(new1);
		add(new2);
	}
	
	public void listen(){
		old.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String oldPW=new String(old.getPassword());
				if(!oldPW.equals(info[2]))
					alert1.setText("ԭ�������");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				alert1.setText("");
			}
		});
		new1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String newPW=new String(new1.getPassword());
				if(!new AlterPW().judgeLegal(newPW))
					alert2.setText("���벻�Ϸ�");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				alert2.setText("");
			}
		});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlterPW alter=new AlterPW(info, old.getPassword(), new1.getPassword(), new2.getPassword());
				JOptionPane.showMessageDialog(getContentPane(), alter.alter());
				if(JOptionPane.OK_OPTION==0){
					if(alter.getSituation()==4)
						openMainUI();
					else clear();
				}
			}
		});
	}

	private void clear() {
		old.setText("");
		new1.setText("");
		new2.setText("");
	}

}
