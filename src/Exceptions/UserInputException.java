package exceptions;

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
		super("The user information you entered is not correct! Please use an valid user account");
	}
	
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	public UserInputException(String message){
		super(message);
	}
}
