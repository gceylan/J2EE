package coreservlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationForm
 */
public class RegistrationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String title = "Registation Servlet";
		String actionURL = "./registration-servlet";
		
		String firstName = CookieUtilities.getCookieValue(request, "firstName", "");
		String lastName = CookieUtilities.getCookieValue(request, "lastName", "");
		String email = CookieUtilities.getCookieValue(request, "email", "");
		
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		out.println(ServletUtilities.openForm("post", actionURL));
		
		out.println("<p>First Name: <input type=\"text\" name=\"firstName\" value=\"" + firstName + "\"></p>");
		out.println("<p>Last Name: <input type=\"text\" name=\"lastName\" value=\"" + lastName + "\"></p>");
		out.println("<p>e-mail: <input type=\"text\" name=\"email\" value=\"" + email + "\"></p>");
		out.println("<p><input class=\"btn btn-primary\" type=\"submit\">");
		
		out.println(ServletUtilities.closeForm());
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./home.html"));
		
	}

}
