package Exceptions;

import java.rmi.RemoteException;

/**
 * class OverlappedPricestepException extends RemoteException 
 * thrown on the billingserver if 2 pricesteps are overlapping
 * @author PatrickPoelzlbauer
 * @version 20.02.2014 1.0
 */
public class OverlappedPricestepException extends RemoteException{

	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	OverlappedPricestepException(){
		super("Two pricesteps are overlapping.");
	}
	
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	OverlappedPricestepException(String message){
		super(message);
	}
}