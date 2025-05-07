package models;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class AbstractWorker implements IfromTo{
	protected Object gui;
	
	protected Queue queue;
	protected Transaction trs;
	protected JLabel label;
	
	public AbstractWorker(Object gui, JLabel label, Queue queue) {
		this.gui = gui;
		this.queue = queue;
	}
	
	protected void display(String pct) {
		URL u = getClass().getResource(pct);
		ImageIcon image = new ImageIcon(u);
		label.setIcon(image);
	}
	
	protected void showWorking(String[] pictures) {
		new Thread(() -> {
			for (String pic : pictures) {
				display(pic);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
