package coreservlet.sessiontracking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreservlet.cookies.ServletUtilities;

/**
 * Servlet implementation class ShowItems
 */
public class ShowItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		ArrayList<String> myOrders = (ArrayList<String>) session.getAttribute("myOrders");

		if (myOrders == null) {
			myOrders = new ArrayList<>();
			session.setAttribute("myOrders", myOrders);
		}

		String orderName = request.getParameter("order");

		if (orderName != null && !orderName.trim().equals(""))
			myOrders.add(orderName);

		if (request.getParameter("showList") != null) {
			PrintWriter out = response.getWriter();
			
			String title = "Items Purchased";
			out.println(ServletUtilities.getHeadWithCss(title));
			out.println(ServletUtilities.openMainDiv(title));

			if (myOrders.size() == 0) {
				out.println("<p><i>No Order</i></p>");
			} else {
				out.println("<table class=\"table table-hover\">");
				out.println("<tr><th>#</th><th>Order Name</th>");
				for (int i = 0; i < myOrders.size(); i++) {
					out.println("<tr><td>" + (i + 1) + "</td><td>" + myOrders.get(i) + "</td></tr>");
				}
				out.println("</table>");
				out.println("<p><a href=\"./buy-order\" class=\"btn btn-primary\">Buy</a></p>");
			}

			out.println(ServletUtilities.closeMainDiv());
			out.println(ServletUtilities.getFooter("./order_form.html"));

		} else {
			response.sendRedirect("./order_form.html");
		}
	}

}
