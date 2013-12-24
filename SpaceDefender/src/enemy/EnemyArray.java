package enemy;

import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyArray {
	
	public static ArrayList<Enemy> enemies; 
	public int id = 0; 
	
	public EnemyArray() {
		enemies = new ArrayList<Enemy>();
	}
	
	public void newEnemy(int enemyType, int weaponType, int hp, int dmg, int points, int shotCD, int shotSpeed, int movementSpeed) {
		enemies.add(new Enemy(id, enemyType, weaponType, hp, dmg, points, shotCD, shotSpeed, movementSpeed));
		id++; 
	}
	
	public void paintEnemies(Graphics g) {
		for(int i = 0; i < enemies.size(); i++) {
			//System.out.println("Enemies are being painted.");
			enemies.get(i).paint(g);
		}
	}
}
