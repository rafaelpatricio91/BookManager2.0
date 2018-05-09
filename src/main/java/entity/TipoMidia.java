package entity;

public enum TipoMidia
{
	FISICO("Física"),
	KINDLE("Kindle"),
	PDF("PDF"),
	OUTRO("Outro");
	
	private String descricao;

	private TipoMidia(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

}
