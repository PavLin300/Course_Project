package models;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

public abstract class AbstractWorker implements IfromTo, Runnable{
	protected Object gui;
	
	protected Queue queue;
	protected Transaction trs;
	protected JLabel label;
	protected JSlider minWorkTimeSlider; 
	
	public AbstractWorker(Object gui, JLabel label, Queue queue, JSlider minWorkTimeSlider) {
		this.gui = gui;
		this.queue = queue;
		this.label = label;
		this.minWorkTimeSlider = minWorkTimeSlider;
	}
	
	protected void display(String pct) {
		URL u = getClass().getResource(pct);
		ImageIcon image = new ImageIcon(u);
		label.setIcon(image);
	}
	
	protected void showWorking(String[] pictures) {
		Thread t = new Thread(() -> {
			for (String pic : pictures) {
				display(pic);
				try {
					Thread.sleep(minWorkTimeSlider.getValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join(); // чекаємо, поки завершиться
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
