package enemy;

import game.Board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import sound.PlayThread;
import sound.SoundLoader;

public class EnemyShot implements ActionListener {
	public int x, y; 
	public Timer timer; 
	public boolean isHit = false; // sets true when shot hits enemy (the player in this case) 
//	private int id; 
	private int dmg; 
	public boolean valid; 
	
	private PlayThread shipHitSound = new PlayThread(new SoundLoader("spaceshipWasHit.mp3"));
	
	
	@SuppressWarnings("static-access")
	public EnemyShot(int x, int y, int dmg, int speed, int id) {
//		this.id = id;
		this.dmg = dmg;
		this.x = x; 
		this.y = y;
		
		if(Board.enemies.enemies.size() == 0) {
			valid = false; 
		} else {
			valid = true; 
		}
		
		timer = new Timer(speed, this);
		timer.start();
	}
	
	public void move() {
		checkColission();
		if(!isHit) {
			x -= dmg;
		} else {
			x = 0; 
			timer.stop();
			
		}
		
		
	}

	private void checkColission() {
		if(Board.ves.alive && valid && x >= Board.ves.x && x <= (Board.ves.x+30) && y >= Board.ves.y-5 && y <= (Board.ves.y+10)) {
			System.out.println("$$$ --- VESSEL IS HIT! --- $$$");
			if(Board.ves.hp > 0) {
				Board.ves.hp -= 1;
				if(Board.ves.hp > 0) {
					shipHitSound = new PlayThread(new SoundLoader("spaceshipWasHit.mp3"));
					shipHitSound.start();
				} else {
					Board.ves.vesselDead();
					
				}
				
			} 
			x = -100;
			isHit = true; 
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x-5, y+8, 10, 2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("EnemyShot ");
		move();
		
	}
}
