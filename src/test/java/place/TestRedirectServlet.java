package place;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestRedirect
 */
@WebServlet("/TestRedirectServlet")
public class TestRedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //重定向前循环sleep五秒
        for (int i = 0; i < 5; i++) {
            System.out.println("before redirect:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //重定向
        response.sendRedirect("result.jsp");

        //重定向后循环sleep五秒
        for (int i = 0; i < 5; i++) {
            System.out.println("after redirect:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}