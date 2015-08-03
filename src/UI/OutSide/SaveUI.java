package UI.OutSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Server.Surface.SaveMoney;
import UI.Interface.SmallWindow_UI;

/**
 * ������
 * @author overlord
 *
 */
public final class SaveUI extends SmallWindow_UI{
	JTextField saveMoney;
	ButtonGroup type;
	JRadioButton type1,type2;

	/**
	 * ���ø��๹�췽��
	 * @param info	��¼��Ϣ
	 */
	public SaveUI(String[] info) {
		super(info);
	}
	/**
	 * ����������
	 */
	public void create(){
		super.create();
		setTitle("��       ��");
		title.setText("��       ��");
		
		JLabel saveHint=new JLabel("��������Ҫ��Ľ�");
		saveHint.setBounds(80, 150, 150, 20);
		add(saveHint);
		
		saveMoney=new JTextField();
		saveMoney.setBounds(230, 150, 150, 20);
		add(saveMoney);
		
		JLabel typeL=new JLabel("������ͣ�");
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
		submit.addActionListener(new ActionListener() {
			/**
			 * ���֮������
			 * ����SaveMoney����
			 * �����Ի�����ʾ����ʾ������ִ�����
			 * 		��������Ի����ȷ����ť��
			 * 			���ɹ����򷵻�������
			 * 			���������д��
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveMoney save=new SaveMoney(info, saveMoney.getText(), selectedType());
				JOptionPane.showMessageDialog(getContentPane(), save.save());
				if(JOptionPane.OK_OPTION==0){
					if(save.getSituation()==2)
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
		saveMoney.setText("");
		type1.setSelected(true);
	}

}
