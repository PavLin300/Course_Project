package models;

import java.awt.Component;

import javax.swing.JTextField;

public class Counter implements IfromTo{
	
	private int count = 0;
	private JTextField textField;
	
	
	public Counter(JTextField textField) {
		super();

		this.textField = textField;
	}


	@Override
	public void onOut(Transaction tr) {
		// TODO Auto-generated method stub
		this.count--;
	}


	@Override
	public void onIn(Transaction tr) {
		// TODO Auto-generated method stub
		this.count++;
	}


	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return textField;
	}


	public void increment() {
		this.count++;
	}
	
}
