package ctrl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Medium;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import view.Cart;

public class ControllerCart extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String address = null;
		request.setAttribute("errortext", "");

		if (request.getParameter("cart") != null) {
			/* Customer clicked cart button */
			Cart cart;
			address = "ShowCart.jsp";
			
			/* get cart object if already exists
			 * otherwise set attribute
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
			
		} else if (request.getParameter("clean") != null) {
			/* Customer clicked cart button */
			Cart cart;
			address = "ShowCart.jsp";
			
			/* get cart object if already exists
			 * otherwise set attribute
			 */
			try {
				if (request.getSession().getAttribute("cart") != null) {
					cart = (Cart) request.getSession().getAttribute("cart");
					cart.getMedia().clear();
				} else {
					cart = new Cart();
					request.getSession().setAttribute("cart", cart);
				}
			} catch (NullPointerException ex) {
				System.err.println("Failed to create cart object." + ex);
			    throw new ExceptionInInitializerError(ex);
			}
			
			request.getSession().setAttribute("cart", cart);
			
		} else if (request.getParameter("back") != null) {
			/* Customer clicked back button */
			address = "AllMedia.jsp";

		} else if (request.getParameter("rmid") != null) {
			/* remove item from list */
			Cart cart;
			address = "ShowCart.jsp";
			
			/* get cart object if already exists
			 * otherwise set attribute
			 */
			try {
				if (request.getSession().getAttribute("cart") != null) {
					cart = (Cart) request.getSession().getAttribute("cart");
					String id = request.getParameter("rmid");

					for (Iterator<Medium> it = cart.getMedia().iterator(); it.hasNext(); ) {
				        Medium m = it.next();

				        if (m.getId() == Integer.parseInt(id)) {
				        	/* remove item and break out of loop */
				            cart.getMedia().remove(m);
				            break;
				        }
				    }
				} else {
					cart = new Cart();
					request.getSession().setAttribute("cart", cart);
				}
			} catch (NullPointerException ex) {
				System.err.println("Failed to create cart object." + ex);
			    throw new ExceptionInInitializerError(ex);
			}

			cart = (Cart) request.getSession().getAttribute("cart");
		} else {
			request.setAttribute("errortext", "Wierd ... Something went wront with your request O_o");
			address = "ShowCart.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}


