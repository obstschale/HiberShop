package view;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Medium;
import model.Type;
import model.Album;

public class DatabaseQueries {
	
	public List<Medium> getAllMedia(Session session, Transaction transaction) {
		Query query = session.createQuery("FROM Medium");
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();
		return media;
	}

	public List<Type> getAllTypes(Session session, Transaction transaction) {
		Query query = session.createQuery("FROM Type");
		@SuppressWarnings("unchecked")
		List<Type> types = query.list();
		return types;
	}

	public List<Album> getAllAlbums(Session session, Transaction transaction) {
		Query query = session.createQuery("FROM Album");
		@SuppressWarnings("unchecked")
		List<Album> albums = query.list();
		return albums;
	}

	public List<Medium> getTopPlayedMedia(Session session, Transaction transaction, int x) {
		Query query = session.createQuery("FROM Medium ORDER BY Angehoert DESC");
		query.setFirstResult(0);
		query.setMaxResults(x);
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();
		return media;
	}

	public List<Medium> getTopBoughtMedia(Session session, Transaction transaction, int limit) {
		Query query = session.createQuery("FROM Medium WHERE Gekauft >= " + limit + "ORDER BY Gekauft DESC");
		@SuppressWarnings("unchecked")
		List<Medium> media = query.list();	
		return media;
	}
}
