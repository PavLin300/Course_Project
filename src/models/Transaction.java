package models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import gui.MainGui;

public class Transaction {
	private Object gui;

	private Graphics g;

	public Transaction(Object gui) {
		this.gui = gui;
		
//		this.stepTimeSlider = gui.getStepTimeSlider();
		
		this.g = ((MainGui) gui).getPane().getGraphics();
		
		// Налаштування коольору графічного контексту
		Color color = Color.RED; 
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

				int xFrom = pointFrom(from).x;
				int xTo = pointTo(to).x;
				if (xFrom > xTo) {
	
					xFrom = pointTo(from).x;
					xTo = pointFrom(to).x;
				}
				int lenX = xTo - xFrom;

				int yFrom = pointFrom(from).y;
				int yTo = pointTo(to).y;
				int lenY = yTo - yFrom;
	
				int len = (int) (Math.round(Math
						.sqrt(lenX * lenX + lenY * lenY)));
		
				int lenT = (hT + wT) / 2;
				
				int n = len / lenT + 1;
				
				int dx = lenX / n;
				int dy = lenY / n;
				
				
				from.onOut(Transaction.this);
				
				for (int x = xFrom, y = yFrom, i = 0; i < n; x += dx, y += dy) {
					// Рисуем транзакцию
					g.fillRect(x, y, wT, hT);
					try {
						// Задержка
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Повторный xor возвращает картинку фона
					g.fillRect(x, y, wT, hT);
				}
				// Вызов метода обработки события "прибытие"
				to.onIn(Transaction.this);
			}
		};
		// Запускаем созданный поток движения транзакции
		t.start();
		return t;
	}


}
