package Exceptions;

import java.rmi.RemoteException;

/**
 * class EndpriceException extends RemoteException 
 * thrown if the value of a parameter is invalid
 * @author PatrickPoelzlbauer
 * @version 20.02.2014 1.0
 */
public class InvalidParameterException extends RemoteException{

	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	InvalidParameterException(){
		super("The value of a parameter is invalid.");
	}
	
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	InvalidParameterException(String message){
		super(message);
	}
	
}
