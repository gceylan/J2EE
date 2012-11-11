package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShadowedText
 */
public class ShadowedText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShadowedText() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("images/png");  // response headers -> "images/png"

    	String showFontList = request.getParameter("showFontList");
    	if (showFontList != null) {
    		showFonts(response);
    	} else {
    		String text = request.getParameter("text");
    		if ((text == null) || (text.length() == 0)) {
    			text = "Missing 'text' parameter";
    		}
    		String fontName = request.getParameter("fontName");
    		if ((fontName == null) || (fontName.length() == 0)) {
    			fontName = "Serif";
    		}
    		String fontSizeString = request.getParameter("fontSize");
    		int fontSize;
    		try {
    			fontSize = Integer.parseInt(fontSizeString);
    		} catch(NumberFormatException nfe) {
    			fontSize = 90;
    		}
    		
    		MessageImage.writePNG(
    				MessageImage.makeMessageImage(
    						text,
    						fontName,
    						fontSize
    				), response.getOutputStream());
    	}

    }

	private void showFonts(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Desteklenen Fontlar:";
		String [] allFonts = MessageImage.getFontNames();
		
		out.println(ServletUtilities.getHeadWithCss(title));
		out.println(ServletUtilities.openMainDiv(title));
		
		out.println("<ul>");
		for (String font : allFonts) {
			out.println("<li>" + font + "</li>");
		}
		out.println("</ul>");
		
		out.println(ServletUtilities.closeMainDiv());
		out.println(ServletUtilities.getFooter("./shadowed_text.html"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
