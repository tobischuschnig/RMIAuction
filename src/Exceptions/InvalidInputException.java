package Exceptions;

/**
 * class InvalidInputException extends Exception 
 * thrown if the command !login was used with invalid arguments
 * @author PatrickPoelzlbauer
 * @version 09.02.2014 1.0
 */
public class InvalidInputException extends Exception{
	  
	   /**
		* Constructor of the class
		* sets the Exception message
		*/
		public InvalidInputException(){
			super("There are invalid arguments!");
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
