package level;

import game.Board;
import game.GameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class EnemySpawner implements ActionListener{

	public Timer spawnerTimer; 
	public int time = 0; //game time in seconds
	public FileReader lvl = new FileReader(); 
	public int deathCount = 0; 
	public boolean endGame = false;
	public int tempTime; 
	
	//temp vars
	public int enemyType; 
	public int weaponType; 
	public int hp; 
	public int dmg; 
	public int points;
	public int shotCD; 
	public int shotSpeed;
	public int movementSpeed;
	
	public EnemySpawner() {
		spawnerTimer = new Timer(1000, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		time += 1;
		Board.spawner.isLvlOver();
		System.out.println("TIME: " + time);
		for(int i = 0; i < lvl.listOfEnemies.size(); i++) {
			if(lvl.listOfEnemies.get(i).spawnTime == time) {
				System.out.println("ENEMY SHOULD SPAWN!");
				
				//sets temp vars
				enemyType = lvl.listOfEnemies.get(i).enemyType;
				weaponType = lvl.listOfEnemies.get(i).weaponType;
				hp = lvl.listOfEnemies.get(i).hp;
				dmg = lvl.listOfEnemies.get(i).dmg; 
				points = lvl.listOfEnemies.get(i).points;
				shotCD = lvl.listOfEnemies.get(i).shotCD;
				shotSpeed = lvl.listOfEnemies.get(i).shotSpeed;
				movementSpeed = lvl.listOfEnemies.get(i).movementSpeed;
				
				//feeds enemy info to 'newEnemy' method in EnemyArray class
				Board.enemies.newEnemy(enemyType, weaponType, hp, dmg, points, shotCD, shotSpeed, movementSpeed);
			}
		}
		if(endGame) {
			System.out.println("ENDGAME COMMENCING");
			lvl.listOfEnemies.clear();
			//gives artistic delay on showing the "LVL CLEARED" msg
			System.out.println("TIME; TEMP = " +time+"; "+tempTime);
			if(time - tempTime > 1) {
				GameGUI.board.removeKeyListener(GameGUI.board);
				Board.end.endTheLevel();
			}
		}
	}
	
	//this compares kill amount to amount of enemies in the lvl-file
	public void isLvlOver() {
		System.out.println("LEN: "+lvl.listOfEnemies.size());
		System.out.println("KILLS: "+deathCount);
		if(deathCount == (lvl.listOfEnemies.size())) {
			System.out.println("############ All enemies are dead :)");
			tempTime = time;
			endGame = true; 
			
		}
	}
	
}
