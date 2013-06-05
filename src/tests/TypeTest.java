package tests;

import model.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TypeTest {
	
	Transaction transaction = null;
	Session session = null;
	
	/**
	 * @param args
	 */
	public void typeTests(SessionFactory sf) {
		Type audio = createNewType("Audio", "/path/to/audio-icon");
		Type video = createNewType("Video", "/path/to/video-icon");
		
		try {
			session = sf.getCurrentSession();
			// Datenmanipulation ueber Transaktionen
			transaction = session.beginTransaction();
			session.save(audio);
			session.save(video);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
	
		
	}
	
	private static Type createNewType(String name, String icon) {
		Type type = new Type();
		type.setName(name);
		type.setIcon(icon);
		return type;
	}
}
