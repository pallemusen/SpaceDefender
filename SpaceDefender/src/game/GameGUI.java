package game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import player.Vessel;

public class GameGUI extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	
	public static Board board; 
	
	public GameGUI() {
		System.out.println("GAMEGUI: running");
		setFrame();
		setComp();
		addComp();
		
	}
	
	private void setFrame() {
		setTitle("SpaceDefender");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		addWindowListener(this);
		
		System.out.println("GAMEGUI: frame is set");
	}
	
	private void setComp() {
		Vessel ves = new Vessel();
		board = new Board(ves);
		board.setBounds(0,0,600,400);
		board.setFocusable(true);
	}
	
	private void addComp() {
		add(board);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		board.stopGame();
		main.Main.menu.setVisible(true);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
