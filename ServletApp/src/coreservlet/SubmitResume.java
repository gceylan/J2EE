package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmitResume
 */
public class SubmitResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitResume() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		if (request.getParameter("previewButton") != null) {
			showPreview(request, out);
		} else {
			showConfirmation(request, out);
		}
	}

	private void showConfirmation(HttpServletRequest request, PrintWriter out) {
		String title = "Submissin Confirmed.";
		
		out.println(ServletUtilities.getHeadWithCss(title));
		
		out.println(ServletUtilities.openMainDiv(title));
		
		out.println("<p class=\"center\">Your resume should appear online within\n" +
				"24 hours. If it doesn't, try submitting\n" +
				"again with a different email address.</p>\n");
		
		out.println(ServletUtilities.closeMainDiv());
		
		out.println(ServletUtilities.getFooter("./submit_resume.html"));
	}

	private void showPreview(HttpServletRequest request, PrintWriter out) {
		String headingFont = request.getParameter("headingFont");
		headingFont = replaceIfMissing(headingFont, "");
		
		String textColor = request.getParameter("textColor");
		textColor = replaceIfMissingOrDefault(textColor, "BLACK");
		
		String fgColor = request.getParameter("fgColor");
		fgColor = replaceIfMissing(fgColor, "BLACK");
		
		String bgColor = request.getParameter("bgColor");
		bgColor = replaceIfMissing(bgColor, "WHITE");
		
		String name = request.getParameter("name");
		name = replaceIfMissing(name, "G�khan CEYLAN");
		
		String title = request.getParameter("title");
		title = replaceIfMissing(title, "Submit - Preview Servlet");
		
		String email = request.getParameter("email");
		email = replaceIfMissing(email, "gokhan.ceylan@bil.omu.edu.tr");
		
		String languages = request.getParameter("languages");
		languages = replaceIfMissing(languages, "<I>None</I>");
		
		String skills = request.getParameter("skills");
		skills = replaceIfMissing(skills, "<i>Not many, obviously.</i>");
		
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		
		out.println(
				"<body bgcolor=" + bgColor + " fgcolor=" + fgColor + " text=" + textColor + ">" +
				"<center>" +
				"<h2>" + name + "</h2>" +
				"<p><a href=\"mailto:" + email + "\">" + email + "</a></p>\n" +
				"<hr>\n" +
				"<p>Programming Languages:</p>" + makeList(languages) + "\n" +
				
				"<p>Skills and Experience:</p>" + skills + "\n" +
				
				"</center>"
		);
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./submit_resume.html"));
		
	}
	
	private String makeList(String languages) {
		StringBuffer sb = new StringBuffer();
		
		for (String lang : languages.split(",")) {
			sb.append("<li>").append(lang.trim()).append("</li>\n");
		}
		
		return sb.toString();
	}

	private String replaceIfMissing(String original, String replacement) {
		if (original == null || original.trim().equals(""))
			return replacement;
		else
			return original;
	}
	
	private String replaceIfMissingOrDefault(String original, String replacement) {
		if (original == null || original.trim().equals("") || original.equals("default"))
			return replacement;
		else
			return original;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
