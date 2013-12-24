package player;

import game.Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import sound.PlayThread;
import sound.SoundLoader;
import main.ResourceLoader;


public class WeaponSystem implements ActionListener {
	
	private Timer shotCDTimer; 
	private Timer missileCDTimer; 
	private Timer cdBarTimer; 
	private Timer missileBarTimer; 
	
	public ImageIcon missileIcon; 
	
	private boolean shotReady = true;
	private boolean missileReady = true;
	
	private int shotCDX = 90;
	private int missileCDX = 90; 
	
	private cdBarListener cdAction = new cdBarListener();
	private missileCDBarListener missileBar = new missileCDBarListener();
	private missileCDListener missileAction = new missileCDListener();
	
	// a thread object with playthread abilities - loads my_laser.mp3 and can play it
	private Thread laserThread = new PlayThread(new SoundLoader("laser.mp3"));
	private Thread missileThread = new PlayThread(new SoundLoader("missile.mp3"));
	
	
	public static ArrayList<Shot> shots; 
	
	public WeaponSystem() {
		shotCDTimer = new Timer(300, this);	//weapon cooldown
		missileCDTimer = new Timer(5000, missileAction);	//cd på missile (5 sek)
		cdBarTimer = new Timer(10, cdAction); //cooldown bar timer that fills up timer
		missileBarTimer = new Timer(55, missileBar);
		shots = new ArrayList<Shot>();
		
		try {
			missileIcon = new ImageIcon(ImageIO.read(ResourceLoader.load("missile.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//takes shottype as arg, and shoots corresponding shot, adding both correct dmg and movement 
	public void fire(int shotType) {
		if(shotType == 0) {	//normal gunfire
			if(shotReady && Board.ves.alive) {
				shotReady = false; 
				shotCDTimer.start();
				shots.add(new Shot(Board.ves.x, Board.ves.y, 1, 3, 0));	//shots and missiles speed are OLD-STYLE = 3
				
				shotCDX = 0; 
				cdBarTimer.start();
				
				laserThread = new PlayThread(new SoundLoader("laser.mp3"));
				laserThread.start();
			}
		} else if(shotType == 1) {	//missile
			if(missileReady && Board.ves.alive) {
				missileCDTimer.start();
				missileReady = false; 
				shots.add(new Shot(Board.ves.x, Board.ves.y, 3, 1, 1));	//(x, y, dmg, speed, shottype)
				
				missileCDX = 0; 
				missileBarTimer.start();
				
				missileThread = new PlayThread(new SoundLoader("missile.mp3"));
				missileThread.start();
			}
		}	
	}

	public void paintShots(Graphics g) {
		for(int i = 0; i < shots.size(); i++) {
			shots.get(i).paint(g);
		}
	}
	
	public void paintCooldownBar(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(30, 340, 91, 15);
		g.drawString("Gunfire", 30, 330);
		
		g.drawRect(150, 340, 91, 15);
		g.drawString("Missile", 150, 330);
		
		g.setColor(Color.RED);
		g.fillRect(31, 341, shotCDX, 14);
		
		g.setColor(Color.BLUE);
		g.fillRect(151, 341, missileCDX, 14);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		cdBarTimer.stop();
		shotCDX = 90; //længde af cd bar
		//System.out.println("Weapon recharged!");
		shotReady = true; 
		shotCDTimer.stop();
	}
	
	private class cdBarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			shotCDX += 3; //dette gange (300/10)=30 giver total længde af cd bar = 90
		}
	}
	
	private class missileCDListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			missileCDX = 90; 
			missileReady = true; 
			missileCDTimer.stop();
			//System.out.println("Missile rdy");
			missileBarTimer.stop();
		}
	}
	
	private class missileCDBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			missileCDX += 1; 
		}
		
	}
	
}
