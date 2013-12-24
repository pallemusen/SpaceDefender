package sound;

import main.ResourceLoader;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class SoundLoader {
	public AdvancedPlayer player;
	
	public SoundLoader(String path) {
		try {
			player = new AdvancedPlayer(ResourceLoader.load(path));
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
