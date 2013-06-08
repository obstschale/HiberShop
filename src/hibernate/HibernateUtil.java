package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
 
 
public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
	private static Session session;
	private static Transaction transaction;

    static
    {
        try
        {
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
        	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (HibernateException ex)
        {
            System.out.println(ex.getMessage());            
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    

	public static Session prepareTransaction() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		return session;
	}
	
	public static void endTransaction() {
		transaction.commit();
	}
}