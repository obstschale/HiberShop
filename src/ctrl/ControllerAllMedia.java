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

import backend.Cart;
import backend.DatabaseQueries;

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

		address = "AllMedia.jsp";

		/* click from Navigation */
		loadAllMedia(request);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		request.setAttribute("errortext", "");
		
		if (request.getParameter("buy") != null) {
			/* Customer clicked buy button */
			Medium medium = new Medium();
			address = "AllMedia.jsp";

			int id = Integer.parseInt(request.getParameter("buy"));

			/* get cart object if already exists otherwise set attribute */
			Cart cart = getSessionCart(request);

			try {
				/* get Medium from DB */
				medium = DatabaseQueries.getMediumById(id);
				
				/* check if Medium is already in Cart */
				Boolean notInList = true;
				for (Medium m : cart.getMedia() ) {
					if (m.getId() == id)
						notInList = false;
				}
				if (notInList) {
					cart.getMedia().add(medium);
				}
				
				request.getSession().setAttribute("cart", cart);
				loadAllMedia(request);
				
			} catch (Exception ex) {
				System.err.println("Failed to buy Object object." + ex);
				ex.printStackTrace();
				request.setAttribute("errortext", "Das hat wohl nicht geklappt, das Lied in den Warenkorb zu legen.");
			}


		} else if (request.getParameter("details") != null) {
			/* Customer clicked details button */
			Medium medium = new Medium();
			address = "Medium.jsp";
			int id = Integer.parseInt(request.getParameter("details"));

			try {

				medium = DatabaseQueries.getMediumById(id);
				request.getSession().setAttribute("dateigroesse", medium.getDateigroesseMB());
				request.getSession().setAttribute("album", medium.getType().getName());
				request.getSession().setAttribute("type", medium.getAlbum().getName());
				request.getSession().setAttribute("mediumdata", medium);
				
			} catch (Exception ex) {
				
				System.err.println("Failed to create show details." + ex);
				ex.printStackTrace();
				request.getSession().setAttribute("errortext", "Das war wohl nichts mit den Details. Sorry :(");
				address = "AllMedia.jsp";	
				
			}
		} else if (request.getParameter("play") != null) {
			/* Customer clicked play button */
			Medium medium = new Medium();
			address = "PlayMedium.jsp";
			int id = Integer.parseInt(request.getParameter("play"));
			
			try {
				
				/* increase attribute played in DB by one */
				DatabaseQueries.increasePlayedofMedium(id);
				
				/* get medium data from DB to pass to PlayMedium.jsp */
				medium = DatabaseQueries.getMediumById(id);
				
				/* save medium as attribute so PlayMedium.jsp has access */
				request.getSession().setAttribute("mediumdata", medium);
				
				/* this attribute sets the action of the form in PlayMedium.jsp
				 * so the user comes back to AllMedia.jsp by clicking 
				 * on back.
				 */
				request.setAttribute("controller", "ControllerAllMedia");

			} catch (Exception ex) {
				System.err.println("Failed to create play object." + ex);
				ex.printStackTrace();
				request.getSession().setAttribute("errortext", "Das hat irgendwie nicht geklappt mit dem abspielen. Sorry :(");
				address = "AllMedia.jsp";	
			}

		} else {
			/* click from Navigation */
			address = "AllMedia.jsp";
			
			try {
				request.setAttribute("allMedia", DatabaseQueries.getAllMedia());
			} catch (Exception e) {
				System.err.println("Failed to get albumList/typeList. " + e);
				e.printStackTrace();
				request.setAttribute("errortext", "Medien Daten konnten nicht geholt werden.");
				request.setAttribute("allMedia", null);
			}
			
			if (request.getParameter("backPlay") == null) {
				request.setAttribute("errortext", "Das ist jetzt aber peinlich. Da ist wohl etwas schief gelaufen.");
			}
			
		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	public static void loadAllMedia(HttpServletRequest request) {
		try {
			request.setAttribute("allMedia", DatabaseQueries.getAllMedia());
		} catch (Exception e) {
			System.err.println("Failed to get albumList/typeList. " + e);
			e.printStackTrace();
			request.setAttribute("errortext", "Medien Daten konnten nicht geholt werden.");
			request.setAttribute("allMedia", null);
		}
	}
	
	public static Cart getSessionCart(HttpServletRequest request) {
		Cart cart;
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
		
		return cart;
	}
}
