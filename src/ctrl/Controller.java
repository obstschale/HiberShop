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

		if (request.getParameter("submitButton") != null) {

			/** setting up Hibernate SessionFactory **/
			sf = HibernateUtil.getSessionFactory();
			
			String submitBtn = request.getParameter("submitButton");
			
			if (submitBtn.equals("Weiter: Album speichern")) {
				address = "AlbumProcessing.jsp";
				Album album = (Album) request.getSession().getAttribute("albumdata");
				
				try {
					session = sf.getCurrentSession();
					// Datenmanipulation ueber Transaktionen
					transaction = session.beginTransaction();
					session.save(album);
					transaction.commit();
				} catch (Exception ex) {
					System.err.println("Failed to create sessionFactory object." + ex);
				    throw new ExceptionInInitializerError(ex);
				}
				
			} else if (submitBtn.equals("Weiter: Type speichern")) {
				address = "TypeProcessing.jsp";
				Type type = (Type) request.getSession().getAttribute("typedata");
				
				try {
					session = sf.getCurrentSession();
					// Datenmanipulation ueber Transaktionen
					transaction = session.beginTransaction();
					session.save(type);
					transaction.commit();
				} catch (Exception ex) {
					System.err.println("Failed to create sessionFactory object." + ex);
				    throw new ExceptionInInitializerError(ex);
				}
				
			} else if (submitBtn.equals("Weiter: Medium speichern")) {
				address = "MediumProcessing.jsp";
				Medium medium = (Medium) request.getSession().getAttribute("mediumdata");
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
					System.err.println("Failed to create sessionFactory object." + ex);
				    throw new ExceptionInInitializerError(ex);
				}
			} else {
				request.setAttribute("errortext", "Wierd ... What happend with your submit?");
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
				request.setAttribute("errortext", "Wierd ... What happend with your back operation?");
				address = "ErrorText.jsp";
			}
			
		} else if (request.getParameter("confirmButton") != null) {
			String confirmBtn = request.getParameter("confirmButton");

			if (confirmBtn.equals("Neues Album")) {
				Album album = new Album();

				address = "AlbumConfirmation.jsp";
				request.getSession().setAttribute("albumdata", album);

				album.setName(request.getParameter("name"));
				album.setInterpret(request.getParameter("interpret"));
				album.setCover(request.getParameter("cover"));
				//album.setMedium();

			} else if (confirmBtn.equals("Neuer Typ")) {
				Type type = new Type();
				
				address = "TypeConfirmation.jsp";
				request.getSession().setAttribute("typedata", type);
				
				type.setName(request.getParameter("name"));
				type.setIcon(request.getParameter("icon"));
				
			} else if (confirmBtn.equals("Neues Medium")) {
				try {
					Medium medium = new Medium();
					Album tempAlbum = new Album();
					Type tempType = new Type();
					
					address = "MediumConfirmation.jsp";
					request.getSession().setAttribute("mediumdata", medium);
					
					try {
						/** setting up Hibernate SessionFactory **/
						sf = HibernateUtil.getSessionFactory();
						session = sf.getCurrentSession();
						// Datenmanipulation ueber Transaktionen
						transaction = session.beginTransaction();
						
						System.out.println(request.getParameter("album"));
						if (!request.getParameter("album").equals("NULL")) {
							tempAlbum = (Album) session.get(Album.class, Integer.parseInt(request.getParameter("album")));
						} else {
							tempAlbum = null;
						}
						
						if (!request.getParameter("type").equals("NULL")) {
							tempType = (Type) session.get(Type.class, Integer.parseInt(request.getParameter("type")));							
						} else {
							tempType = null;
						}

						
						transaction.commit();
					} catch (Exception ex) {
						System.err.println("Failed to create sessionFactory object." + ex);
					    throw new ExceptionInInitializerError(ex);
					}
					
					medium.setAlbum(tempAlbum);
					medium.setDateigroesse(Float.parseFloat(request.getParameter("dateigroesse")));
					medium.setInterpret(request.getParameter("interpret"));
					medium.setLaenge(Float.parseFloat(request.getParameter("laenge")));
					medium.setPfad(request.getParameter("pfad"));
					medium.setTitel(request.getParameter("titel"));
					medium.setType(tempType);
					
				} catch (NumberFormatException e){
					request.setAttribute("errortext", "Fehlermeldung: Da ist wohl was falsch gelaufen!");
					address="NewMedium.jsp";
				}
				
			} else {
				request.setAttribute("errortext", "Wierd ... What happend with your confirmation?");
				address = "ErrorText.jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
