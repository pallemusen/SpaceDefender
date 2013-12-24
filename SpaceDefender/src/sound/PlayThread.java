package sound;

import javazoom.jl.decoder.JavaLayerException;

public class PlayThread extends Thread {
	private SoundLoader sound;
	
	public PlayThread(SoundLoader sound) {
		this.sound = sound; 
	}
	
	@Override
	public void run() {
		try {
			sound.player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
