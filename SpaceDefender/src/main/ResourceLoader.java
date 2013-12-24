package main;

import java.io.InputStream;

final public class ResourceLoader {
	
	/*
	 * this method is handed a file path. 
	 * It finds the file specified and returns it as an inputstream. 
	 * it also adds a forward slash to the path if the file isn't found.
	 */
	
	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if( input == null ) {
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}
		return input; 
	}
}
