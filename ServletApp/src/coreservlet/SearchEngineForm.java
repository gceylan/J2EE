package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchEngineForm
 */
public class SearchEngineForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEngineForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Web Search Engine";
		
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		out.println(ServletUtilities.openForm("post", "./search-engine"));
		
		out.println("<p>Search keywords: <input type=\"text\" name=\"seachString\"></p>");
		
		SearchSpec[] searchSpecs = SearchUtilities.getCommonSpecs();
		for (SearchSpec searchSpec : searchSpecs) {
			out.println("<input type=\"radio\" " +
								"name = \"searchEngine\" " +
								"value = \"" + searchSpec.getName() + "\">"
			);
			out.println(searchSpec.getName() + "<br>");
		}
		
		out.println("<p><br><input type=\"submit\" value=\"Search\" class=\"btn\"></p>");
		
		out.println(ServletUtilities.closeForm());
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
