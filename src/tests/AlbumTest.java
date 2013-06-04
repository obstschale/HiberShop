package tests;

import model.Album;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AlbumTest {
	
	Transaction transaction = null;
	Session session = null;
	
	/**
	 * @param args
	 */
	public void albumTests(SessionFactory sf) {
		Album rootbeers = createNewAlbum("Back to the Roots", "the Rootbeers", "path/to/root-cover");
		Album bonJovi = createNewAlbum("Greatest Hits", "Bon Jovi", "path/to/bonJovi-cover");

		try {
			session = sf.getCurrentSession();
			// Datenmanipulation ueber Transaktionen
			transaction = session.beginTransaction();
			session.save(rootbeers);
			session.save(bonJovi);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
	
		
	}
	
	private static Album createNewAlbum(String name, String interpret, String cover) {
		Album album = new Album();
		album.setName(name);
		album.setInterpret(interpret);
		album.setCover(cover);
		return album;
	}
	
}
