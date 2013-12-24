package level;

import game.Board;

import java.awt.Color;
import java.awt.Graphics;


public class Progress { 
	private int i;
	private String tempStr1, tempStr2;
	private double tempDouble1, tempDouble2;
	private int onePct = 2;
	public boolean active = false; 
	public boolean listLoaded = false; 
	private int progress = 0; 
	
	public int determineProgress() {
		if(active) {
			i = Board.spawner.lvl.listOfEnemies.size()-1;
			if(!listLoaded) {
				tempStr2 = ("" + Board.spawner.lvl.listOfEnemies.get(i).spawnTime);
				listLoaded = true;
				System.out.println("Last spawn: "+tempStr2);
			}
			tempStr1 = ""+Board.spawner.time; 
			tempDouble1 = Double.parseDouble(tempStr1);
			
			tempDouble2 = Double.parseDouble(tempStr2);
			
			if(tempDouble2 == tempDouble1) {
				return 100; 
			} else {
				int tempProg = ((int) ((tempDouble1/tempDouble2)*100));
				if(tempProg <= 100) {
					progress = tempProg;
					return progress;
				} else {
					progress = 100;
				}
				return progress;
				
			}
		} else {
			return 0; 
		}
		
	}
	
	/*
	 * skal progress beregnes ud fra antal enemies i stedet? 
	 */
	
	// calculates based on progress, how many out of 200 px should be filled (length of filling rect)
	private int calcPixels() {
		int res = (onePct * determineProgress());
		if(res <= 200) {
			return res;
		} else {
			return 200; 
		}
	}
	
	//paints progress bar
	public void paint(Graphics g) {
		g.setFont(Board.ves.hpFont);
		g.setColor(Color.WHITE);
		g.drawString("Level "+Board.currentLvl+": "+ progress +" %", 380, 330);
		g.drawRect(380, 340, 201, 14);
		
		g.setColor(Color.CYAN);
		g.fillRect(381, 341, calcPixels(), 13);
	}
}
