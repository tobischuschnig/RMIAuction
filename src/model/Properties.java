package model;

import java.io.IOException;

public class Properties {
	String file;
	public Properties(String file){
		this.file=file;
	}
	/**
	 * Get the property value by name
	 * @param name
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
