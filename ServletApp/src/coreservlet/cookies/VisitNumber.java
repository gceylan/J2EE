package coreservlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	boolean newbie = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charsetUTF-8");
		
		out.println(ServletUtilities.getHeadWithCss(""));
		out.println(ServletUtilities.openMainDiv(""));
		
		String cookieName = "visitNum";
		
		if (newbie) {
			Cookie c = new Cookie(cookieName, "1");
			c.setMaxAge(60*60*24*7);
			response.addCookie(c);
			
			out.println("<div class=\"well\">");
			out.println("<h2>Welcome Aborth</h2>");
			out.println("</div>");
			
			newbie = false;
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie != null && cookie.getName().equals(cookieName)) {
						int visitNum = Integer.parseInt(cookie.getValue()) + 1;
						response.addCookie(new Cookie(cookieName, String.valueOf(visitNum)));
						out.println("<div class=\"well\">");
						out.println("<h2>Welcome Back</h2>");
						out.print("<p>Visited Count: " + visitNum + "</p>");
						out.println("</div>");
					}
				}
			} else
				newbie = true;	// kuki silindiyse bastan basla
		}
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./home.html"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
