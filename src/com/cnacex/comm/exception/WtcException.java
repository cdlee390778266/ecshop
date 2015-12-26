/**
 *@author:Administrator 
 *
 *@todo:TODO
 *
 *@date: 2015-6-2 下午4:00:21
 */
package com.cnacex.comm.exception;

import weblogic.wtc.jatmi.TPException;

/**
 * @author Administrator
 *
 */
public class WtcException extends TPException{
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 
	 * @param itperrno
	 * @param message
	 */
	public WtcException(int itperrno, String message) {
		super(itperrno, message);
	}
	

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public WtcException(String message, Throwable cause) {
		super();
	}

	
	
	/**
	 * @param message
	 */
	public WtcException(String message) {
		super();
	}

}
