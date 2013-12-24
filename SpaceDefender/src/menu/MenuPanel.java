package menu;

import game.GameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.ResourceLoader;

public class MenuPanel extends JPanel implements MouseMotionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private ImageIcon menubg; 
	private Font f;
	private int tempX, tempY; 
	private boolean exitMarked; 
	private boolean startMarked; 
	private boolean levelMarked;
	private final int START_X = 100;
	private final int START_Y = 320;
	private final int LEVEL_X = 92; 
	private final int LEVEL_Y = 360;
	private final int EXIT_X = 174; 
	private final int EXIT_Y = 400;
	private final LevelSelect levelSelectFrame = new LevelSelect(); 
	private final GameGUI gameGUI = new GameGUI();
	
	
	public MenuPanel() {
		f = new Font("Helvetica", Font.BOLD, 30);
		addMouseMotionListener(this);
		addMouseListener(this);
		try {
			menubg = new ImageIcon(ImageIO.read(ResourceLoader.load("menubg.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(400,450);
	}
	
	private Color chooseColor(String buttonName) {
		if(buttonName == "EXIT") {
			if(exitMarked) {
				return Color.YELLOW;
			} else {
				return Color.WHITE;
			}
		} else if(buttonName == "START") {
			if(startMarked) {
				return Color.YELLOW;
			} else {
				return Color.WHITE;
			}
		} else if(buttonName == "LEVEL") {
			if(levelMarked) {
				return Color.YELLOW;
			} else {
				return Color.WHITE;
			}
		}
		return null; 
	}
	
	public void paint(Graphics g) {
		g.drawImage(menubg.getImage(), 0, 0, 400, 450, this);
		
		g.setFont(new Font("Helvetica", Font.BOLD, 35));
		
		// title (on earth)
		g.setColor(Color.GRAY);
		g.drawString("SPACEDEFENDERS", 40, 160);
		g.setColor(Color.WHITE);
		g.drawString("SPACEDEFENDERS", 42, 162);
		
		g.setFont(f);
		
		//start game button
		g.setColor(Color.GRAY);
		g.drawString("START GAME", START_X, START_Y);
		g.setColor(chooseColor("START"));
		g.drawString("START GAME", START_X+2, START_Y+2);
		
		//level select button
		g.setColor(Color.GRAY);
		g.drawString("LEVEL SELECT", LEVEL_X, LEVEL_Y);
		g.setColor(chooseColor("LEVEL"));
		g.drawString("LEVEL SELECT", LEVEL_X+2, LEVEL_Y+2);
		
		//EXIT-button
		g.setColor(Color.GRAY);
		g.drawString("EXIT", EXIT_X, EXIT_Y);
		g.setColor(chooseColor("EXIT"));
		g.drawString("EXIT", EXIT_X+2, EXIT_Y+2);
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void mouseClicked(MouseEvent e) {
		if(exitMarked) {
			System.exit(0);
		} else if(startMarked) {
			System.out.println("GAME STARTED!");
			main.Main.menu.setVisible(false); 
			gameGUI.setVisible(true);
			gameGUI.board.startGame();
			gameGUI.board.restartGame();
		} else if (levelMarked) {
			main.Main.menu.setVisible(false);
			levelSelectFrame.setVisible(true);
		} else {
			System.out.println("CLICK");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		tempX = e.getX();
		tempY = e.getY();
		
		
		//System.out.println("tempX: "+e.getX());
		//System.out.println("tempY: "+e.getY());
		
		if(tempX >= EXIT_X && tempX <= EXIT_X+70 && tempY <= EXIT_Y && tempY >= EXIT_Y-20) {
			exitMarked = true; 
		} else if(tempX >= START_X && tempX <= START_X+200 && tempY <= START_Y && tempY >= START_Y-20) {
			startMarked = true; 
		} else if(tempX >= LEVEL_X && tempX <= LEVEL_X+225 && tempY <= LEVEL_Y && tempY >= LEVEL_Y-20) {
			levelMarked = true;
		} else {
			exitMarked = false;
			startMarked = false;
			levelMarked = false; 
		}
		
		repaint();
		
	}
	
}
