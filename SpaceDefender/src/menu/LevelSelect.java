package menu;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Main;
import main.ResourceLoader;

public class LevelSelect extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	public LevelSelectPanel lsPanel;
	private ImageIcon lsIcon;
	
	public LevelSelect() {
		setFrame();
		setContent();
		try {
			lsIcon = new ImageIcon(ImageIO.read(ResourceLoader.load("level_overview.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		// DEV PURPOSE - ret dette og i main.Main
		setVisible(true);
	}
	
	private void setContent() {
		 lsPanel = new LevelSelectPanel();
		 lsPanel.setBackground(java.awt.Color.CYAN);
		 add(lsPanel);
	}
	
	public void paint(Graphics g) {
		g.drawImage(lsIcon.getImage(), 0, 0, this);
	}
	
	private void setFrame() {
		setTitle("Level Select");
		setSize(600,600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(this);
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		Main.menu.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}
	
	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
