package game;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.ResourceLoader;

public class ExplosionImageLoader {
	private ImageIcon f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12;
	public static ArrayList<ImageIcon> frames = new ArrayList<ImageIcon>();
	
	public ExplosionImageLoader() {
		loadIcons();
	}
	
	public ArrayList<ImageIcon> getFrames() {
		return frames; 
	}
	
	public void loadIcons() {
		try {
			f1 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp1.png")));
			f2 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp2.png")));
			f3 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp3.png")));
			f4 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp4.png")));
			f5 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp5.png")));
			f6 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp6.png")));
			f7 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp7.png")));
			f8 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp8.png")));
			f9 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp9.png")));
			f10 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp10.png")));
			f11 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp11.png")));
			f12 = new ImageIcon(ImageIO.read(ResourceLoader.load("exp12.png")));
			System.out.println("***IMAGES LOADED!");
		} catch(IOException e) {
			e.printStackTrace();
		}
		frames.add(f1);
		frames.add(f2);
		frames.add(f3);
		frames.add(f4);
		frames.add(f5);
		frames.add(f6);
		frames.add(f7);
		frames.add(f8);
		frames.add(f9);
		frames.add(f10);
		frames.add(f11);
		frames.add(f12);
	}
}
