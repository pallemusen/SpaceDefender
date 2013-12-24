package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import player.Vessel;
import sound.PlayThread;
import sound.SoundLoader;
import enemy.EnemyArray;
import level.EndLevel;
import level.EnemySpawner;
import level.Progress;
import main.Main;

public class Board extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	public static Progress progress;
	public static EndLevel end = new EndLevel();
	public static EnemySpawner spawner; 
	public static int currentLvl = 1; 
	public static Vessel ves;
	private Timer timer;
	public static StarSky starsky = new StarSky();
	public static ExplosionImageLoader explosionImages = new ExplosionImageLoader(); 
	public static boolean lvlCleared = false;
	public static boolean drawVessel = true;
	private boolean debugOn = false;
	private boolean startOfLvl = true; 
	private boolean isGameSet = false; 
	
	private int test = 1; 
	private int test2 = 1; 
	
	public static EnemyArray enemies = new EnemyArray();
	private PlayThread prepare = new PlayThread(new SoundLoader("prepare-to-fight.mp3"));
	
	public Board(Vessel ves) {
		Board.ves = ves; 
	}
	
	public void startGame() {
		if(!isGameSet) {
			addKeyListener(this);
			setBackground(Color.BLACK);
			progress = new Progress();
			spawner = new EnemySpawner();
			timer = new Timer(50, this);
			isGameSet = true; 
		} else {
			System.out.println("GAME ALREADY SET!");
		}
	}
	
	@SuppressWarnings("static-access")
	public void stopGame() {
		Main.log.logMsg("STOP GAME");
		System.out.println("Game stopping...");
		timer.stop();
		progress.active = false; 
		
		for(int i = 0; i < enemies.enemies.size(); i++) {
			enemies.enemies.get(i).alive = false;
			enemies.enemies.get(i).x = -100;
			enemies.enemies.get(i).shotCooldown.stop();
			enemies.enemies.get(i).movementTimer.stop();
			enemies.enemies.get(i).weapons.shots.clear();
		}
		
		ves.scoreTimer.stop();
		spawner.spawnerTimer.stop();
		
		enemies.enemies.clear();
		spawner.lvl.listOfEnemies.clear();
		spawner.lvl.loadLevel(currentLvl);
		
		ves.weapon.shots.clear();
		
	}
	
	@SuppressWarnings("static-access")
	public void restartGame() {
		Main.log.logMsg("Restart #"+test);
		System.out.println("Restart #"+test);
		test++;
		
		removeKeyListener(this);
		
		timer.stop();
		
		Main.log.logMsg("Game is restarting...");
		Board.enemies.id = 0;
		
		for(int i = 0; i < enemies.enemies.size(); i++) {
			enemies.enemies.get(i).enemyDead();
			enemies.enemies.get(i).x = -100;
			/*for(int x = 0; x < enemies.enemies.get(i).weapons.shots.size(); x++) {
				enemies.enemies.get(i).weapons.shots.get(x).x = -100;  
				enemies.enemies.get(i).weapons.shots.get(x).timer.stop();
				enemies.enemies.get(i).weapons.shots.clear(); 
			}
			*/
			enemies.enemies.get(i).shotCooldown.stop();
			enemies.enemies.get(i).movementTimer.stop();
			enemies.enemies.get(i).weapons.shots.clear();
			
		}
		
		enemies.enemies.clear();
		spawner.lvl.listOfEnemies.clear();
		spawner.lvl.loadLevel(currentLvl);
		
		end.soundPlayed = false; 
		end.sound2Played = false;
		
		progress.listLoaded = false; 
		progress.active = false; 
		drawVessel = true; 
		lvlCleared = false;
		startOfLvl = true; 
		ves.resetPos();
		ves.hp = 3; 
		ves.score = 0; 
		spawner.time = 0; 
		
		ves.weapon.shots.clear();
		
		ves.alive = true; 
		addKeyListener(this);
		spawner.deathCount = 0; 
		ves.triggered = false; 
		progress.active = true;
		spawner.endGame = false; 
		spawner.spawnerTimer.start();
		end.accelerationInt = 0; 
		end.backStepsRemaining = 25;
		timer.start();
		ves.scoreTimer.start();
		
		System.out.println("Enemies: " + enemies.enemies.size());
		System.out.println("Shots: " + ves.weapon.shots.size());
		
		addKeyListener(this);
		Main.log.logMsg("Game RESTARTED!");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		starsky.paint(g);
		enemies.paintEnemies(g);
		if(drawVessel) {
			ves.paint(g);
		}
		ves.paintVesStuff(g);
		progress.paint(g);
		ves.weapon.paintShots(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			ves.dy = -10;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			ves.dy = 10; 
		}
		
		if(key == KeyEvent.VK_LEFT) {
			ves.dx = -10;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			ves.dx = 10; 
		}
		
		if(key == KeyEvent.VK_SPACE) {
			ves.weapon.fire(0);
			
		}
		
		if(key == KeyEvent.VK_ENTER) {
			ves.weapon.fire(1);
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			ves.weapon.fire(1);
		}
		
		if(key == KeyEvent.VK_O) {
			removeKeyListener(this);
			debugOn = !debugOn;
			Main.log.setVisible(debugOn);
			addKeyListener(this);
		}
		
		if(key == KeyEvent.VK_P) {
			removeKeyListener(this);
			Main.log.logMsg("P pressed #"+test2);
			test2++;
			restartGame();
		}
		
		if(key == KeyEvent.VK_Q) {
			/*System.out.println("### ENEMY SPAWNED ###");
			enemies.newEnemy();*/
		}
		
		//key prints vessel-coordinates
		if(key == KeyEvent.VK_K) {
			System.out.println("Vessel X: " + ves.x);
			System.out.println("Vessel Y: " + ves.y);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			ves.dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			ves.dy = 0; 
		}
		
		if(key == KeyEvent.VK_LEFT) {
			ves.dx = 0; 
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			ves.dx = 0; 
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(startOfLvl) {
			startOfLvl = false;
			prepare = new PlayThread(new SoundLoader("prepare-to-fight.mp3"));
			prepare.start();
		}
		ves.move();
		repaint();
	}
	
}
