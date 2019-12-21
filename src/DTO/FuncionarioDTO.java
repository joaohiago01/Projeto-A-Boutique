package DTO;

import java.util.ArrayList;

public class FuncionarioDTO {

	private String nome;
	private String senha;
	private String telefone;
	private String email;
	private String cargo;
	private int idFuncionario;
	private ArrayList<FuncionarioDTO> table;
	private String filtro;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public ArrayList<FuncionarioDTO> getTable() {
		return table;
	}
	public void setTable(ArrayList<FuncionarioDTO> table) {
		this.table = table;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}
