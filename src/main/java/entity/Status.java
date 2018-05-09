package entity;

public enum Status
{
	NAO_LIDO("Não lido"),
	LENDO("Lendo"),
	LIDO("Lido"),
	ABANDONADO("Abandonado"),
	RELENDO("Relendo");
	
	public String descricao;

	public String getDescricao()
	{
		return descricao;
	}

	private Status(String descricao)
	{
		this.descricao = descricao;
	}
	
}
