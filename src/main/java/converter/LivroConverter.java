package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import entity.Livro;
import persistence.HibernateUtil;

@FacesConverter(forClass=Livro.class)
public class LivroConverter implements Converter
{
	public Object getAsObject(FacesContext fc, UIComponent ui, String value)
	{
		Livro retorno = null;
		Session session = null;
		
		if (value != null && !value.equals("") )
		{
			try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				
					return retorno = (Livro) session.get(Livro.class, new Integer(value) );
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
		
		if (retorno == null)
		{
			String msg = "Livro não existe";
			FacesContext f = FacesContext.getCurrentInstance();
			
			f.addMessage(null, new FacesMessage(msg) );
		}
		
		return retorno;
			
	}

	public String getAsString(FacesContext fc, UIComponent ui, Object value)
	{
		if (value != null)
		{
			Integer codigo = ( (Livro) value).getIdLivro();
			return codigo == null ? "" : codigo.toString();
		}
		
		return null;
	}
}
