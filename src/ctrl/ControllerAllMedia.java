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

import view.Cart;

import model.Medium;

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
			Medium medium = new Medium();
			Cart cart;
			address = "AllMedia.jsp";

			int id = Integer.parseInt(request.getParameter("buy"));

			/*
			 * get cart object if already exists otherwise set attribute
			 */
			try {
				if (request.getSession().getAttribute("cart") != null) {
					cart = (Cart) request.getSession().getAttribute("cart");
				} else {
					cart = new Cart();
					request.getSession().setAttribute("cart", cart);
				}
			} catch (NullPointerException ex) {
				System.err.println("Failed to create cart object." + ex);
				throw new ExceptionInInitializerError(ex);
			}

			try {
				/** setting up Hibernate SessionFactory **/
				sf = HibernateUtil.getSessionFactory();
				session = sf.getCurrentSession();
				// Datenmanipulation ueber Transaktionen
				transaction = session.beginTransaction();

				medium = (Medium) session.get(Medium.class, id);
				Boolean notInList = true;
				for (Medium m : cart.getMedia() ) {
					if (m.getId() == id)
						notInList = false;
				}
				if (notInList)
					cart.getMedia().add(medium);
				

				transaction.commit();
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}

			request.getSession().setAttribute("cart", cart);

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
				request.getSession().setAttribute("dateigroesse",
						medium.getDateigroesseMB());
				request.getSession().setAttribute("album",
						medium.getType().getName());
				request.getSession().setAttribute("type",
						medium.getAlbum().getName());

				transaction.commit();
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}

			request.getSession().setAttribute("mediumdata", medium);
		} else if (request.getParameter("play") != null) {
			/* Customer clicked play button */
			Medium medium = new Medium();
			address = "PlayMedium.jsp";
			int id = Integer.parseInt(request.getParameter("play"));
			
			try {
				/** setting up Hibernate SessionFactory **/
				sf = HibernateUtil.getSessionFactory();
				session = sf.getCurrentSession();
				// Datenmanipulation ueber Transaktionen
				transaction = session.beginTransaction();
				
				/* get medium object */
				medium = (Medium) session.get(Medium.class, id);
				/* save medium as attribute so PlayMedium.jsp has access */
				request.getSession().setAttribute("medium", medium);
				/* increament played in Database */
				int played = medium.getAngehoert();
				played++;
				medium.setAngehoert(played);
				session.update(medium);

				transaction.commit();
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			
			request.setAttribute("controller", "ControllerAllMedia");

		} else if (request.getParameter("backPlay") != null) {
			address = "AllMedia.jsp";
		} else {
			request.setAttribute("errortext",
					"Wierd ... Something went wront with your request O_o");
			address = "AllMedia.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
