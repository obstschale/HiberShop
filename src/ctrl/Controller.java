package ctrl;

import hibernate.HibernateUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import view.DatabaseQueries;

import model.Album;
import model.Medium;
import model.Type;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		request.setAttribute("errortext", "");
		
		/** setting up Hibernate SessionFactory **/
		sf = HibernateUtil.getSessionFactory();
		
		if (request.getParameter("submitButton") != null) {
			String submitBtn = request.getParameter("submitButton");

			if (submitBtn.equals("Weiter: Album speichern")) {
				address = "AlbumProcessing.jsp";
				Album album = (Album) request.getSession().getAttribute(
						"albumdata");

				try {
					session = sf.getCurrentSession();
					// Datenmanipulation ueber Transaktionen
					transaction = session.beginTransaction();
					session.save(album);
					transaction.commit();
				} catch (Exception ex) {
					System.err
							.println("Failed to create sessionFactory object."
									+ ex);
					throw new ExceptionInInitializerError(ex);
				}

			} else if (submitBtn.equals("Weiter: Type speichern")) {
				address = "TypeProcessing.jsp";
				Type type = (Type) request.getSession()
						.getAttribute("typedata");

				try {
					session = sf.getCurrentSession();
					// Datenmanipulation ueber Transaktionen
					transaction = session.beginTransaction();
					session.save(type);
					transaction.commit();
				} catch (Exception ex) {
					System.err
							.println("Failed to create sessionFactory object."
									+ ex);
					throw new ExceptionInInitializerError(ex);
				}

			} else if (submitBtn.equals("Weiter: Medium speichern")) {
				address = "MediumProcessing.jsp";
				Medium medium = (Medium) request.getSession().getAttribute(
						"mediumdata");
				Album album = medium.getAlbum();
				int albumId = album.getId();

				try {
					session = sf.getCurrentSession();
					// Datenmanipulation ueber Transaktionen
					transaction = session.beginTransaction();
					album = (Album) session.get(Album.class, albumId);
					album.getMedia().add(medium);

					session.save(album);
					session.save(medium);
					transaction.commit();
				} catch (Exception ex) {
					System.err
							.println("Failed to create sessionFactory object."
									+ ex);
					throw new ExceptionInInitializerError(ex);
				}
			} else {
				request.setAttribute("errortext",
						"Wierd ... What happend with your submit?");
				address = "ErrorText.jsp";
			}

		} else if (request.getParameter("backButton") != null) {
			String backBtn = request.getParameter("backButton");

			if (backBtn.equals("Zurück zu New Album")) {
				address = "NewAlbum.jsp";
			} else if (backBtn.equals("Zurück zu New Type")) {
				address = "NewType.jsp";
			} else if (backBtn.equals("Zurück zu New Medium")) {
				address = "NewMedium.jsp";
			} else {
				request.setAttribute("errortext",
						"Wierd ... What happend with your back operation?");
				address = "ErrorText.jsp";
			}

		} else if (request.getParameter("statistik") != null) {
			address = "Statistik.jsp";
			List<Medium> topBought = null;
			List<Medium> topPlayed = null;
			
			try {
				session = sf.getCurrentSession();
				// Datenmanipulation ueber Transaktionen
				transaction = session.beginTransaction();
				DatabaseQueries queries = new DatabaseQueries();
				topBought = queries.getTopBoughtMedia(session, transaction, 1);
				request.setAttribute("topBought", topBought);
				
				topPlayed = queries.getTopPlayedMedia(session, transaction, 10);
				request.setAttribute("topPlayed", topPlayed);
				
				transaction.commit();
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		} else {
				request.setAttribute("errortext",
						"Wierd ... What happend with your confirmation?");
				address = "ErrorText.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
