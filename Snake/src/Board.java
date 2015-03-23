
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		timer = new Timer(400, this);
		timer.start();
		
		setFocusable(true);
		setBackground(Color.BLACK);
	}
	
	public void start() {
		repaint();
	}
	
	private void doDrawing(Graphics g) {
		Dimension size = getSize();
		System.out.println(size);
		//g.drawRect(0, 0, 20, 20);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
