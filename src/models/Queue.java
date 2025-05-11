package models;

import java.awt.Component;
import java.util.ArrayDeque;

import javax.swing.JSlider;
import javax.swing.JTextField;

import gui.MainGui;

public class Queue implements IfromTo{
	private Object gui;
	public Counter refuseCounter; 
	public Counter counter;
	private ArrayDeque<Transaction> que = new ArrayDeque<Transaction>();
	private JSlider slider;
	private int maxSizeOfPlane = 10;
		

	public Queue(Object gui, Counter counter,  Counter refuseCounter, JSlider slider) {
		this.gui = gui;
		this.refuseCounter = refuseCounter;
		this.slider = slider;
		this.counter = counter;
	}
	
	
	public void onIn(Transaction tr) {
		synchronized (this) {
			if(counter.getCounter() >= maxSizeOfPlane) {
				tr.moveFromTo(this, refuseCounter);
				((MainGui) gui).doStopPlay();
				return;
			}
			if (getQueueSize() < getMaxSize()) {
				addLast(tr);
				this.slider.setValue(getQueueSize());
				this.notify();
				return;
			}
		}
		
		tr.moveFromTo(this, refuseCounter);
	}


	public void addLast(Transaction tr) {
		// TODO Auto-generated method stub
		que.addLast(tr);
	}
	
	public Transaction removeFirst() {
		this.slider.setValue(getQueueSize());
		return que.removeFirst(); 
	}


	public int getMaxSize() {
		// TODO Auto-generated method stub
		return 10;
	}


	public int getQueueSize() {
		// TODO Auto-generated method stub
		return que.size();
	}


	@Override
	public void onOut(Transaction tr) {
		// TODO Auto-generated method stub


	}


	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return slider;
	} 

}
