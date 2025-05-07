package models;

import java.awt.Component;

import javax.swing.JTextField;

public class Counter implements IfromTo{
	
	private int count;
	private JTextField textField;
	
	
	public Counter(int count, JTextField textField) {
		super();
		this.count = count;
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
}
