package coreservlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientAccessCounts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String countString = CookieUtilities.getCookieValue(request,
				"accessCount", "1");
		int count = 1;

		try {
			count = Integer.parseInt(countString);
		} catch (NumberFormatException nfe) {
		}

		LongLivedCookie c = new LongLivedCookie("accessCount",
				String.valueOf(count + 1));

		response.addCookie(c);
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Access Count Servlet";

		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));

		out.println("<H2>This is visit number <span class=\"btn btn-medium btn-primary disabled\">"
				+ count + "</span> by this browser.</H2>\n");

		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./home.html"));
	}

}
