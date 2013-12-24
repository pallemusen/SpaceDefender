package enemy;

import game.Board;
import game.Explosion;
import game.GameGUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import sound.PlayThread;
import sound.SoundLoader;
import main.ResourceLoader;

@SuppressWarnings("unused")
public class Enemy implements ActionListener {
	
	public int x;
	private int y; 
	
	private int id; 
	private int enemyType; 
	private int weaponType; 
	public int hp; 
	private int dmg; 
	private int points;
	private int shotCD;
	private int shotSpeed;
	
	public Timer movementTimer;
	public Timer shotCooldown;  
	public EnemyShotSystem weapons; 
	private Explosion explosion; 
	
	private ImageIcon enemyIcon; 
	
	public boolean alive = true;
	
	private PlayThread shipHitSound = new PlayThread(new SoundLoader("spaceshipWasHit.mp3"));
	
	//when enemy is initialized, it receives random y-coordinates
	public Enemy(int id, int enemyType, int weaponType, int hp, int dmg, int points, int shotCD, int shotSpeed, int movementSpeed) {
		this.id = id; 
		this.enemyType = enemyType;
		this.weaponType = weaponType;
		this.hp = hp; 
		this.dmg = dmg; 
		this.points = points;
		this.shotCD = shotCD;
		this.shotSpeed = shotSpeed;
		
		try {
			enemyIcon = new ImageIcon(ImageIO.read(ResourceLoader.load(enemyType+".png")));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		explosion = new Explosion();
		
		y = 10 + (int)(Math.random() * (290 - 10) + 1);
		x = 610;
		
		weapons = new EnemyShotSystem(id); 
		
		movementTimer = new Timer(movementSpeed, this);
		movementTimer.start();
		
		shotCooldownActionListener wepCD = new shotCooldownActionListener();
		shotCooldown = new Timer(shotCD, wepCD);
		shotCooldown.start();
	}
	
	// sees if shot hits enemy. gets called by the individual shot.
	/*
	 * shottypes:
	 * 0 = laser
	 * 1 = missile
	 */
	public boolean isEnemyHit(int shotX, int shotY, int shotDmg) {
		if(alive && shotY >= y-10 && shotY < y+25 && shotX >= x-30 && shotX < x) {
			if(hp > 0) {
				hp -= shotDmg;
				if(hp < 1) {
					enemyDead();
				} else {
					// if enemy survives shot, play 'ding'
					shipHitSound = new PlayThread(new SoundLoader("spaceshipWasHit.mp3"));
					shipHitSound.start();
				}
			} 
			return true; 
		} else {
			return false;
		}
	}
	
	public void enemyDead() {
		System.out.println("лллллллл ENEMY DEAD: "+id);
		alive = false; 
		shotCooldown.stop();
		explosion.triggerExplosion(x, y);
		explosion.playSound();
		Board.ves.score += points; 
		Board.spawner.deathCount++; 
		System.out.println("???? KILLS: "+Board.spawner.deathCount);
//		Board.spawner.isLvlOver();
		x = -120;
		
	}
	
	// paints enemy if it is to be painted
	public void paint(Graphics g) {
		if(alive) {
			g.drawImage(enemyIcon.getImage(), x, y, 50, 30, GameGUI.board);
		}
		
		explosion.paint(g);
		
		for(int i = 0; i < weapons.shots.size(); i++) {
			if(!weapons.shots.get(i).isHit) {
				weapons.shots.get(i).paint(g);
			}
			
			 
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Board.ves.alive && alive) {
			if((!(x > -10))&& (x > -80)) {
				//JOptionPane.showMessageDialog(null, "ID: "+id, "TITLE!", JOptionPane.PLAIN_MESSAGE);
				System.out.println("ллл ENEMY MADE IT PAST!" + id);
				alive = false; 
				Board.spawner.deathCount++;
				Board.ves.hp -= 1; 
			} else if(alive) {
				x -= 1; // moves enemy (make this vary by just a bit randomly)
			}
		} else {
			movementTimer.stop();
			shotCooldown.stop();
			System.out.println("Enemy dead - timers stopped...");
			alive = false; 
		}
	}
	
	// actionlistener for the timer that controls shot cooldown for enemy
	public class shotCooldownActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			weapons.enemyFire(x, y, dmg, shotSpeed);
			
			//System.out.println("ллл SKUD!");
		}
		
	}
	
}
