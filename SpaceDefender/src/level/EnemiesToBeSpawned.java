package level;

public class EnemiesToBeSpawned {
	public int enemyType; 
	public int spawnTime;
	public int weaponType; 
	public int hp; 
	public int dmg; 
	public int points;
	public int shotCD;
	public int shotSpeed;
	public int movementSpeed;
	
	public EnemiesToBeSpawned(int enemyType, int spawnTime, int weaponType, int hp, int dmg, int points, int shotCD, int shotSpeed, int movementSpeed) {
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		this.weaponType = weaponType;
		this.hp = hp; 
		this.dmg = dmg; 
		this.points = points;
		this.shotCD = shotCD;
		this.shotSpeed = shotSpeed;
		this.movementSpeed = movementSpeed;
	}
}
