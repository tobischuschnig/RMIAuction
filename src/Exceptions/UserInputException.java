package Exceptions;

/**
 * class UserInputException extends Exception 
 * thrown if a command except the !login command was used with invalid arguments
 * @author PatrickPoelzlbauer
 * @version 09.02.2014 1.0
 */
public class UserInputException extends Exception{
	
	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	public UserInputException(){
		super("There are invalid arguments!");
	}
}
