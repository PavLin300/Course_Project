package models;

import java.awt.Component;

import javax.swing.JLabel;

import gui.MainGui;

public class Handler extends AbstractWorker {

	private Counter counter;
	private String[] pcts = {
	    "",
	    "",
	    ""
	};
	
	public Handler(Object gui, JLabel label, Queue queue) {
		super(gui, label, queue);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
	    while (((MainGui)gui).isCreatorWorking() || queue.getQueueSize() > 0) {
	        synchronized (queue) {
	            while (queue.getQueueSize() <= 0) {
//	                display("/other/peoplWait.png");
	                try {
	                    queue.wait();
	                } catch (InterruptedException e) {
	                    Thread.currentThread().interrupt();
	                    return;
	                }
	            }

	            // 2. Беремо транзакцію з черги
	            trs = (Transaction) queue.removeFirst();
	            queue.notify();
	        }

	        // 3. Переміщуємо транзакцію до себе
	        Thread moveToHandler = trs.moveFromTo(queue, this);
//	        display("/other/peopljoin.png");

	        // 4. Чекаємо завершення переміщення
	        try {
	            moveToHandler.join();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            return;
	        }

	        // 5. Імітація обробки транзакції
	        showWorking(pcts);

	        // 6. Переміщення транзакції до лічильника
	        trs.moveFromTo(this, counter);
	        counter.increment(); // не забудь збільшити лічильник обробок
	    }

	    // Завершення роботи
//	    display("/other/peoplWait.png");
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
