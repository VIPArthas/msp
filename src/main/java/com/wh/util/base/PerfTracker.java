package com.wh.util.base;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * 
 * performance tracker
 * 
 * @author Leo
 * 
 */
final public class PerfTracker {

	final private static String TAG = PerfTracker.class.getSimpleName();

	private Log logger;
	private long startTimer = 0;
	private long lastTimer = startTimer;

	public PerfTracker() {
		this.logger = null;
	}

	public PerfTracker(String tag) {
		this.logger = Log.getLogger(tag);
	}

	public PerfTracker(Object target) {
		this.logger = Log.getLogger(target);
	}

	public PerfTracker(Log logger) {
		this.logger = logger;
	}

	public void start(String message, Object... objects) {
		reset();
		log(lastTimer - startTimer, message, objects);
	}

	public void start() {
		start("start");
	}

	public long stop(String message, Object... objects) {
		lastTimer = Calendar.getInstance().getTimeInMillis();
		long ts = lastTimer - startTimer;
		log(ts, message, objects);
		reset();
		return ts;
	}

	public long stop() {
		return stop("stop");
	}

	public long track(String message, Object... objects) {
		long t = Calendar.getInstance().getTimeInMillis();
		long ts = t - lastTimer;
		log(ts, message, objects);
		lastTimer = t;
		return ts;
	}

	public long track() {
		return track("track");
	}

	public void reset() {
		startTimer = Calendar.getInstance().getTimeInMillis();
		lastTimer = startTimer;
	}

	private void log(long timeSpan, String message, Object... objects) {
		String str = null;
		if (timeSpan > 0) {
			str = MessageFormat.format("[{0}] {1}ms | ", TAG, timeSpan);
		} else {
			str = MessageFormat.format("[{0}] ", TAG);
		}
		if (logger != null)
			logger.info(str + message, objects);
		else
			System.out.println(str);
	}

}
