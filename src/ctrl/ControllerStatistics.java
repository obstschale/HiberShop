package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import backend.DatabaseQueries;

public class ControllerStatistics extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = "Statistik.jsp";
		request.setAttribute("errortext", "");

		 if (request.getParameter("stats") != null) {
			try {
				int limit = Integer.parseInt(request.getParameter("limit"));
				int max = Integer.parseInt(request.getParameter("max"));
				
				request.setAttribute("topBought", DatabaseQueries.getTopBoughtMedia(limit));
				request.setAttribute("topPlayed", DatabaseQueries.getTopPlayedMedia(max));
			} catch (Exception e) {
				System.err.println("Failed to get topPlayed/topBought. " + e);
				e.printStackTrace();
				request.setAttribute("errortext",
						"Mit deiner Anfrage ist wohl etwas schief gelaufen.");
			}
		} else {
				request.setAttribute("errortext",
						"Wierd ... What happend with your confirmation?");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String address = "Statistik.jsp";
		
		try {			
			request.setAttribute("topBought", DatabaseQueries.getTopBoughtMedia(1));
			request.setAttribute("topPlayed", DatabaseQueries.getTopPlayedMedia(5));
		} catch (Exception e) {
			System.err.println("Failed to get topPlayed/topBought. " + e);
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
