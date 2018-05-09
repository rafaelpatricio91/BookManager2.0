package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Period;

import entity.Autor;
import entity.Livro;
import entity.Status;
import persistence.LivroDao;
import util.AddMensagem;

@ManagedBean
@ViewScoped
public class BeanLeituras implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public String teste = "PORRA";
	
	private Autor autor = new Autor();
	private Livro livro = new Livro();
	private Livro livroSelecionado = new Livro();
	
	private List<Livro> listaLivros = new ArrayList<Livro>();
	private List<Livro> listaLivrosLendo = new ArrayList<Livro>();
	private List<Livro> listaLivrosSugestao = new ArrayList<Livro>();
	
	@PostConstruct
	public void init()
	{
		this.livro = new Livro();
		this.livroSelecionado = new Livro();
		
		this.listaLivros = new LivroDao().findaAll();
		this.listaLivrosLendo = new LivroDao().lendo();
		this.listaLivrosSugestao = new ArrayList<Livro>();
		
		this.listaLivrosSugestao = this.montarSugestao();
		this.listaLivros = new LivroDao().findaAll();
		
	}
	
//	public List<Livro> montarSugestao2(List<Livro> lista)
//	{
//		lista = new ArrayList<Livro>();
//		final int QTDLIVROSUGESTAO = 5;
//		Collections.shuffle(listaLivros);
//		
//		for (int i = 1; i <= QTDLIVROSUGESTAO; i++)
//		{
//			Livro l = new Livro();
//			l = listaLivros.get(i);
//			System.out.println("L: " + l);
//			lista.add(l);
//		}
//		return lista;
//	}
	
	public List<Livro> montarSugestao()
	{
		final int QTDLIVROSUGESTAO = 10;
		Collections.shuffle(listaLivros);
		
		for (int i = 1; i <= QTDLIVROSUGESTAO; i++)
		{
			Livro l = new Livro();
			l = listaLivros.get(i);
			System.out.println("L: " + l);
			listaLivrosSugestao.add(l);
		}
		
		return listaLivrosSugestao;
	}
	
	public void iniciarLeitura()
	{
		FacesMessage msg = null;
		
		try
		{
			this.livroSelecionado.setStatus(Status.LENDO);
			new LivroDao().update(livroSelecionado);
			msg = new FacesMessage("Boa Leitura!");
			AddMensagem.addMsgINFO(msg);
			this.init();
		} 
		catch (Exception e)
		{
			msg = new FacesMessage("Erro ao Iniciar Leitura");
			e.printStackTrace();
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void concluirLeitura()
	{
		FacesMessage msg = null;
		
		
		
		try
		{
			this.livroSelecionado.setStatus(Status.LIDO);
			this.estatisticasConclusao();
			new LivroDao().update(livroSelecionado);
			msg = new FacesMessage("Leitura Concluída!");
			AddMensagem.addMsgINFO(msg);
			
			this.init();
			
//			System.out.println("****************LIDO EM DIAS: " + this.livroSelecionado.getLidoEmDias());
//			System.out.println("****************PG P/ DIA: " + this.livroSelecionado.getPagPorDia());
		} 
		catch (Exception e)
		{
			msg = new FacesMessage("Erro ao Concluir Leitura");
			e.printStackTrace();
			AddMensagem.addMsgERRO(msg);
		}
	}
	
	public void estatisticasConclusao()
	{		
//		DateTime dataInicial = new DateTime(livroSelecionado.getDataInicio());
//		DateTime dataFinal = new DateTime(livroSelecionado.getDataTermino());
		
//		DateTime dataInicial = new DateTime(2011,1,1,0,0);
//		DateTime dataFinal = new DateTime(2011,2,1,0,0);
		
//		Interval intervalo = new Interval(dataInicial, dataFinal);
//		Period periodo = intervalo.toPeriod();
		DateTime dataInicial = new DateTime(this.livroSelecionado.getDataInicio());
		DateTime dataFinal = new DateTime(this.livroSelecionado.getDataTermino());
		
		try
		{
			this.livroSelecionado.setLidoEmDias(daysBetweenDates(dataInicial, dataFinal));
			this.livroSelecionado.setPagPorDia(quantasPaginasPorDia(this.livroSelecionado.getNumPaginas()));
			
			this.init();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int quantasPaginasPorDia(Integer qtdPaginas)
	{
		DateTime dataInicial = new DateTime(livroSelecionado.getDataInicio());
		DateTime dataFinal = new DateTime(livroSelecionado.getDataTermino());
		
		try
		{
			return
					( (this.livroSelecionado.getNumPaginas() ) / daysBetweenDates(dataInicial, dataFinal) ); 
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("*************ERRO QUANTAS PGS POR DIA");
			
			return 0;
		}
	}
	
	public int daysBetweenDates(DateTime dataInicial, DateTime dataFinal)
	{
		Days dias = Days.daysBetween(dataInicial, dataFinal);
		
		try
		{
			return dias.getDays();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("******ERRO DAYSBETWEENDATES");
			
			return 0;
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

	public List<Livro> getListaLivros()
	{
		return listaLivros;
	}

	public void setListaLivros(List<Livro> listaLivros)
	{
		this.listaLivros = listaLivros;
	}

	public List<Livro> getListaLivrosLendo()
	{
		return listaLivrosLendo;
	}

	public void setListaLivrosLendo(List<Livro> listaLivrosLendo)
	{
		this.listaLivrosLendo = listaLivrosLendo;
	}

	public List<Livro> getListaLivrosSugestao()
	{
		return listaLivrosSugestao;
	}

	public void setListaLivrosSugestao(List<Livro> listaLivrosSugestao)
	{
		this.listaLivrosSugestao = listaLivrosSugestao;
	}
	
	public static void main(String[] args)
	{
		DateTime dataInicial = new DateTime(2011,1,1,0,0);
		DateTime dataFinal = new DateTime(2012,1,1,0,0);
		BeanLeituras bl = new BeanLeituras();
		System.out.println(bl.daysBetweenDates(dataInicial, dataFinal));
	}
	
}
