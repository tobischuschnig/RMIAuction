package Exceptions;

import java.rmi.RemoteException;

/**
 * class InvalidLoginException extends RemoteException 
 * thrown if the entered userinformation isn't correct 
 * @author PatrickPoelzlbauer
 * @version 20.02.2014 1.0
 */
public class InvalidLoginException extends RemoteException{

	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	InvalidLoginException(){
		super("The user information you entered is not correct! Please use an valid user account.");
	}
	
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	InvalidLoginException(String message){
		super(message);
	}
}
