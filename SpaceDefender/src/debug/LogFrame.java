package debug;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * This class makes a seperate window, which can be used as a debug log for the compiled version of the game.
 * The method 'logMsg(String msg)' writes the msg in the log, and scrolls the scrollpane the farthest down it can. 
 * 
 */

public class LogFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea area; 
	private JScrollPane scroll;
	
	public LogFrame() {
		setFrame();
		setContent();
		
		
		setVisible(false);
	}
	
	private void setContent() {
		area = new JTextArea();
		area.setEditable(false);
		
		scroll = new JScrollPane(area);
		add(scroll);
		
		area.append("### LOGGING AREA ###");
	}
	
	private void setFrame() {
		setSize(300,200);
		setTitle("SpaceDefender - Debug Log");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setLocationRelativeTo(null);
		
	}
	
	//appends a message to the log, and scrolls the scrollpane the farthest down possible
	public void logMsg(String msg) {
		area.append("\n-"+msg);
		area.setCaretPosition(area.getDocument().getLength());
	}
	
}
