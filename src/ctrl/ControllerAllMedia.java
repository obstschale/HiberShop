package ctrl;

import hibernate.HibernateUtil;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Album;
import model.Medium;
import model.Type;

public class ControllerAllMedia extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String address = null;
		request.setAttribute("errortext", "");

		if (request.getParameter("buy") != null) {
			/* Customer clicked buy button */
			
		} else if (request.getParameter("details") != null) {
			/* Customer clicked details button */
			Medium medium = new Medium();
			
			address = "Medium.jsp";
			
			int id = Integer.parseInt(request.getParameter("details"));
			
			try {
				/** setting up Hibernate SessionFactory **/
				sf = HibernateUtil.getSessionFactory();
				session = sf.getCurrentSession();
				// Datenmanipulation ueber Transaktionen
				transaction = session.beginTransaction();
				
				medium = (Medium) session.get(Medium.class, id);
				request.getSession().setAttribute("dateigroesse", medium.getDateigroesseMB());
				request.getSession().setAttribute("album", medium.getType().getName());
				request.getSession().setAttribute("type", medium.getAlbum().getName());

				transaction.commit();
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
			    throw new ExceptionInInitializerError(ex);
			}

			request.getSession().setAttribute("mediumdata", medium);
		} else if (request.getParameter("play") != null) {
			/* Customer clicked play button */
			
		} else {
			request.setAttribute("errortext", "Wierd ... Something went wront with your request O_o");
			address = "AllMedia.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
