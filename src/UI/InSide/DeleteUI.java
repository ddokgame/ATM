package UI.InSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Server.Interior.Delete;
import UI.Interface.SmallAdmin_UI;

/**
 * ��̨��������
 * @author overlord
 *
 */
public final class DeleteUI extends SmallAdmin_UI{
	//����1������2
	JTextField account1,account2;
	
	/**
	 * ���ø��๹�췽��
	 */
	public DeleteUI(){
		super();
	}
	
	/**
	 * ������������
	 */
	public void create(){
		super.create();
		String ti="��      ��";
		setTitle(ti);
		title.setText(ti);
		
		JLabel acl1=new JLabel("������Ҫע�����˻���");
		JLabel acl2=new JLabel("���ٴ�����ע���˻���");
		acl1.setBounds(80, 150, 130, 20);
		acl2.setBounds(80, 190, 130, 20);
		add(acl1);
		add(acl2);
		
		account1=new JTextField();
		account2=new JTextField();
		account1.setBounds(220, 150, 200, 20);
		account2.setBounds(220, 190, 200, 20);
		add(account1);
		add(account2);
	}
	
	/**
	 * ���ȷ�ϰ�ť������
	 */
	public void listen(){
		submit.addActionListener(new ActionListener() {
			/**
			 * ������̨ɾ������
			 * �����Ի��򣬵���ɾ������ķ��������ʾ��Ϣ
			 * ���ȷ�ϰ�ť���ɹ��򷵻غ�̨���������棬���������д��
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				Delete delete=new Delete(account1.getText(), account2.getText());
				JOptionPane.showMessageDialog(getContentPane(), delete.delete());
				if(JOptionPane.OK_OPTION==0){
					if(delete.getSituation()==4)
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
	}
}
