package ctrl;

import hibernate.HibernateUtil;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Medium;
import model.Album;
import model.Type;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ControllerMedium extends HttpServlet {

	private static final long serialVersionUID = 6901673769632833244L;
	Transaction transaction = null;
	Session session = null;
	SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String address = null;
		Medium medium = new Medium();
		request.setAttribute("errortext", "");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Factory für FileItems erzeugen
		FileItemFactory factory = new DiskFileItemFactory();
		// Neuen File-Upload-Handler erzuegen
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Request parsen
		List<FileItem> items = null;

		try {
			Album tempAlbum = new Album();
			Type tempType = new Type();

			try {
				/** setting up Hibernate SessionFactory **/
				sf = HibernateUtil.getSessionFactory();

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
						if (name.equals("album")) {
							if (!content.equals("NULL")) {

								session = sf.getCurrentSession();
								// Datenmanipulation ueber Transaktionen
								transaction = session.beginTransaction();
								tempAlbum = (Album) session.get(Album.class,
										Integer.parseInt(content));
								medium.setAlbum(tempAlbum);
								transaction.commit();
							} else {
								tempAlbum = null;
							}
						} else if (name.equals("type")) {
							if (!content.equals("NULL")) {

								session = sf.getCurrentSession();
								// Datenmanipulation ueber Transaktionen
								transaction = session.beginTransaction();
								tempType = (Type) session.get(Type.class,
										Integer.parseInt(content));
								medium.setType(tempType);
								transaction.commit();
							} else {
								isMultipart = false;
							}
						} else if (name.equals("titel")) {
							medium.setTitel(content);
						} else if (name.equals("interpret")) {
							medium.setInterpret(content);
						} else if (name.equals("laenge")) {
							if (!content.equals("")) {
								medium.setLaenge(Float.parseFloat(content));
							}
						}

					} else if (!item.isFormField()) {
						// Process a file upload
						// String fieldName = item.getFieldName();
						String fileName = item.getName();
						// String contentType = item.getContentType();
						// boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();
						// System.out.println("-----------");
						// System.out.println(fieldName);
						// System.out.println(fileName);
						// System.out.println(contentType);
						// System.out.println(isInMemory);
						// System.out.println(sizeInBytes);
						// System.out.println("-----------");
						// Process a file upload
						try {
							if (sizeInBytes > 0) {
								String systemPath = request.getSession()
										.getServletContext().getRealPath("/");
								String filePath = "";

								if (fileName.matches("(.*)(.mp4)$")) {
									filePath = systemPath + "media/video/"
											+ fileName;
									/*
									 * save absolute path to album object to
									 * save path to DB
									 */
									medium.setPfad("media/video/" + fileName);
								} else if (fileName
										.matches("(.*)(.mp3|.ogg|.midi|.wav)$")) {
									filePath = systemPath + "media/audio/"
											+ fileName;
									/*
									 * save absolute path to album object to
									 * save path to DB
									 */
									medium.setPfad("media/audio/" + fileName);
								}

								/* set filesize */
								float size = sizeInBytes / (1024 * 1024);
								medium.setDateigroesse(size);

								/* save file to disk */
								File uploadedFile = new File(filePath);
								item.write(uploadedFile);
							}
						} catch (Exception e) {
							System.err.println("Failed to upload file." + e);
							e.printStackTrace();
							/*
							 * set isMultipart to false so doPost() does not
							 * write broken parts to DB and forwards to
							 * AlbumConfirmation.jsp instead write errortext and
							 * reload NewAlbum.jsp
							 */
							isMultipart = false;
						}
					}
				} // end while
			} catch (FileUploadException ex) {
				System.err.println("Failed to upload File." + ex);
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}

		if (isMultipart) {
			address = "MediumConfirmation.jsp";
			request.getSession().setAttribute("mediumdata", medium);

		} else {
			address = "NewMedium.jsp";
			request.setAttribute("errortext",
					"Fehlermeldung: Oho O_o das ist wohl etwas schief gelaufen.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
