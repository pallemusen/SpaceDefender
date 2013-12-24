package main;

import debug.LogFrame;
import menu.MenuFrame;


public class Main {
	public static MenuFrame menu;
	public static LogFrame log;
	
	public static void main(String[] args) {
		System.out.println("MAIN: running");
		
		//menu = new MenuFrame();
		new menu.LevelSelect();
		
		
		log = new LogFrame();
		
		//new game.GameGUI();
	}
}
