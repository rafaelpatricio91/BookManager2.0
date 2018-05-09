package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import entity.Autor;
public class AutorDao 
{
	Session session;
	Transaction transaction;
	Criteria criteria;
	
	@SuppressWarnings("unchecked")
	public List<Autor> findAll()
	{
		session = HibernateUtil.getSessionFactory().openSession();
		
		try
		{
			return session.createCriteria(Autor.class)
					.addOrder(Order.asc ("nome") )
					.list();
		}
		
		finally 
		{
			session.close();
		}
	
	}
	
	public void save(Autor a) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.save(a);
			transaction.commit();
		session.close();
	}
	
	public void delete(Autor a) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.delete(a);
			transaction.commit();
		session.close();
	}
	
	public void update(Autor a) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.update(a);
			transaction.commit();
		session.close();
	}
	
	
}


