package DTO;

import java.util.ArrayList;

public class ContabilidadeDTO {

	private String mesAtual;
	private float lucroMensal;
	private int qtdVendidos;
	private int id_venda;
	private ArrayList<ContabilidadeDTO> table;
	
	public String getMesAtual() {
		return mesAtual;
	}
	public void setMesAtual(String mesAtual) {
		this.mesAtual = mesAtual;
	}
	public float getLucroMensal() {
		return lucroMensal;
	}
	public void setLucroMensal(float lucroMensal) {
		this.lucroMensal = lucroMensal;
	}
	public int getQtdVendidos() {
		return qtdVendidos;
	}
	public void setQtdVendidos(int qtdVendidos) {
		this.qtdVendidos = qtdVendidos;
	}
	public ArrayList<ContabilidadeDTO> getTable() {
		return table;
	}
	public void setTable(ArrayList<ContabilidadeDTO> table) {
		this.table = table;
	}
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	
}
