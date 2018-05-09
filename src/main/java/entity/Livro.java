package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
@Table
public class Livro implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer idLivro;
	@Column
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="id_autor")
	private Autor autor;
	
	@Column
	private Integer numPaginas;
	@Column
	private Double skoob;
	@Column
	private TipoMidia midia;
	@Column
	private String genero; 
	@Column
	private String sinopse;
	@Column
	private String opiniao;
	@Column
	private Classificacao avaliacao;
	@Column
	private Status status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dataTermino;
	
	@Transient
	private Integer lidoEmDias;
	@Transient
	private Integer pagPorDia;
	
	
	public Integer getIdLivro()
	{
		return idLivro;
	}
	public void setIdLivro(Integer idLivro)
	{
		this.idLivro = idLivro;
	}
	public String getTitulo()
	{
		return titulo;
	}
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	public Autor getAutor()
	{
		return autor;
	}
	public void setAutor(Autor autor)
	{
		this.autor = autor;
	}
	public Integer getNumPaginas()
	{
		return numPaginas;
	}
	public void setNumPaginas(Integer numPaginas)
	{
		this.numPaginas = numPaginas;
	}
	public Double getSkoob()
	{
		return skoob;
	}
	public void setSkoob(Double skoob)
	{
		this.skoob = skoob;
	}
	
	public TipoMidia getMidia()
	{
		return midia;
	}
	public void setMidia(TipoMidia midia)
	{
		this.midia = midia;
	}
	
	public String getGenero()
	{
		return genero;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	public String getSinopse()
	{
		return sinopse;
	}
	public void setSinopse(String sinopse)
	{
		this.sinopse = sinopse;
	}
	public String getOpiniao()
	{
		return opiniao;
	}
	public void setOpiniao(String opiniao)
	{
		this.opiniao = opiniao;
	}
	public Classificacao getAvaliacao()
	{
		return avaliacao;
	}
	public void setAvaliacao(Classificacao avaliacao)
	{
		this.avaliacao = avaliacao;
	}
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}

	public Date getDataInicio()
	{
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio)
	{
		this.dataInicio = dataInicio;
	}
	public Date getDataTermino()
	{
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino)
	{
		this.dataTermino = dataTermino;
	}
	public Integer getLidoEmDias()
	{
		return lidoEmDias;
	}
	public void setLidoEmDias(Integer lidoEmDias)
	{
		this.lidoEmDias = lidoEmDias;
	}
	public Integer getPagPorDia()
	{
		return pagPorDia;
	}
	public void setPagPorDia(Integer pagPorDia)
	{
		this.pagPorDia = pagPorDia;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLivro == null) ? 0 : idLivro.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (idLivro == null)
		{
			if (other.idLivro != null)
				return false;
		} else if (!idLivro.equals(other.idLivro))
			return false;
		return true;
	}
}
