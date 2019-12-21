package DAO;

import org.postgresql.util.PSQLException;

import DTO.FuncionarioDTO;
import View.FuncionarioDuplicadoException;
import View.FuncionarioNaoExisteException;

public class FuncionarioAdapterPostgreSQL extends FuncionarioPostgreSQL implements FuncionarioInterface {

	@Override
	public void checkConnection() {
		// TODO Auto-generated method stub
		super.checkConnection();
	}

	@Override
	public void finishConnection() {
		// TODO Auto-generated method stub
		super.finishConnection();
	}

	@Override
	public void createFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioDuplicadoException, PSQLException {
		// TODO Auto-generated method stub
		super.createFuncionario(funcionarioDTO);
	}

	@Override
	public void deleteFuncionario(FuncionarioDTO funcionarioDTO) {
		// TODO Auto-generated method stub
		super.deleteFuncionario(funcionarioDTO);
	}

	@Override
	public void updateFuncionario(FuncionarioDTO funcionarioDTO) {
		// TODO Auto-generated method stub
		super.updateFuncionario(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO readFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExisteException {
		// TODO Auto-generated method stub
		return super.readFuncionario(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO table() {
		// TODO Auto-generated method stub
		return super.table();
	}

	@Override
	public FuncionarioDTO filtro(FuncionarioDTO DTOfuncionario) {
		// TODO Auto-generated method stub
		return super.filtro(DTOfuncionario);
	}

	
}
