package tests;

import java.util.Iterator;
import java.util.List;

import hibernate.HibernateUtil;

import model.Album;
import model.Medium;
import model.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import view.DatabaseQueries;

public class Tests {
	private static Session session;
	private static Transaction transaction;

	public static void main(String[] args) {
//		TypeTest typeTest = new TypeTest();
//		AlbumTest albumTest = new AlbumTest();
//		MediumTest mediumTest = new MediumTest();

		/** setting up Hibernate SessionFactory **/
		SessionFactory sf = HibernateUtil.getSessionFactory();

//		typeTest.typeTests(sf);
//		mediumTest.mediumTests(sf);
//		albumTest.albumTests(sf);
		
		Type type1 = new Type();
		Type type2 = new Type();
		type1.setName("Audio");
		type2.setName("Video");
		
		Medium medium1 = new Medium();
		Medium medium2 = new Medium();
		medium1.setInterpret("Medium 1");
		medium1.setTitel("Awesome Titel 1");
		medium1.setType(type2);
		medium2.setInterpret("Medium 2");
		medium2.setTitel("Awesome Titel 2");
		medium2.setType(type1);
		
		Album album = new Album();
		album.setName("Album 1");
		album.setInterpret("Album Interpret");
		
		album.getMedia().add(medium1);
		medium1.setAlbum(album);
		
		album.getMedia().add(medium2);
		medium2.setAlbum(album);
		
		try {
			session = sf.getCurrentSession();
			// Datenmanipulation ueber Transaktionen
			transaction = session.beginTransaction();
			session.save(album);
			session.save(type1);
			session.save(type2);
			session.save(medium1);
			session.save(medium2);
			transaction.commit();
		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		// testDBQueries(sf);
		// testDBgetTopPlayedMedia(sf, 2);
		// testDBgetTopBoughtMedia(sf, 10);

	}

	private static void testDBgetTopBoughtMedia(SessionFactory sf, int i) {
		DatabaseQueries dbTests = new DatabaseQueries();
		session = sf.getCurrentSession();
		// Datenmanipulation ueber Transaktionen
		transaction = session.beginTransaction();

		List<Medium> media = dbTests.getTopBoughtMedia(session, transaction, i);
		Iterator<Medium> iter = media.iterator();
		while (iter.hasNext()) {
			Medium medium = iter.next();
			System.out.println("Medium:  " + medium + "; Titel: "
					+ medium.getTitel() + "; Gekauft: " + medium.getGekauft());
		}

		transaction.commit();
	}

	private static void testDBgetTopPlayedMedia(SessionFactory sf, int i) {
		DatabaseQueries dbTests = new DatabaseQueries();
		session = sf.getCurrentSession();
		// Datenmanipulation ueber Transaktionen
		transaction = session.beginTransaction();

		List<Medium> media = dbTests.getTopPlayedMedia(session, transaction, i);
		Iterator<Medium> iter = media.iterator();
		while (iter.hasNext()) {
			Medium medium = iter.next();
			System.out.println("Medium:  " + medium + "; Titel: "
					+ medium.getTitel() + "; Angehoert: "
					+ medium.getAngehoert());
		}

		transaction.commit();
	}

	private static void testDBQueries(SessionFactory sf) {
		DatabaseQueries dbTests = new DatabaseQueries();
		session = sf.getCurrentSession();
		// Datenmanipulation ueber Transaktionen
		transaction = session.beginTransaction();

		List<Medium> media = dbTests.getAllMedia(session, transaction);
		Iterator<Medium> iter = media.iterator();
		while (iter.hasNext()) {
			Medium medium = iter.next();
			System.out.println("Medium:  " + medium + "; Titel: "
					+ medium.getTitel());
		}

		List<Type> types = dbTests.getAllTypes(session, transaction);
		Iterator<Type> iter1 = types.iterator();
		while (iter1.hasNext()) {
			Type type = iter1.next();
			System.out.println("Type:  " + type + "; Name: " + type.getName());
		}

		List<Album> albums = dbTests.getAllAlbums(session, transaction);
		Iterator<Album> iter2 = albums.iterator();
		while (iter2.hasNext()) {
			Album album = iter2.next();
			System.out.println("Album:  " + album + "; Titel: "
					+ album.getName());
		}

		transaction.commit();
	}
}
