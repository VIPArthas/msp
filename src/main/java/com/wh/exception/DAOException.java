package com.wh.exception;
/**
 * DAO层出现异常时，通过此异常类进行异常抛出
 * @author Administrator
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7951174920316808764L;

	public DAOException() {  
        super();  
    }  
  
    public DAOException(String msg) {  
        super(msg);  
    }  
  
    public DAOException(Throwable e) {  
        super(e);  
    }  
  
    public DAOException(String msg, Throwable e) {  
        super(msg, e);  
    }  
}

