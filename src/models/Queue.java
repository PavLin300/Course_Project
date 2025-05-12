package models;

import java.awt.Component;
import java.util.ArrayDeque;

import javax.swing.JSlider;
import javax.swing.JTextField;

import gui.MainGui;

public class Queue implements IfromTo{
	private Object gui;
	public Counter refuseCounter; 
	private ArrayDeque<Transaction> que = new ArrayDeque<Transaction>();
	private JSlider slider;
	private int maxSizeOfPlane = 30;
	private int currentSizeOfPlane = 0;
		

	public Queue(Object gui,  Counter refuseCounter, JSlider slider) {
		this.gui = gui;
		this.refuseCounter = refuseCounter;
		this.slider = slider;
	}
	
	
	public void onIn(Transaction tr) {
		synchronized (this) {
//			System.out.println(getQueueSize());
			if(currentSizeOfPlane >= maxSizeOfPlane) {
				tr.moveFromTo(this, refuseCounter);
				((MainGui) gui).doStopPlay();
				return;
			}
			if (getQueueSize() < getMaxSize()) {
				addLast(tr);
				this.currentSizeOfPlane++;
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
