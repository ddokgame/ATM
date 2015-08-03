package UI.OutSide;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Bank.DateTimeThread;
import UI.Interface.General_UI;

/**
 * ��¼�����������
 * @author overlord
 *
 */
public final class MainInterface extends General_UI{
	/*
	 * ��Ӱ�ť����ȡ�ֽ𣬴�ת��ҵ�񣬲�ѯ���޸����룬�˳�����
	 */
	private JButton draw,save,transfer,inquiry,alterPW,exit;

	/**
	 * ��ȡ����Ĺ��췽��
	 * @param info	��¼��Ϣ
	 */
	public MainInterface(String[] info) {
		super(info);
	}
	/**
	 * ����������
	 */
	public void create(){
		super.create();
		setTitle("������");
		setBounds(300, 200, 800, 600);
		
		JLabel title=new JLabel("��ѡ������Ҫ�ķ���");
		title.setFont(new Font("��������", 0, 24));
		title.setBounds(290, 30, 300, 60);
		add(title);
		
		date.setBounds(600, 0, 200, 20);
		
		JLabel name=new JLabel(info[5]+info[6]+"���ã�");
		name.setFont(new Font("����", 1, 16));
		name.setBounds(320, 15, 200, 20);
		add(name);
		
		
		draw=new JButton("��ȡ�ֽ�");
		save=new JButton("��        ��");
		transfer=new JButton("ת��ҵ��");
		inquiry=new JButton("��ѯ���");
		alterPW=new JButton("�޸�����");
		exit=new JButton("�˳�����");
		
		draw.setBounds(0, 100, 250, 60);
		save.setBounds(0, 270, 250, 60);
		transfer.setBounds(0, 450, 250, 60);
		inquiry.setBounds(550, 100, 250, 60);
		alterPW.setBounds(550, 270, 250, 60);
		exit.setBounds(550, 450, 250, 60);
		
		add(draw);
		add(save);
		add(transfer);
		add(inquiry);
		add(alterPW);
		add(exit);
	}
	
	/**
	 * ���6�����ܰ�ť�ļ��������򿪹��ܽ��桢�˳�����
	 */
	public void listen(){
		draw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new DrawUI(info));
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new SaveUI(info));
			}
		});
		transfer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new TransferUI(info));
			}
		});
		alterPW.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new AlterPasswordUI(info));
			}
		});

		inquiry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new InquiryUI(info));
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

}
