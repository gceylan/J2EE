package coreservlet.sessiontracking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreservlet.cookies.ServletUtilities;

/**
 * Servlet implementation class ShowSession
 */
public class ShowSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "Session Tracking Example";

		HttpSession session = request.getSession();
		String heading;
		Integer accessCount = (Integer) session.getAttribute("accessCount");
		if (accessCount == null) {
			accessCount = new Integer(1);
			heading = "Welcome, Newcomer";
		} else {
			accessCount += 1;
			heading = "Welcome Back";
		}
		session.setAttribute("accessCount", accessCount);

		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));

		out.println("<p>Information on Your Session:</p>");

		out.println("<table class=\"table table-hovered\">");
		out.println("<tr><th>Information Type</th><th>Value</th></tr>");
		out.println("<tr><td>ID</td><td>" + session.getId() + "</td></tr>");
		out.println("<tr><td>Creation Time</td><td>"
				+ new Date(session.getCreationTime()) + "</td></tr>");
		out.println("<tr><td>Time of Last Access</td><td>"
				+ new Date(session.getLastAccessedTime()) + "</td></tr>");
		out.println("<tr><td>Number of Previous Access</td><td>"
				+ session.getAttribute("accessCount") + "</td></tr>"); // accessCount
		out.println("</table>");

		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./home.html"));
	}

}
