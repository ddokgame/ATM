package UI.OutSide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Server.Surface.Transfer;
import UI.Interface.SmallWindow_UI;

/**
 * ת��ҵ�����
 * @author overlord
 *
 */
public final class TransferUI extends SmallWindow_UI{
	/*
	 * ����򣺿���1������2�����
	 * ��ʾ��ǩ1��2
	 */
	JTextField account1,account2,money;
	JLabel alert1,alert2;
	
	/**
	 * ���ø��๹�췽��
	 * @param info	�˻���Ϣ
	 */
	public TransferUI(String[] info) {
		super(info);
	}
	/**
	 * ����ת�˽���
	 */
	public void create(){
		super.create();
		setTitle("ת��ҵ��");
		title.setText("ת��ҵ��");
		
		JLabel hint1=new JLabel("������Ҫת��Ŀ��ţ�");
		JLabel hint2=new JLabel("���ٴ�ȷ��ת�뿨�ţ�");
		JLabel hint3=new JLabel("��������Ҫת�˵Ľ�");
		hint1.setBounds(80, 130, 130, 20);
		hint2.setBounds(80, 170, 130, 20);
		hint3.setBounds(66, 210, 150, 20);
		add(hint1);
		add(hint2);
		add(hint3);
		
		account1=new JTextField();
		account2=new JTextField();
		money=new JTextField();
		account1.setBounds(210, 130, 210, 20);
		account2.setBounds(210, 170, 210, 20);
		money.setBounds(210, 210, 210, 20);
		add(account1);
		add(account2);
		add(money);
		
		alert1=new JLabel();
		alert1.setForeground(Color.red);
		alert1.setBounds(430, 130, 70, 20);
		add(alert1);
		
		alert2=new JLabel();
		alert2.setForeground(Color.red);
		alert2.setBounds(430, 170, 70, 20);
		add(alert2);
	}
	/**
	 * ��ӽ���Ԫ�ؼ�����
	 */
	public void listen(){
		/**
		 * ������򿨺�1��ӽ����������
		 * ʧ�����ж��Ƿ���ڿ���1���˻����۽��������ǩ��ʾ
		 */
		account1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(!new Transfer(account1.getText()).judgeAccountExist())
					alert1.setText("�˻�������");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				alert1.setText("");
			}
		});

		/**
		 * ������򿨺�2��ӽ����������
		 * ʧ�����ж����ο��������Ƿ���ͬ���۽��������ǩ��ʾ
		 */
		account2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(!account2.getText().equals(account1.getText()))
					alert2.setText("���벻һ��");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				alert2.setText("");
			}
		});
		/**
		 * ���ȷ�ϰ�ť��������
		 * ����򵯳���ʾ�򣬸�֪����ִ�������
		 * �ɹ���������棬���������д
		 */
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Transfer trans=new Transfer(info, account1.getText(), account2.getText(), money.getText());
				JOptionPane.showMessageDialog(getContentPane(), trans.transfer());
				if(JOptionPane.OK_OPTION==0){
					if(trans.getSituation()==5)
						openMainUI();
					else clear();
				}
			}
		});
	}
	
	/**
	 * �����д��
	 */
	private void clear(){
		account1.setText("");
		account2.setText("");
		money.setText("");
		alert1.setText("");
		alert2.setText("");
	}

}
