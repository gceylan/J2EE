package coreservlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String title;
		
		if (request.getParameter("showCookies") != null) {
			title = "Showing All Cookies in this Server";
			out.println(ServletUtilities.getHeadWithCss(title));
			out.println(ServletUtilities.openMainDiv(title));
			out.println("<hr>");
			
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie != null) {
						out.println("<p>Firt Name: " + cookie.getName() + "<br>");
						out.println("Password: " + cookie.getValue() + "</p>");
					}
				}
			} else
				out.println("<p><i>No Cookie!!!</i></p>");
		} else {
			title = "";
			out.println(ServletUtilities.getHeadWithCss(title));
			out.println(ServletUtilities.openMainDiv(title));
			
			String firstName = request.getParameter("firstName");
			String passwd = request.getParameter("passwd");

			if (firstName != null && passwd != null) {
				if (firstName.equals("") || passwd.equals("")) {
					response.sendError(response.SC_NO_CONTENT, "Do not leave fields blank!!!");
					return;
				}
				Cookie c = new Cookie(firstName, passwd);
				c.setMaxAge(60*60*24*7);	// a week
				response.addCookie(c);

				out.println("<h2>Cookie added.</h2>");
			}
		}
		
		out.println(ServletUtilities.closeMainDiv());
		ServletUtilities.getFooter("./cookie_form.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
