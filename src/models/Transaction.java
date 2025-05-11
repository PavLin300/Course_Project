package models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JSlider;

import gui.MainGui;

public class Transaction {
	private Object gui;

	private Graphics g;
	
	private JSlider stepTimeSlider;

	public Transaction(Object gui) {
		this.gui = gui;
		this.stepTimeSlider = ((MainGui) gui).getStepTimeSlider();
		this.g = ((MainGui) gui).getPane().getGraphics();
		// Налаштування коольору графічного контексту
		Color color = Color.RED; //Колір транзакції
		Color back = ((MainGui) gui).getPane().getBackground();
		int rgb = back.getRGB() ^ color.getRGB();
		g.setXORMode(new Color(rgb));

	}
	
	public Point pointTo(IfromTo point) {
		Component comp = point.getComponent();
	    Point location = comp.getLocation();
	    // Координати середини лівої кромки
	    int x = location.x;
	    int y = location.y + comp.getHeight() / 2;
	    return new Point(x, y);
	}
	
	public Point pointFrom(IfromTo point) {
		Component comp = point.getComponent();
	    Point location = comp.getLocation();
	    // Координати середини правої кромки
	    int x = location.x + comp.getWidth();
	    int y = location.y + comp.getHeight() / 2;
	    return new Point(x, y);
	}
	
	public Thread moveFromTo(final IfromTo from, final IfromTo to) {
		Thread t = new Thread() {
			public void run() {
				int hT = 15, wT=15 ;
				int xOffset = 20;
				int xFrom = pointFrom(from).x + xOffset;
				int xTo = pointTo(to).x + xOffset;
				if (xFrom > xTo) {
					
					xFrom = pointTo(from).x;
					xTo = pointFrom(to).x;
				}
				int lenX = xTo - xFrom;
				
				int yOffset = -30;
				int yFrom = pointFrom(from).y + yOffset;
				int yTo = pointTo(to).y + yOffset;
				int lenY = yTo - yFrom;
				
				int len = (int) (Math.round(Math
						.sqrt(lenX * lenX + lenY * lenY)));
				
				int lenT = (hT + wT) / 2;
				
				int n = len / lenT + 1;
				
				int dx = lenX / n;
				int dy = lenY / n;
				
				
				from.onOut(Transaction.this);
				
				for (int x = xFrom, y = yFrom, i = 0; i < n; x += dx, y += dy, i++) {
					g.fillRect(x, y, wT, hT);
					try {
						
						Thread.sleep(stepTimeSlider.getValue());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					g.fillRect(x, y, wT, hT);
					
				}
				
				to.onIn(Transaction.this);
			}
		};
		
		t.start();
		return t;
	}



}
