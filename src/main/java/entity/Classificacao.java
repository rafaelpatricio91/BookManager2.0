package entity;

public enum Classificacao
{
	EXCELENTE("Excelente"),
	MUITO_BOM("Muito bom"),
	BOM("Bom"),
	RUIM("Ruim"),
	PESSIMO("Péssimo");
	
	private String descricao;
	
	private Classificacao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
	
}
