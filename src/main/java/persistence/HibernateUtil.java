
package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			Configuration cfg = new Configuration();
			cfg.configure("config/hibernate.cfg.xml");
			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(cfg.getProperties() );
			StandardServiceRegistry servico = registradorServico.build();
			
			return cfg.buildSessionFactory(servico);
		} 
		catch (Throwable ex)
		{
			System.out.println("Falhou: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
		public static SessionFactory getSessionFactory()
		{
			return sessionFactory;
		}
		
//		public static Session getSession()
//		{
//			return HibernateUtil.getSessionFactory().openSession();
//		}
//		
//		public static Session closeSession(Session session)
//		{
//			return (Session) session.close();
//		}
	}
