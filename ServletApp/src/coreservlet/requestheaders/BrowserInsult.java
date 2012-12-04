package coreservlet.requestheaders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreservlet.ServletUtilities;

/**
 * Servlet implementation class BrowserInsult
 */
public class BrowserInsult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowserInsult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String title = null;
		String message = null;
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		
		if (userAgent != null) {
			if (userAgent.contains("chromium")) {
				title = "Chromium";
				message = "Welcome to Chromium";
			} else if (userAgent.contains("chrome")) {
				title = "Chrome";
				message = "Welcome to Chrome";
			} else if (userAgent.contains("firefox")) {
				title = "Mozilla Firefox";
				message = "Welcome to Firefox";
			} else if (userAgent.contains("MSIE")) {
				title = "Microsoft IE";
				message = "Welcome to Microsoft IE";
			}
		} else {
			title = "No Value";
			message = "User-Agent: " + userAgent;
		}
		
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		
		out.println("<p class=\"center\">" + message + "</p>");
		out.println("<p class=\"center\"> (User Agent Inf: " + userAgent + ")</p>");
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./home.html"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
