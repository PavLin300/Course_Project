package models;

import java.awt.Component;
import java.util.ArrayDeque;

import javax.swing.JSlider;

import gui.MainGui;

public class Queue implements IfromTo{
	private Object gui;
	public Counter refuseCounter; 
	private ArrayDeque<Transaction> que = new ArrayDeque<Transaction>();
	private JSlider slider;
		

	public Queue(Object gui, Counter refuseCounter, JSlider slider) {
		this.gui = gui;
		this.refuseCounter = refuseCounter;
		this.slider = slider;
	}
	
	
	public void onIn(Transaction tr) {
		synchronized (this) {
			if (getQueueSize() < getMaxSize()) {
				addLast(tr);
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
