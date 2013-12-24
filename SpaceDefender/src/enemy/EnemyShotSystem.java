package enemy;

import java.awt.Graphics;
import java.util.ArrayList;

public class EnemyShotSystem  {

	public ArrayList<EnemyShot> shots;  
	private int id; 
	
	public EnemyShotSystem(int id) {
		this.id = id;  
		shots = new ArrayList<EnemyShot>();
	}

	public void enemyFire(int x, int y, int dmg, int speed) {
		System.out.println(" --- Shot fired... ---");
		shots.add(new EnemyShot(x, y, dmg, speed, id));
		System.out.println("Shot added to array.");
		
		for(int i = 0; i < shots.size(); i++) {
			System.out.println("Shots: "+i);
		}
		
	}
	
	public void paint(Graphics g) {
		for(int i = 0; i < shots.size(); i++) {
			shots.get(i).paint(g);
		}
	}
	
}
