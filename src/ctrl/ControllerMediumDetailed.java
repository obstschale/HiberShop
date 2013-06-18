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

public class ControllerMediumDetailed extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;
	
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
			Cart cart = ControllerAllMedia.getSessionCart(request);

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
				ControllerAllMedia.loadAllMedia(request);
				
			} catch (Exception ex) {
				System.err.println("Failed to buy Object object." + ex);
				ex.printStackTrace();
				request.setAttribute("errortext", "Das hat wohl nicht geklappt, das Lied in den Warenkorb zu legen.");
			}
			
		} else if (request.getParameter("back") != null) {
			/* Customer clicked back button */
			address = "AllMedia.jsp";
			ControllerAllMedia.loadAllMedia(request);
			
		} else if (request.getParameter("play") != null) {
			/* Customer clicked play button */
			Medium medium = new Medium();
			address = "PlayMedium.jsp";
			int id = Integer.parseInt(request.getParameter("play"));

			try {
				
				/* increase attribute Angehoert in DB by one */
				DatabaseQueries.increasePlayedofMedium(id);
				
				/* save medium as attribute so PlayMedium.jsp has access */
				request.getSession().setAttribute("mediumdata", medium);
				
				/* this attribute sets the action of the form in PlayMedium.jsp
				 * so the user comes back to Medium.jsp by clicking 
				 * on back.
				 */
				request.setAttribute("controller", "ControllerMediumDetailed");

			} catch (Exception ex) {
				System.err.println("Failed to create play object." + ex);
				ex.printStackTrace();
				request.getSession().setAttribute("errortext", "Das hat irgendwie nicht geklappt mit dem abspielen. Sorry :(");
				address = "AllMedia.jsp";	
			}

		} else {
			request.setAttribute("errortext", "Wierd ... Something went wront with your request O_o");
			address = "AllMedia.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}

