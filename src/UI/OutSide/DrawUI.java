package UI.OutSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Server.Surface.DrawMoney;
import UI.Interface.SmallWindow_UI;

/**
 * ��ȡ�ֽ��ܵĽ���
 * @author overlord
 *
 */
public final class DrawUI extends SmallWindow_UI{
	/*
	 * ��������
	 * ��ѡ��ť��
	 * ���͵�ѡ��ť1��2
	 */
	JTextField drawMoney;
	ButtonGroup type;
	JRadioButton type1,type2;
	/**
	 * ��ȡ���๹�췽��
	 * @param info	��¼��Ϣ
	 */
	public DrawUI(String[] info){
		super(info);
	}
	/**
	 * ����ȡ�����
	 */
	public void create(){
		super.create();
		setTitle("��ȡ�ֽ�");
		
		title.setText("��ȡ�ֽ�");
		
		JLabel drawhint=new JLabel("��������Ҫȡ�Ľ�");
		drawhint.setBounds(80, 150, 150, 20);
		add(drawhint);
		
		drawMoney=new JTextField();
		drawMoney.setBounds(230, 150, 150, 20);
		add(drawMoney);
		
		JLabel typeL=new JLabel("ȡ�����ͣ�");
		typeL.setBounds(80, 190, 80, 20);
		add(typeL);
		
		type=new ButtonGroup();
		type1=new JRadioButton("����", true);
		type.add(type1);
		type2=new JRadioButton("����", false);
		type.add(type2);
		
		type1.setBounds(230, 190, 80, 20);
		type2.setBounds(310, 190, 70, 20);
		add(type1);
		add(type2);
	}
	
	/**
	 * �ύ��ť�ļ�����
	 */
	public void listen(){
		/**
		 * ���֮������
		 * ����DrawMoney����
		 * �����Ի�����ʾ����ʾȡ�����ִ�����
		 * 		��������Ի����ȷ����ť��
		 * 			���ɹ����򷵻�������
		 * 			���������д��
		 */
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DrawMoney draw=new DrawMoney(info, drawMoney.getText(), selectedType());
				JOptionPane.showMessageDialog(getContentPane(), draw.draw());
				if(JOptionPane.OK_OPTION==0){
					if(draw.getSituation()==2)
						openMainUI();
					else clear();
				}
			}
		});
	}
	/**
	 * ��������ʽ���ش�����͵�ѡ��
	 * @return	���ֱ�ʾ�Ĵ������
	 */
	private byte selectedType(){
		byte knowType=0;
		if(type2.isSelected())
			knowType=1;
		return knowType;
	}
	/**
	 * �����д��
	 */
	private void clear(){
		drawMoney.setText("");
		type1.setSelected(true);
	}
}
