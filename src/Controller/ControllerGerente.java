package Controller;

import org.postgresql.util.PSQLException;

import DTO.FuncionarioDTO;
import Model.Gerente;
import View.FuncionarioDuplicadoException;
import View.FuncionarioNaoExisteException;

public class ControllerGerente {

	private Gerente gerente;
	
	public ControllerGerente () {
		this.gerente = new Gerente();
	}
	
	public void ativarPromocao () {
		gerente.ativarPromocao();
	}
	
	public void desativarPromocao () {
		gerente.desativarPromocao();
	}
	
	public void cadastrarVendedor (FuncionarioDTO funcionarioDTO) throws FuncionarioDuplicadoException, PSQLException {
		gerente.cadastrarVendedor(funcionarioDTO);
	}
	
	public void demitirVendedor (FuncionarioDTO funcionarioDTO) {
		gerente.demitirVendedor(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExisteException {
		return gerente.recuperarFuncionario(funcionarioDTO);
	}
	
	public void atualizarFuncionario (FuncionarioDTO funcionarioDTO) {
		gerente.atualizarFuncionario(funcionarioDTO);;
	}

	public void fecharConexao() {
		gerente.fecharConexao();
	}

	public FuncionarioDTO tableFuncionarios() {
		return gerente.tableFuncionarios();
	}

	public FuncionarioDTO filtroFuncionario(FuncionarioDTO funcionarioDTO) {
		return gerente.filtro(funcionarioDTO);
	}
}
