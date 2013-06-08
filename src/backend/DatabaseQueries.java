package backend;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Medium;
import model.Type;
import model.Album;

public class DatabaseQueries {
	
	public static Medium getMediumById(int id) throws Exception {
		Session session = HibernateUtil.prepareTransaction();

		Medium medium = (Medium) session.get(Medium.class, id);
		HibernateUtil.endTransaction();
		return medium;
	}
	
	public static void increasePlayedofMedium(int id) throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		
		Medium medium = (Medium) session.get(Medium.class, id);
		
		/* increament played in Database */
		int played = medium.getAngehoert();
		played++;
		medium.setAngehoert(played);
		session.update(medium);
		
		HibernateUtil.endTransaction();
	}
	
	public static void saveMediumToDB(Medium m) throws Exception {
		int albumId = 0;
		
		Session session = HibernateUtil.prepareTransaction();
		
		/* if album is set save medium to album list
		 * and save it to DB
		 */
		if (m.getAlbum() != null) {
			albumId = m.getAlbum().getId();
			Album album = (Album) session.get(Album.class, albumId);
			album.getMedia().add(m);

			session.save(album);
		}
		
		session.save(m);
		
		HibernateUtil.endTransaction();
	}
	
	public static void saveAlbumToDB(Album a) throws Exception {

		Session session = HibernateUtil.prepareTransaction();
		session.save(a);
		HibernateUtil.endTransaction();
	}
	
	public static void saveTypeToDB(Type t) throws Exception {

		Session session = HibernateUtil.prepareTransaction();
		session.save(t);
		HibernateUtil.endTransaction();
	}
	
	public static List<Medium> getAllMedia() throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		Query query = session.createQuery("FROM Medium");
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();
		HibernateUtil.endTransaction();
		return media;
	}

	public static List<Type> getAllTypes() throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		Query query = session.createQuery("FROM Type");
		@SuppressWarnings("unchecked")
		List<Type> types = query.list();
		HibernateUtil.endTransaction();
		return types;
	}

	public static List<Album> getAllAlbums() throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		Query query = session.createQuery("FROM Album");
		@SuppressWarnings("unchecked")
		List<Album> albums = query.list();
		HibernateUtil.endTransaction();
		return albums;
	}

	public static List<Medium> getTopPlayedMedia(int x) throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		Query query = session.createQuery("FROM Medium ORDER BY Angehoert DESC");
		query.setFirstResult(0);
		query.setMaxResults(x);
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();
		HibernateUtil.endTransaction();
		return media;
	}

	public static List<Medium> getTopBoughtMedia(int limit) throws Exception {
		Session session = HibernateUtil.prepareTransaction();
		Query query = session.createQuery("FROM Medium WHERE Gekauft >= " + limit + "ORDER BY Gekauft DESC");
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();	
		HibernateUtil.endTransaction();
		return media;
	}
}
