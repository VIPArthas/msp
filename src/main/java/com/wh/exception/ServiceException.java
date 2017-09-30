
package com.wh.exception;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6809646337658833063L;

	public ServiceException() {  
        super();  
    }  
  
    public ServiceException(String msg) {  
        super(msg);  
    }  
  
    public ServiceException(Throwable e) {  
        super(e);  
    }  
	
	public ServiceException(String message,Throwable cause){
		super(message,cause);
	}
}
