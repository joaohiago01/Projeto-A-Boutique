package DTO;

import java.util.ArrayList;

public class VendaDTO {

	private int idVenda;
	private int idCliente;
	private int idVendedor;
	private float precoTotal;
	private String data_venda;
	private ArrayList<ProdutoDTO> produtos;
	private String[] table;
	private String vendedor;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public float getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	public ArrayList<ProdutoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	public String getData_venda() {
		return data_venda;
	}
	public void setData_venda(String data_venda) {
		this.data_venda = data_venda;
	}
	public String[] getTable() {
		return table;
	}
	public void setTable(String[] table) {
		this.table = table;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
		
}
