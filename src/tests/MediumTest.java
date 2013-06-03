package tests;

import model.Medium;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MediumTest {
	
	Transaction transaction = null;
	Session session = null;
	
	/**
	 * @param args
	 */
	public void mediumTests(SessionFactory sf) {
		Medium medium1 = createNewMedium(1, 2, "Titel 1", "Michael Jackson", 3.20f, 22.44f, "path/to/medium1");
		Medium medium2 = createNewMedium(2, 1, "Track 99", "TuPac", 4.44f, 12.34f, "path/to/medium2");

		try {
			session = sf.getCurrentSession();
			// Datenmanipulation ueber Transaktionen
			transaction = session.beginTransaction();
			session.save(medium1);
			session.save(medium2);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
	
		
	}
	
	private static Medium createNewMedium(int type, int album, String titel, String interpret, float laenge, float dateigroesse, String pfad) {
		Medium m = new Medium();
//		m.setType(type);
//		m.setAlbum(album);
		m.setTitel(titel);
		m.setInterpret(interpret);
		m.setLaenge(laenge);
		m.setDateigroesse(dateigroesse);
		m.setPfad(pfad);
		m.setAngehoert(0);
		m.setGekauft(0);
		return m;
	}
	
}
