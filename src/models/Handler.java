package models;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;

public class Handler extends AbstractWorker {

	private Counter counter;
	private String[] pictures = {
	    "/photo/man1.png",
	    "/photo/man2.png",
	    "/photo/man3.png",
	};
	
	public Handler(Object gui, JLabel label, Queue queue, JSlider minWorkTimeSlider, Counter counter) {
		super(gui, label, queue, minWorkTimeSlider);
		// TODO Auto-generated constructor stub
		this.counter = counter;
	}
	
	public void run() {
		while (((MainGui) gui).isCreatorWorking() || queue.getQueueSize() > 0) {
			System.out.println("handle");
			synchronized (queue) {
				while (queue.getQueueSize() <= 0) {
//					display("/other/peoplWait.png");
					
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				trs = (Transaction) queue.removeFirst();
				queue.notify();
			}
			
			Thread t = trs.moveFromTo(queue, this);
//			display("/other/peopljoin.png");

			try {
				
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			showWorking(pictures);		
			trs.moveFromTo(this, counter);
		}
//		display("/other/peoplWait.png");
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
