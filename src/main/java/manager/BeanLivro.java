package manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Autor;
import entity.Livro;
import entity.Status;
import entity.TipoMidia;
import persistence.AutorDao;
import persistence.LivroDao;
import util.AddMensagem;

@ManagedBean(name="livroMB")
@ViewScoped
public class BeanLivro
{
	private Autor autor;
	private Livro livro;
	private Livro livroSelecionado;
	private List<Autor> listaAutores;
	private List<Livro> listaLivros;
	
	@PostConstruct
	public void init()
	{
		this.autor = new Autor();
		this.livro = new Livro();
		this.livroSelecionado = new Livro();
		
		this.listaAutores = new ArrayList<Autor>();
		this.listaAutores = new AutorDao().findAll();
		
		this.listaLivros = new ArrayList<Livro>();
		this.listaLivros = new LivroDao().findaAll();
	}
	
	public void cadastrar()
	{
		FacesMessage msg = null;
		
		try
		{
			msg = new FacesMessage("Livro cadastrado com sucesso"); 
			new LivroDao().save(livro);
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception e)
		{
			msg = new FacesMessage("Erro ao cadastrar o livro");
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void atualizar()
	{
		FacesMessage msg = null;
		
		try
		{
			msg = new FacesMessage("Livro alterado com sucesso"); 
			new LivroDao().update(livroSelecionado);
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception e)
		{
			msg = new FacesMessage("Erro ao atualizar o livro");
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void remover()
	{
		FacesMessage msg = null;
		
		try
		{
			msg = new FacesMessage("Livro removido com sucesso"); 
			new LivroDao().delete(livroSelecionado);
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception e)
		{
			msg = new FacesMessage("Erro ao remover o livro");
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

	public Livro getLivro()
	{
		return livro;
	}

	public void setLivro(Livro livro)
	{
		this.livro = livro;
	}

	public Livro getLivroSelecionado()
	{
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado)
	{
		this.livroSelecionado = livroSelecionado;
	}

	public List<Autor> getListaAutores()
	{
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores)
	{
		this.listaAutores = listaAutores;
	}

	public List<Livro> getListaLivros()
	{
		return listaLivros;
	}

	public void setListaLivros(List<Livro> listaLivros)
	{
		this.listaLivros = listaLivros;
	}
	
	public Status[] getStatus()
	{
		return Status.values();
	}
	
	public TipoMidia[] getTipoMidia()
	{
		return TipoMidia.values();
	}
	
}
