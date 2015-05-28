package ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.StrategyGame;

/**
 * 
 * @author Sam Costigan
 *
 */

public class Strategy extends JFrame implements ActionListener {
	
	private JPanel menu;
	private JPanel endGame;
	private StrategyGame game;
	
	public Strategy() {
		initUI();
	}
	
	private void initUI() {
		createMenuBar();
		
		setSize(606,628);
		setTitle("Strategy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void createMenuBar() {
		menu = new JPanel();
		
		JButton startBtn = new JButton("Start");
		startBtn.setActionCommand("Start");
		startBtn.addActionListener(this);
		
		menu.add(startBtn);
		
		add(menu, BorderLayout.CENTER);
	}
	
	private void startGame() {
		game = new StrategyGame();
		add(game);
		game.start();
		
		menu.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			startGame();
		}
	}
	
	public static void main(String[] args) {	
		Strategy game = new Strategy();
		game.setVisible(true);
	}
	
	

}
