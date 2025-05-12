package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3597640841694116827L;
	private final List<Rectangle> rects = new CopyOnWriteArrayList<>();

    public void addRect(Rectangle rect) {
        rects.add(rect);
        repaint();
    }

    public void clearRects() {
        rects.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Rectangle r : rects) {
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }
}
