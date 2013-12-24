package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class StarSky implements ActionListener {
	
	private Timer timer = new Timer(50, this); 
	private ArrayList<Point> stars = new ArrayList<Point>();
	private Font deadFont = new Font("Helvetica", Font.BOLD, 80); 
	private Font subDeadFont = new Font("Helvetica", Font.ITALIC, 20);
	
	public StarSky() {
		stars.add(new Point(290, 210));
		stars.add(new Point(100, 100));
		stars.add(new Point(50, 300));
		stars.add(new Point(550, 350));
		stars.add(new Point(460, 50));
		stars.add(new Point(20, 140));
		stars.add(new Point(610, 40));
		stars.add(new Point(70, 230));
		stars.add(new Point(610, 305));
		stars.add(new Point(350, 180));
		stars.add(new Point(200, 350));
		stars.add(new Point(83, 200));
		stars.add(new Point(205, 70));
		stars.add(new Point(375, 105));
		stars.add(new Point(240, 50));
		stars.add(new Point(400, 350));
		stars.add(new Point(510, 300));
		stars.add(new Point(90, 125));
		stars.add(new Point(514, 265));
		stars.add(new Point(500, 200));
		
		
		
		timer.start();	// calls actionlistener periodically, which "this" is
	}
	
	// moves stars back 1 px, unless it has reached x <= 0
	public void moveStars() {
		for(int i = 0; i < stars.size(); i++) {
			if(stars.get(i).x > 0) {
				stars.get(i).x -= 3; 
			} else {
				stars.get(i).x = 610; 
			}
		}
	}
	
	public void paintEndMsg(Graphics g) {
		g.setFont(deadFont);
		g.setColor(Color.WHITE);
		g.drawString("Level Cleared", 50, 200);
	}
	
	@SuppressWarnings("static-access")
	public void paint(Graphics g) {
		//paints stars in white
		g.setColor(Color.WHITE);
		for(int i = 0; i < stars.size(); i++) {
			g.fillOval(stars.get(i).x, stars.get(i).y, 5, 5);
		}
		
		if(Board.lvlCleared) {
			paintEndMsg(g);
		}
		
		// this clears the board of enemies (and thereby their shots) and writes "game over..." when dead
		if(!Board.ves.alive) {
			//removes enemies and thereby their shots
			for(int i = 0; i < Board.enemies.enemies.size(); i++) {
				Board.enemies.enemies.get(i).alive = false; 
				Board.enemies.enemies.remove(i);
			}
			Board.enemies.enemies.clear();
			
			
			//removes vessel shots
			Board.ves.weapon.shots.clear();
			for(int i = 0; i < Board.ves.weapon.shots.size(); i++) {
				Board.ves.weapon.shots.remove(i);
			}
			
			//declares to the player that the game is over 
			g.setColor(Color.WHITE);
			g.setFont(deadFont);
			g.drawString("You Died!", 100, 200);
			g.setFont(subDeadFont);
			g.drawString("Much playing. Very spaceship. Wow", 130, 240);
		} else {
			
			//tests if player should be dead
			if(Board.ves.hp == 0) {
				System.out.println("setting enemies as dead");
				for(int i = 0; i < Board.enemies.enemies.size(); i++) {
					Board.enemies.enemies.get(i).alive = false; 
					Board.enemies.enemies.remove(i);
				}
				Board.enemies.enemies.clear();
				Board.ves.alive = false; 
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		moveStars();
		
	}
	
}
