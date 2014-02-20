package Exceptions;

import java.rmi.RemoteException;

/**
 * class EndpriceException extends RemoteException 
 * thrown if the startprice of a auction is higher as the endprice
 * @author PatrickPoelzlbauer
 * @version 20.02.2014 1.0
 */
public class EndpriceException extends RemoteException{

	/**
	 * Constructor of the class
	 * sets the Exception message
	 */
	EndpriceException(){
		super("the startprice of a auction is higher as the endprice.");
	}
	
	/**
	 * Second Constructor of the class
	 * sets the the param as Message
	 * @param message The Message that should be shown
	 */
	EndpriceException(String message){
		super(message);
	}
}
