package menu;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final MenuPanel menuPanel;
	
	public MenuFrame() {
		menuPanel = new MenuPanel(); 
		add(menuPanel);
		setFrame();
		
		setVisible(true);
		
	}
	
	private void setFrame() {
		setTitle("SpaceDefender");
		setSize(400,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
	}
	
}
