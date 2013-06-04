package ctrl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		request.setAttribute("errortext", "");

		if (request.getParameter("submitButton") != null) {
			String submitBtn = request.getParameter("submitButton");
			
			if (submitBtn.equals("Weiter: Album speichern")) {
				address = "AlbumProcessing.jsp";
			} else if (submitBtn.equals("Weiter: Type speichern")) {
				address = "TypeProcessing.jsp";
			} else if (submitBtn.equals("Weiter: Medium speichern")) {
				address = "MediumProcessing.jsp";
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
				address = "AlbumConfirmation.jsp";
			} else if (confirmBtn.equals("Neuer Typ")) {
				address = "TypeConfirmation.jsp";
			} else if (confirmBtn.equals("Neues Medium")) {
				address = "MediumConfirmation.jsp";
			} else {
				request.setAttribute("errortext", "Wierd ... What happend with your confirmation?");
				address = "ErrorText.jsp";
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
