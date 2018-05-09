package converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import entity.Autor;
import persistence.HibernateUtil;

@FacesConverter(forClass = Autor.class)
public class AutorConverter implements Converter
{

	public Object getAsObject(FacesContext fc, UIComponent ui, String value)
	{
		Autor retorno = null;
		Session session = null;
		
		if (value != null)
		{
			try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				
					return retorno = (Autor) session.get(Autor.class, new Integer(value) );
			} 
			
			catch (Exception e)
			{
				System.out.println("Erro no converter ");
			}
			
			finally 
			{
				session.close();
			}
		}
		
		return null;
			
	}

	public String getAsString(FacesContext fc, UIComponent ui, Object value)
	{
		if (value != null)
		{
			return ( (Autor) value).getIdAutor().toString();
		}
		
		return null;
	}

}
