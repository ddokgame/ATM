package Bank;

import java.text.SimpleDateFormat;
import java.util.Date;

import UI.Interface.DateUI;

/**
 * ����ʱ���߳�
 * @author overlord
 *
 */
public class DateTimeThread implements Runnable{
	/*
	 * �߳�
	 * ��ʱ���ǩ����
	 * ����ʱ���ȡ��ʽ
	 */
	Thread t;
	DateUI ui;
	private SimpleDateFormat ft=new SimpleDateFormat("yyyy'��'MM'��'dd'��' HH'ʱ'mm'��'ss'��'");

	/**
	 * ���췽��������棬�½�����ʼ���д��߳�
	 * @param ui	��ʱ���ǩ����
	 */
	public DateTimeThread(DateUI ui){
		this.ui=ui;
		t=new Thread(this,"Datetime");
		t.start();
	}
	/**
	 * ��ȡʱ�䲢���ؽ��棬1��ִ��һ��
	 */
	@Override
	public void run() {
		try {
			while(true){
				String datetime=ft.format(new Date());
				ui.setDate(datetime);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
