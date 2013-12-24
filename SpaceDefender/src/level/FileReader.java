package level;

import game.Board;

import java.util.ArrayList;
import java.util.Scanner;

import main.ResourceLoader;

public class FileReader {
	private Scanner s; 
	private String str; 
	private String fileName; 
	public ArrayList<EnemiesToBeSpawned> listOfEnemies = new ArrayList<EnemiesToBeSpawned>();
	
	private String[] temp; 
	private int enemyType; 
	private int spawnTime; 
	private int weaponType; 
	private int hp;
	private int dmg; 
	private int points;
	private int shotCD;
	private int shotSpeed;
	private int movementSpeed;
	
	
	/* INFO FOR .LVL-files!
	 * LVL parameters: 
	 * enemyType,spawnTime,weaponType,hp,dmg,points,shotCD 
	 * 
	 * spawntime is in seconds and tells when enemy should spawn 
	 * DO NOT USE SPACE IN LVL-FILE!!!
	 */
	
	public void loadLevel(int lvl) {
		fileName = lvl+".lvl";
		System.out.println(fileName);

		s = new Scanner(ResourceLoader.load(fileName));
		while(s.hasNext()) {
			str = s.nextLine();
			System.out.println(str);
			temp = str.split(",");
			
			enemyType = Integer.parseInt(temp[0]);
			spawnTime = Integer.parseInt(temp[1]);
			weaponType = Integer.parseInt(temp[2]);
			hp = Integer.parseInt(temp[3]);
			dmg = Integer.parseInt(temp[4]);
			points = Integer.parseInt(temp[5]);
			shotCD = Integer.parseInt(temp[6]);
			shotSpeed = Integer.parseInt(temp[7]);
			movementSpeed = Integer.parseInt(temp[8]);
			
			/*
			System.out.println("ENEMYTYPE: "+enemyType);
			System.out.println("SPAWNTIME: "+spawnTime);
			System.out.println("WEAPON: "+weaponType);
			System.out.println("HP: "+hp);
			System.out.println("DMG: "+dmg);
			System.out.println("POINTS: "+points);
			System.out.println("SHOTCD: "+shotCD);
			*/
			
			listOfEnemies.add(new EnemiesToBeSpawned(enemyType, spawnTime, weaponType, hp, dmg, points, shotCD, shotSpeed, movementSpeed));
		}
		
		Board.progress.active = true; 
	}
}
