package coreservlet.cookies;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ServletUtilities {
	
	public static String getHeadWithCss(String title) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\n");
		sb.append("<html>\n").append("<head>\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
		sb.append("<link href=\"assets/css/bootstrap.css\" rel=\"stylesheet\">\n");
		sb.append("<link href=\"assets/css/bootstrap-responsive.css\" rel=\"stylesheet\">\n");
		sb.append("<link href=\"assets/css/style.css\" rel=\"stylesheet\">\n");
		sb.append("<title>").append(title).append("</title>\n");
		sb.append("</head>\n");
		
		return sb.toString();
	}
	
	public static String openMainDiv(String mainText) {
		return "<div class=\"container\">\n" +
				 "<div class=\"row\">\n" +
				   "<div class=\"span12\">\n" +
				     "<h2>" + mainText + "</h2>\n";
	}
	
	public static String closeMainDiv() {
		return "</div>\n</div>\n</div>\n";
	}
	
	public static String getFooter(String back) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<footer>\n").append("<div class=\"container\">").append("<hr>\n");
		sb.append("<p class=\"pull-right\"><a href=" + back + "><i class=\"icon-arrow-left\"></i> Back to top</a></p>\n");
		sb.append("<ul>\n");
		sb.append("<li><a href=\"./home.html\"><i class=\"icon-home\"></i> Home</a></li>\n");
		sb.append("</ul>\n").append("</div>\n").append("</footer>\n");
		
		return sb.toString();
	}
	
	public static String openForm(String method, String action) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<center><form").append(" action = \"");
		sb.append(action).append("\"").append(" method = \"").append(method);
		sb.append("\">");
		
		return sb.toString();
	}
	
	public static String closeForm() {
		return "</center></form>";
	}

}
