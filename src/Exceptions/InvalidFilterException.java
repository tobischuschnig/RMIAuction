package Exceptions;

	/**
	 * class InvalidFilterException extends Exception 
	 * thrown if the param filter for the subscribe method in the class TaskExecuter contains wrong arguments
	 * @author PatrickPoelzlbauer
	 * @version 09.02.2014 1.0
	 */
public class InvalidFilterException extends Exception{
		  
	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	 public InvalidFilterException(){
		super("Thats not a valid argument for the Filter");
	 }
			
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	public InvalidFilterException(String message){
		super(message);
	}
}
