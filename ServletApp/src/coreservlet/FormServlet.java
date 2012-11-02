package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println(ServletUtilities.getHeadWithCss("Servlet Result"));
		
		out.println(ServletUtilities.openMainDiv("Servlet Form Result"));
		
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
		
		String refering = request.getHeader("referer");
		out.println("<p>Referring page: <b><i>" + refering + "</i></b></p>");
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./form.html"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
