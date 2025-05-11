package models;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;

;

public class Creator extends AbstractWorker{

	private String[] pictures = {
	    "/photo/man1.png",
	    "/photo/man2.1.png",
	    "/photo/man3.png",
	};
	public Creator(Object gui, JLabel label, Queue queue, JSlider minWorkTimeSlider) {
		super(gui, label, queue, minWorkTimeSlider);
		// TODO Auto-generated constructor stub
	}
	
	
	public void run() {
		do {
			showWorking(pictures);
			trs = new Transaction(gui);
			synchronized (queue) {
				while (queue.getQueueSize() >= queue.getMaxSize()) {
					
					try {
						display("/photo/man2.png");
						
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			Thread t = trs.moveFromTo(this, queue);
			display("/photo/man2.png");
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		} while (((MainGui) gui).getIsPlaying());
		
		display("/photo/man2.png");
	}




	@Override
	public void onOut(Transaction tr) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onIn(Transaction tr) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return label;
	}

	
	

}
