package model;

import java.io.IOException;

/**
 * This Class gets a Path as parameter. this path directs to the specific 
 * properties file to check login data or something else
 * @author Tobias Lins
 * @email tlins@student.tgm.ac.at
 * @version 1.0
 */
public class Properties {
	String file;
	/**
	 * Sets the Path
	 * @param file	Path to the properties file
	 */
	public Properties(String file){
		this.file=file;
	}
	/**
	 * Get the property value by name
	 * @param name	value of the key
	 */
	public String getProperty(String name) {
		java.io.InputStream is = ClassLoader.getSystemResourceAsStream(this.file);
	
		if (is != null) {
			java.util.Properties props = new java.util.Properties();
			try {
				props.load(is);
				return props.getProperty(name);
			} catch (IOException e) {
				System.out.println("cant read file "+file);
				
			} finally {
				try {
					is.close();
				} catch (IOException e) {	
				}
			}
		} else {
			System.err.println("Properties file not found!");
		}
		return "";
	}
}
