package Exceptions;

/**
 * class InvalidInputException extends Exception 
 * thrown if a command except the !login command was used with invalid arguments
 * @author PatrickPoelzlbauer
 * @version 09.02.2014 1.0
 */
public class InvalidInputException extends Exception{
	  
	   /**
		* Constructor of the class
		* sets the Exception message
		*/
		public InvalidInputException(){
			super("The command was used with invalid arguments or a wrong syntax.");
		}
		
		/**
		 * Second Constructor of the class
		 * sets the the param as Message
		 * @param message The Message that should be shown
		 */
		public InvalidInputException(String message){
			super(message);
		}
}
