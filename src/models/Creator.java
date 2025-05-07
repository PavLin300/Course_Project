package models;

import java.awt.Component;

import javax.swing.JLabel;

;

public class Creator extends AbstractWorker{

	private String[] pictures;


	public Creator(Object gui, JLabel label, Queue queue) {
		super(gui, label, queue);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		do {
			// Імітує процес створення транзакції
			showWorking(pictures);
			// Створює транзакцію
			trs = new Transaction(gui);
			
			// Цикл перевірки та, можливо, чекання місця у черзі
			synchronized (queue) {
				while (queue.getQueueSize() >= queue.getMaxSize()) {
					try {
						display("/other/peoplWait1.png");
						
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}// кінець блоку synchronized (queue) і циклу чекання
			
			// Створює поток переміщення транзакції
			Thread t = trs.moveFromTo(this, queue);
			// Призупиняється на час переміщення транзакції
			display("/other/peopljoin1.png");
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (true);// TODO: change to isPlaying
		// Завершення роботи
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
		return null;
	}

	
	

}
