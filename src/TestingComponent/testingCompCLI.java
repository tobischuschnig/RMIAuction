package testingComponent;

import Client.UI;

/**
 * This class is basically the same as CLI in the package Client. It saves the commung output into a 
 * String to hold it and get it with getter Method.
 * 
 * @author Alexander Klune <aklune@student.tgm.ac.at>
 * @version 1.0
 */
public class TestingCompCLI implements UI{

	private String output;
	@Override
	public void out(String output) {
		this.output = output;
		System.out.println(output);
	}
	@Override
	public void outln(String output){
		this.output = output;
		System.out.print(output);
	}
	
	public String getOutput(){
		return this.output;
	}
}
