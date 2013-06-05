package tests;

import model.Album;
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
		Medium medium1 = createNewMedium("Titel 1", "Michael Jackson", 3.20f, 22.44f, "path/to/medium1");
		Medium medium2 = createNewMedium("Track 99", "TuPac", 4.44f, 12.34f, "path/to/medium2");

		try {
			session = sf.getCurrentSession();
			// Datenmanipulation ueber Transaktionen
			transaction = session.beginTransaction();
			Album tempAlbum = (Album) session.get(Album.class, 1);
			session.save(tempAlbum);
			medium1.setAlbum(tempAlbum);
			session.save(medium1);
			session.save(medium2);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
	
		
	}
	
	private static Medium createNewMedium(String titel, String interpret, float laenge, float dateigroesse, String pfad) {
		Medium m = new Medium();
//		m.setType(null);
//		m.setAlbum(null);
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
