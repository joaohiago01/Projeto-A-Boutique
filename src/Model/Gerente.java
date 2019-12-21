package Model;

import org.postgresql.util.PSQLException;

import DAO.FuncionarioAdapterPostgreSQL;
import DAO.FuncionarioInterface;
import DTO.FuncionarioDTO;
import View.FuncionarioDuplicadoException;
import View.FuncionarioNaoExisteException;

public class Gerente {

	private FuncionarioInterface funcionarioInterface = new FuncionarioAdapterPostgreSQL();

	public void ativarPromocao () {

	}

	public void desativarPromocao() {

	}

	public void cadastrarVendedor (FuncionarioDTO funcionarioDTO) throws FuncionarioDuplicadoException, PSQLException {
		funcionarioInterface.createFuncionario(funcionarioDTO);
	}

	public void demitirVendedor (FuncionarioDTO funcionarioDTO) {
		funcionarioInterface.deleteFuncionario(funcionarioDTO);
	}

	public FuncionarioDTO recuperarFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExisteException {
		return funcionarioInterface.readFuncionario(funcionarioDTO);
	}

	public void atualizarFuncionario (FuncionarioDTO funcionarioDTO) {
		funcionarioInterface.updateFuncionario(funcionarioDTO);
	}

	public void fecharConexao() {
		funcionarioInterface.finishConnection();
	}

	public FuncionarioDTO tableFuncionarios() {
		return funcionarioInterface.table();
	}

	public FuncionarioDTO filtro (FuncionarioDTO funcionarioDTO) {
		return funcionarioInterface.filtro(funcionarioDTO);
	}
}
