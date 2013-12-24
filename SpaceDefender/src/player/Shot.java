package player;

import game.Board;
import game.GameGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Shot implements ActionListener {
	private int x, y, dmg, shotType; 
	private Timer timer; 
	private boolean isHit = false; // sets true when shot hits enemy
	
	public Shot(int x, int y, int dmg, int speed, int shotType) {
		this.x = x; 
		this.y = y;
		this.dmg = dmg; 
		this.shotType = shotType;
		
		System.out.println("SHOT FIRED! DMG = "+dmg);
		timer = new Timer(speed, this);
		timer.start();
	}
	
	public void move() {
		if(x < 600 && !isHit) {
			x += 1;
		} else {
			x = 650; 
			timer.stop();
		}
		
		
	}

	public void paint(Graphics g) {
		if(shotType == 0) {
			g.setColor(Color.RED);
			g.fillRect(x+30, y+8, 10,2);
		} else if(shotType == 1) {
			g.drawImage(Board.ves.weapon.missileIcon.getImage(), x+15, y+10, 20, 5, GameGUI.board);
		}
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	//every time shot moves, it tests to see if it has same coordinates as any enemy. If so, it disappears
	public void actionPerformed(ActionEvent e) {
		move();
		
		for(int i = 0; i < Board.enemies.enemies.size(); i++) {
			if(Board.enemies.enemies.get(i).isEnemyHit(x, y, dmg)) {
				x = 610;
			}
		}
	}
}
