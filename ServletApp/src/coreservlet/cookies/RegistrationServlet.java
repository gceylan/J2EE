package coreservlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		boolean isMissingValue = false;
		String redirectionURL = "./registration-form";
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		if (isMissing(firstName)) {
			firstName = "First Name is Missing";
			isMissingValue = true;
		}
		if (isMissing(lastName)) {
			lastName = "Last Name is Missing";
			isMissingValue = true;
		}
		if (isMissing(email)) {
			email = "email is Missing";
			isMissingValue = true;
		}
		
		Cookie c1 = new LongLivedCookie("fistName", firstName);
		Cookie c2 = new LongLivedCookie("lastName", lastName);
		Cookie c3 = new LongLivedCookie("email", email);
		
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
		if (isMissingValue)
			response.sendRedirect(redirectionURL);
		
		out.println(ServletUtilities.getHeadWithCss("cookies"));
		out.println(ServletUtilities.openMainDiv("Registration Servlet Result"));
		
		out.println("<table class=\"table table-hover\">");
		out.println("<tr><th>#</th><th>Parameter Name</th><th>Parameter Value</th></tr>");
		Enumeration<String> parameterNames = request.getParameterNames();
		int i = 0;
		
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String[] parameterValues = request.getParameterValues(parameterName);
			out.println("<tr>");
			out.print("<td>" + ++i + "</td>");
			if (parameterValues.length == 1) {
				String parameterValue = parameterValues[0];
				
				if (parameterValue.trim().equals("")) {
					out.println("<td>" + parameterName + "</td><td><i>No Value</i></td>");
				} else {
					out.println("<td>" + parameterName + "</td><td>" + parameterValue + "</td>");
				}
			} else {
				for (String value : parameterValues) {
					out.println("<li>" + value + "</li>");
				}
			}
			out.println("</tr>");
		}
		out.println("</table>");
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter(redirectionURL));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean isMissing(String parameter) {
		return (parameter == null || parameter.trim().equals(""));
	}

}
