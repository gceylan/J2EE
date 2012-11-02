package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LongServlet
 */
public class LongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out;
		
		if (GzipUtilities.isGzipSupported(request) && !GzipUtilities.isGzipDisabled(request)) {
			out = GzipUtilities.getGzipWriter(response);
			response.setHeader("Content-Encoding", "gzip");
		} else
			out = response.getWriter();
		
		response.setContentType("text/html");
		
		String title = "Long Servlet Result";
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		
		String line = "merhaba ben gceylan..." + "gzip hız testi";
		
		for (int i = 0; i < 100000; i++) {
			out.println(line);
		}
		
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