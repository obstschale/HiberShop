package tests;

import hibernate.HibernateUtil;

import org.hibernate.SessionFactory;

public class Tests {
	
	public static void main(String[] args) {
		TypeTest typeTest = new TypeTest();
		AlbumTest albumTest = new AlbumTest();
		MediumTest mediumTest = new MediumTest();
		
		/** setting up Hibernate SessionFactory **/
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		typeTest.typeTests(sf);
		albumTest.albumTests(sf);
		mediumTest.mediumTests(sf);
		
	}
}
