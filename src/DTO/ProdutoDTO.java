package DTO;

public class ProdutoDTO {

	private int id_produto;
	private String nome;
	private String descricao;
	private float preco;
	private float precoPorQuant;
	private int qtdEstoque;
	private int qtdPedida;
	private String[] table;
	private String filtro;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String[] getTable() {
		return table;
	}
	public void setTable(String[] table) {
		this.table = table;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public int getQtdPedida() {
		return qtdPedida;
	}
	public void setQtdPedida(int qtdPedida) {
		this.qtdPedida = qtdPedida;
	}
	public float getPrecoPorQuant() {
		return precoPorQuant;
	}
	public void setPrecoPorQuant(float precoPorQuant) {
		this.precoPorQuant = precoPorQuant;
	}
	
	
}
