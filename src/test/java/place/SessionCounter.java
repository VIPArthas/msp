package place;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;

public class SessionCounter implements HttpSessionListener {
	private static int activeSessions = 0;

	/* Session创建事件 */
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(1);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count + 1);
		}
		ctx.setAttribute("numSessions", numSessions);
	}

	/* Session失效事件 */
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(0);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count - 1);
		}
		ctx.setAttribute("numSessions", numSessions);

	}
}