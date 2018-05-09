package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import entity.TipoMidia;
import persistence.HibernateUtil;

//@FacesConverter(forClass=TipoMidia.class)
public class TipoMidiaConverter implements Converter
{
	public Object getAsObject(FacesContext fc, UIComponent ui, String value)
	{
		TipoMidia retorno = null;
		Session session = null;
		
		if (value != null)
		{
			try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				
					return retorno = (TipoMidia) session.get(TipoMidia.class, new String(value) );
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
		
		return retorno;
			
	}

	public String getAsString(FacesContext fc, UIComponent ui, Object value)
	{
		if (value != null)
		{
			return ( (TipoMidia) value).getDescricao().toString();
		}
		
		return null;
	}
}
