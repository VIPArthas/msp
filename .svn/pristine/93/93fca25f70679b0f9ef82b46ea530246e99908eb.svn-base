package com.wh.util.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


/**
 * the standard logging system
 * 
 * could either use log4j or Logging
 * 
 * @author Leo
 *
 */
final public class Log {
	private static Log log = Log.getLogger(Log.class);
	public enum Level {
		DEBUG(org.apache.log4j.Level.DEBUG),
		INFO(org.apache.log4j.Level.INFO),
		WARN(org.apache.log4j.Level.WARN),
		ERROR(org.apache.log4j.Level.ERROR);
		
		private Level(Priority p) {
			this.priority = p;
		}

		private Priority priority;
		
		public Priority getPriority() {
			return priority;
		}
	}
	
	private static final String FQCN = Log.class.getName();
	
	private Logger logger;
	
	private Log(String category) {
		logger = LogManager.getLogger(category);
	}
	
	public static Log getLogger(Object o) {
		String category = null;
		if (o instanceof String) {
			category = (String)o;
		} else if (o instanceof Class<?>) {
			category = ((Class<?>)o).getName();
		} else if (o!=null) {
			category = o.toString();
		}
		return new Log(category);
	}
	
	protected static String format(String format, Object...args) {
		return MessageFormat.format(format, args);
	}

	public void log(Level level, Object message, Throwable t) {
		logger.log(FQCN, level.getPriority(), message, t);
	}
	
	private PerfTracker perf = null;
	public PerfTracker perf() {
		if (perf==null)
			perf = new PerfTracker(this);
		return perf;
	}
	
	public void debug(Object message) {
		debug(message, null);
	}
	public void debug(String format, Object... messages) {
		debug(format(format, messages));
	}
	public void debug(Object message, Throwable t) {
		log(Level.DEBUG, message, t);
	}
	
	public void info(Object message) {
		info(message, null);
	}
//	public void info(Object message,String regex) {
//		info(message, null);
//	}
	public void info(String format, Object... messages) {
		info(format(format, messages));
	}
	public void info(Object message, Throwable t) {
		log(Level.INFO, message, t);
	}
	
	public void warn(Object message) {
		warn(message, null);
	}
	public void warn(String format, Object... messages) {
		warn(format(format, messages));
	}
	public void warn(Object message, Throwable t) {
		log(Level.WARN, message, t);
	}
	
	public void error(Object message) {
		error(message, null);
	}
	public void error(String format, Object... messages) {
		error(format(format, messages));
	}
	public void error(Object message, Throwable t) {
		log(Level.ERROR, message, t);
	}
	
	public void error(Exception ex){
		log(Level.ERROR,printExceptionStack(ex),null);
	}
	public void error(Exception ex,String message){
		log(Level.ERROR,printExceptionStack(ex,message),null);
	}
	
	private String printExceptionStack(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return String.format("threadId:%s,Excpetion:%s", Thread.currentThread().getId(),sw.toString());
	}
	
	public String printExceptionStack(Exception e,String message){
		return String.format("%s,%s",message,printExceptionStack(e));
	}
	
}
