package ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Album;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import backend.DatabaseQueries;
import backend.SHAHashing;

public class ControllerAlbum extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		Album album = new Album();
		request.setAttribute("errortext", "");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
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
							album.setName(content);
						} else if (name.equals("interpret")) {
							album.setInterpret(content);
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
								
								/* create new unique fileName to make sure
								 * not file will be overwritten just b/c
								 * it has the same name.
								 * newFileName is a SHA1 Hash by fileName+current date
								 */
								Date date = new Date();
								String sha = SHAHashing.createSHA1(fileName + date.toString());
								String newFileName = sha + fileName.substring(fileName.lastIndexOf("."));
								
								String filePath = systemPath + "media/img/" + newFileName;
								
								/* save absolute path to album object to save path to db */
								album.setCover("media/img/" + newFileName);
								
								/* save file to disk */
								File uploadedFile = new File(filePath);
								item.write(uploadedFile);
							}
						} catch (Exception e) {
							System.err.println("Failed to upload file." + e);
							e.printStackTrace();
							/* set isMultipart to false so doPost() does not write broken
							 * parts to DB and forwards to AlbumConfirmation.jsp
							 * instead write errortext and reload NewAlbum.jsp
							 */
							isMultipart = false;
						}
					}
				}
			} catch (FileUploadException e) {
				System.err.println("Failed to upload file." + e);
				e.printStackTrace();
			}
	
			if (isMultipart) {
				address = "AlbumConfirmation.jsp";
				request.getSession().setAttribute("albumdata", album);
				
			} else {
				address = "NewAlbum.jsp";
				request.setAttribute("errortext",
						"Fehlermeldung: Oho O_o das ist wohl etwas schief gelaufen.");
			}
		} else if (request.getParameter("submitButton") != null) {
			address = "AlbumProcessing.jsp";

			album = (Album) request.getSession().getAttribute("albumdata");

			try {
				
				DatabaseQueries.saveAlbumToDB(album);
				
			} catch (Exception ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				address = "NewAlbum.jsp";
				request.setAttribute("errortext", "Das ist etwas schiefgelaufen mit dem anlegen eines neuen Albums.");
			}
		
		} else if (request.getParameter("backButton") != null) {
			address = "NewAlbum.jsp";
			
			/* remove file */
			try {
				String systemPath = request.getSession()
						.getServletContext().getRealPath("/");
				album = (Album) request.getSession().getAttribute("albumdata");
				String filePath = album.getCover();

				File myFile = new File(systemPath + filePath);
				if (!myFile.isDirectory()) {
                    myFile.delete();
                }
			} catch (Exception ex) {
				System.err.println("Failed to delete file." + ex);
				ex.printStackTrace();
			}
			
			/* remove mediumdata from session */
			request.getSession().removeAttribute("albumdata");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String address = "NewAlbum.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
