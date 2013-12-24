package player;

import game.Board;
import game.Explosion;
import game.GameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import main.ResourceLoader;

public class Vessel implements ActionListener{
	
	public int hp = 3; 
	public int score = 0; 
	public int x, y, dx, dy, bx, by;
	public boolean alive = true; 
	public Font hpFont = new Font("Helvetica", Font.BOLD, 15);
	private int limitX = 300; // movement limit (300 = half of map)
	public WeaponSystem weapon = new WeaponSystem(); 
	public Timer scoreTimer;
	private Explosion explosion = new Explosion();
	public boolean triggered = false; 
	 
	private ImageIcon vesselIcon;
	
	public Vessel() {
		try {
			vesselIcon = new ImageIcon(ImageIO.read(ResourceLoader.load("vessel.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scoreTimer = new Timer(100, this);
		scoreTimer.start();
	}
	
	public void move() {
		// backup coordinates if move is invalid
		bx = x; 
		by = y;
		
		x += dx; 
		y += dy; 
		
		// if move is invalid, restore previous coordinate
		if((x >= limitX) || (x < 0)) {
			x = bx;
		}
		
		if((y >= 300) || (y < 0)) {
			y = by; 
		}
		
	}
	
	public void resetPos() {
		x = 150;
		y = 200;
	}
	
	public void paint(Graphics g) {
		if(alive) {	
			g.drawImage(vesselIcon.getImage(), x, y, 30, 20, GameGUI.board);	// imageobserveren er panelet "board"
		}
	}
	
	public void paintVesStuff(Graphics g) {
		explosion.paint(g);
		paintHP(g);
		paintScore(g);
		weapon.paintCooldownBar(g);
	}
	
	// paints vessel hp
	//@SuppressWarnings("static-access")
	private void paintHP(Graphics g) {
		g.setFont(hpFont);
		g.setColor(Color.WHITE);
		
		if(hp >= 0) {
			g.drawString("LIFE: " + hp, 520, 30);
			triggered = false;
		} else {
			g.drawString("LIFE: 0", 520, 30);
			
			/*
			 * BELOW CODE CAN BE ERASED
			 */
			
			//Board.resetEnemies();
			//vessel has died
			
			
		}
		
	}
	
	public void vesselDead() {
	System.out.println("TRIGGERED: " + triggered);
	triggered = true;
	Board.spawner.spawnerTimer.stop();
	explosion.playSound();
	explosion.triggerExplosion(x, y);
	}
	
	private void paintScore(Graphics g) {
		g.drawString("SCORE: " + score, 30, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(alive) {
			score += 1;
		}
		
	}
	
}
