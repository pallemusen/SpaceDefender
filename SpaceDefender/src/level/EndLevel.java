package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import sound.PlayThread;
import sound.SoundLoader;
import game.Board;
import game.GameGUI;

public class EndLevel implements ActionListener {
	private Timer animTimer = new Timer(20, this);
	public int accelerationInt = 0;
	public int backStepsRemaining = 25; 
	private PlayThread victorySound = new PlayThread(new SoundLoader("victory.mp3"));
	private PlayThread leavingSound = new PlayThread(new SoundLoader("leaving_map.mp3"));
	public boolean soundPlayed = false;
	public boolean sound2Played = false; 
	
	// ends the level
	public void endTheLevel() {
		Board.lvlCleared = true;
		Board.ves.scoreTimer.stop();
		Board.ves.dx = 0; 
		Board.ves.dy = 0; 
		clearArrays();
		endAnimation();
	}
	
	//makes vessel leave map
	private void endAnimation() {
		animTimer.start();
	}
	
	@SuppressWarnings("static-access")
	private void clearArrays() {
		// sets shots invalid so that they aren't moving anymore
		for(int i = 0; i < Board.enemies.enemies.size(); i++) {
			for(int x = 0; x < Board.enemies.enemies.get(i).weapons.shots.size(); x++) {
				Board.enemies.enemies.get(i).weapons.shots.get(x).valid = false;
				Board.enemies.enemies.get(i).shotCooldown.stop();
			}
		}
		Board.enemies.enemies.clear();
		Board.ves.weapon.shots.clear();
		
	}

	/*
	 * anim-start-coordinates: (250-210; 240)
	 * ^^make method to move vessel to these coordinates
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!soundPlayed) {
			soundPlayed = true; 
			victorySound = new PlayThread(new SoundLoader("victory.mp3"));
			victorySound.start();
		}
		if(Board.spawner.time - Board.spawner.tempTime > 5) { 
			//moves backwards first, and then forward (overwrites 'move()')
			if(backStepsRemaining > 0) {
				Board.ves.x -= 1;
				backStepsRemaining -= 1;
			} else {
				if(Board.ves.x < 700) {
					if(!sound2Played && accelerationInt > 5) {
						sound2Played = true; 
						leavingSound = new PlayThread(new SoundLoader("leaving_map.mp3"));
						leavingSound.start();
					}
					Board.ves.x += accelerationInt;
					accelerationInt += 1; 
				} else {
					// stops timers used by animation
					
					Board.drawVessel = false; 
					animTimer.stop();
					GameGUI.board.addKeyListener(GameGUI.board);
					
					//change level
					if(Board.spawner.time - Board.spawner.tempTime > 10) {
						Board.spawner.spawnerTimer.stop();
						Board.currentLvl += 1; 
						GameGUI.board.restartGame();
					}
				}
			}
		}
	}
}
