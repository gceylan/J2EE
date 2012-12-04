package coreservlet.responseheaders;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchEngine
 */
public class SearchEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEngine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		if (searchString == null || searchString.trim().equals("")) {
			reportProblem(response, "Lütfen aranacak bir ifade girin!!");
			return;
		}
		searchString = URLEncoder.encode(searchString);
		String searchEngineName = request.getParameter("searchEngine");
		if (searchEngineName == null) {
			reportProblem(response, "Lütfen arama motorunu seçin!!");
			return;
		}
		String searchURL = SearchUtilities.makeURL(searchEngineName, searchString);
		if (searchURL != null)
			response.sendRedirect(searchURL);
		else
			reportProblem(response, "Arama gerçekleştirilmedi!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void reportProblem(HttpServletResponse response, String message) throws IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
	}

}
