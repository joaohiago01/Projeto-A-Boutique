package DAO;

import org.postgresql.util.PSQLException;

import DTO.FuncionarioDTO;
import View.FuncionarioDuplicadoException;
import View.FuncionarioNaoExisteException;

public interface FuncionarioInterface {

	public void createFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioDuplicadoException, PSQLException;

	public void deleteFuncionario (FuncionarioDTO funcionarioDTO);

	public void updateFuncionario (FuncionarioDTO funcionarioDTO);

	public FuncionarioDTO readFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExisteException ;

	public void finishConnection();
	
	public FuncionarioDTO table();
	
	public FuncionarioDTO filtro(FuncionarioDTO funcionarioDTO);
	
}
