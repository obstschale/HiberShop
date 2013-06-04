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

//		 typeTest.typeTests(sf);
//		 albumTest.albumTests(sf);
//		 mediumTest.mediumTests(sf);

		testDBQueries(sf);
		testDBgetTopPlayedMedia(sf, 2);
		testDBgetTopBoughtMedia(sf, 10);

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
					+ medium.getTitel() + "; Angehoert: " + medium.getAngehoert());
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
