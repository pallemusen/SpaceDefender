package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import sound.PlayThread;
import sound.SoundLoader;

public class Explosion implements ActionListener {
	private int frame, x, y;
	private Timer frameChanger; 
	private boolean trigger = false; 
	private Thread explosionSound = new PlayThread(new SoundLoader("explosion.mp3"));
	
	
	public Explosion() {
		frameChanger = new Timer(40, this);
		frame = 0; 
	}
	
	public void triggerExplosion(int x, int y) {
		trigger = true;
		this.x = x;
		this.y = y;
		//System.out.println("X;Y = " + x + ";" + y);
		
		//starts animation
		frameChanger.start();
	}
	
	public void playSound() {
		explosionSound = new PlayThread(new SoundLoader("explosion.mp3"));
		explosionSound.start();
	}
	
	//paints the explosion
	@SuppressWarnings("static-access")
	public void paint(Graphics g) {
		if(trigger && (frame < 12)) {
			g.drawImage(Board.explosionImages.frames.get(frame).getImage(), x-5, y-10, 60, 60, GameGUI.board);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this method should change state, so that paint method paints different exp-frame
		if(frame < 12) {
			frame += 1;
			//System.out.println("Frame: "+frame);
		} else {
			frameChanger.stop();
		}
	}
}
