package manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Autor;
import persistence.AutorDao;
import util.AddMensagem;

@ManagedBean
@ViewScoped
public class BeanAutor
{
	private Autor autor;
	private Autor autorSelecionado;
	private List<Autor> listaAutores;
	
	@PostConstruct
	public void init()
	{
		this.autor = new Autor();
		this.autorSelecionado = new Autor();
		this.listaAutores = new ArrayList<Autor>();
		this.listaAutores = new AutorDao().findAll();
	}
	
	public void cadastrar()
	{
		FacesMessage msg = null;
		
		try
		{
			new AutorDao().save(autor);
			msg = new FacesMessage("Autor(a) cadastrado com sucesso");
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			msg = new FacesMessage("Erro ao cadastrar");
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void atualizar()
	{
		FacesMessage msg = null;
		
		try
		{
			new AutorDao().update(autorSelecionado);
			msg = new FacesMessage("Autor(a) atualizado com sucesso");
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			msg = new FacesMessage("Erro ao atualizar");
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void remover()
	{
		FacesMessage msg = null;
		
		try
		{
			new AutorDao().delete(autorSelecionado);
			msg = new FacesMessage("Autor(a) removido com sucesso");
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			msg = new FacesMessage("Erro ao remover");
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public Autor getAutor()
	{
		return autor;
	}
	public void setAutor(Autor autor)
	{
		this.autor = autor;
	}
	public List<Autor> getListaAutores()
	{
		return listaAutores;
	}
	public void setListaAutores(List<Autor> listaAutores)
	{
		this.listaAutores = listaAutores;
	}

	public Autor getAutorSelecionado()
	{
		return autorSelecionado;
	}

	public void setAutorSelecionado(Autor autorSelecionado)
	{
		this.autorSelecionado = autorSelecionado;
	}
	
}
