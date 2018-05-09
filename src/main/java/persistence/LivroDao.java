package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entity.Livro;
import entity.Status;

public class LivroDao
{
	Session session;
	Transaction transaction;
	Criteria criteria;
	
	public void save(Livro l) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.save(l);
			transaction.commit();
		session.close();
	}
	
	public void update(Livro l) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.update(l);
			transaction.commit();
		session.close();
	}
	
	public void delete(Livro l) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				session.delete(l);
			transaction.commit();
		session.close();
	}
	
//	public Livro getLivro(long id) 
//	{
//		session = HibernateUtil.getSessionFactory().openSession();
//		return (Livro) session.load(Livro.class, id);
//	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> findaAll()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try
		{
			return session.createCriteria(Livro.class)
					.addOrder(Order.asc("titulo"))
					.list();
		} 
		finally
		{
			session.close();
		}				
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> lendo()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try
		{
			Criteria consulta =  session.createCriteria(Livro.class);
				     consulta.add(Restrictions.eq("status", Status.LENDO));
					 consulta.addOrder(Order.asc("titulo") );
		 List<Livro> resultado = consulta.list();
					 
					 return resultado;
		} 
		finally
		{
			session.close();
		}				
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> sugerir()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try
		{
			return session.createCriteria(Livro.class)
					.addOrder(Order.asc("titulo"))
					.list();
		} 
		finally
		{
			session.close();
		}				
	}
	
}
