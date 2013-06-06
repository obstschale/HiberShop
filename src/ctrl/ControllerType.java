package ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Type;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ControllerType extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		Type type = new Type();
		request.setAttribute("errortext", "");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Factory für FileItems erzeugen
		FileItemFactory factory = new DiskFileItemFactory();
		// Neuen File-Upload-Handler erzuegen
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Request parsen
		List<FileItem> items = null;
		try {
			// Process the uploaded items
			items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = iter.next();
				String name = item.getFieldName();

				if (item.isFormField()) {
					// Behandlung der „normalen“ Eingabefelder
					String content = item.getString();

					// Process a regular form field
					if (name.equals("name")) {
						type.setName(content);
					}

				} else if (!item.isFormField()) {
					// Process a file upload
//					 String fieldName = item.getFieldName();
					 String fileName = item.getName();
//					 String contentType = item.getContentType();
//					 boolean isInMemory = item.isInMemory();
					 long sizeInBytes = item.getSize();
//					 System.out.println("-----------");
//					 System.out.println(fieldName);
//					 System.out.println(fileName);
//					 System.out.println(contentType);
//					 System.out.println(isInMemory);
//					 System.out.println(sizeInBytes);
//					 System.out.println("-----------");
					// Process a file upload
					try {
						if (sizeInBytes > 0) {
							String systemPath = request.getSession()
									.getServletContext().getRealPath("/");
							String filePath = systemPath + "media/img/" + fileName;
							
							/* save absolute path to album object to save path to DB */
							type.setIcon("media/img/" + fileName);
							
							/* save file to disk */
							File uploadedFile = new File(filePath);
							item.write(uploadedFile);
						}
					} catch (Exception e) {
						/* set isMultipart to false so doPost() does not write broken
						 * parts to DB and forwards to AlbumConfirmation.jsp
						 * instead write errortext and reload NewAlbum.jsp
						 */
						isMultipart = false;
					}
				}
			}
		} catch (FileUploadException e1) {

		}

		if (isMultipart) {
			address = "TypeConfirmation.jsp";
			request.getSession().setAttribute("typedata", type);
			
		} else {
			address = "NewType.jsp";
			request.setAttribute("errortext",
					"Fehlermeldung: Oho O_o das ist wohl etwas schief gelaufen.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
