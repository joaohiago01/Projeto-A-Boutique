package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import DTO.FuncionarioDTO;
import View.FuncionarioDuplicadoException;
import View.FuncionarioNaoExisteException;

public class FuncionarioPostgreSQL {

	private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
	private ConnectionBD connectionBD;

	public FuncionarioPostgreSQL () {
		checkConnection();
	}

	public void checkConnection () {
		connectionBD = ConnectionBD.getInstance();
	}

	public void finishConnection () {
		connectionBD.closeConnection();
	}

	public void createFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioDuplicadoException, PSQLException {

		String nome = "'" + funcionarioDTO.getNome() + "'";
		String senha = "'" + funcionarioDTO.getSenha() + "'";
		String email = "'" + funcionarioDTO.getEmail() + "'";
		String telefone = "'" + funcionarioDTO.getTelefone() + "'";
		String cargo = "'" + funcionarioDTO.getCargo() + "'";
		String query = "( " + nome + ", " + senha + ", " + email + ", " + telefone + ", " + cargo + " )";

		if (connectionBD.isConnectionValid()) {
			try {
				boolean flag = true;
				ResultSet set = connectionBD.getConnection().executeQuery("select * from funcionario");
				while (set.next()) {
					if (set.getString(2).equalsIgnoreCase(funcionarioDTO.getNome())) {
						flag = false;
						throw new FuncionarioDuplicadoException();

					}
				}
				if (flag)
					connectionBD.getConnection().executeUpdate("insert into funcionario (nome, senha, email, telefone, cargo) values " + query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteFuncionario (FuncionarioDTO funcionarioDTO) {

		String id = "'" + funcionarioDTO.getIdFuncionario() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("delete from funcionario where id_funcionario = " + id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateFuncionario (FuncionarioDTO funcionarioDTO) {

		String id = "'" + funcionarioDTO.getIdFuncionario() + "'";
		String nome = "'" + funcionarioDTO.getNome() + "'";
		String senha = "'" + funcionarioDTO.getSenha() + "'";
		String email = "'" + funcionarioDTO.getEmail() + "'";
		String telefone = "'" + funcionarioDTO.getTelefone() + "'";
		String cargo = "'" + funcionarioDTO.getCargo() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				//ResultSet set = connectionBD.getConnection().executeQuery("select * from funcionario where id_funcionario = " + id);
				//while (set.next()) {
				connectionBD.getConnection().executeUpdate("update funcionario set email = " + email + " where id_funcionario = " + id);
				connectionBD.getConnection().executeUpdate("update funcionario set nome = " + nome + " where id_funcionario = " + id);
				connectionBD.getConnection().executeUpdate("update funcionario set senha = " + senha + " where id_funcionario = " + id);
				connectionBD.getConnection().executeUpdate("update funcionario set telefone = " + telefone + " where id_funcionario = " + id);
				connectionBD.getConnection().executeUpdate("update funcionario set cargo = " + cargo + " where id_funcionario = " + id);
				//}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public FuncionarioDTO readFuncionario (FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExisteException {

		String nome = "'" + funcionarioDTO.getNome() + "'";
		String id = "'" + funcionarioDTO.getIdFuncionario() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = null;
				if (funcionarioDTO.getIdFuncionario() == 0) {
					set = connectionBD.getConnection().executeQuery("select * from funcionario where nome = " + nome);	
				} else {
					set = connectionBD.getConnection().executeQuery("select * from funcionario where id_funcionario = " + id);
				}

				if (set.next()) {
					this.funcionarioDTO.setIdFuncionario(set.getInt(1));
					this.funcionarioDTO.setNome(set.getString(2));
					this.funcionarioDTO.setSenha(set.getString(3));
					this.funcionarioDTO.setEmail(set.getString(4));
					this.funcionarioDTO.setTelefone(set.getString(5));
					this.funcionarioDTO.setCargo(set.getString(6));
				} 

				if (this.funcionarioDTO.getNome() == null) {
					throw new FuncionarioNaoExisteException();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.funcionarioDTO;
	}

	public FuncionarioDTO table () {

		ArrayList<FuncionarioDTO> table = new ArrayList<FuncionarioDTO>();

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from funcionario order by id_funcionario");
				while (set.next()) {
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setIdFuncionario(set.getInt(1));
					funcionarioDTO.setNome(set.getString(2));
					funcionarioDTO.setSenha(set.getString(3));
					funcionarioDTO.setEmail(set.getString(4));
					funcionarioDTO.setTelefone(set.getString(5));
					funcionarioDTO.setCargo(set.getString(6));
					table.add(funcionarioDTO);
					if (funcionarioDTO.getIdFuncionario() == 0 && funcionarioDTO.getNome().equals("admin") && funcionarioDTO.getSenha().equals("admin")) {
						table.remove(0);
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (table.isEmpty()) {
			return null;
		} else {
			this.funcionarioDTO.setTable(table);
			return this.funcionarioDTO;
		}

	}

	public FuncionarioDTO filtro(FuncionarioDTO DTOfuncionario) {

		ArrayList<FuncionarioDTO> table = new ArrayList<FuncionarioDTO>();
		String nome = DTOfuncionario.getFiltro().toLowerCase();

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from funcionario where lower(nome) like " + "'%"+nome+"%'");
				while (set.next()) {
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setIdFuncionario(set.getInt(1));
					funcionarioDTO.setNome(set.getString(2));
					funcionarioDTO.setSenha(set.getString(3));
					funcionarioDTO.setEmail(set.getString(4));
					funcionarioDTO.setTelefone(set.getString(5));
					funcionarioDTO.setCargo(set.getString(6));
					table.add(funcionarioDTO);

					if (funcionarioDTO.getIdFuncionario() == 0 && funcionarioDTO.getNome().equals("admin") && funcionarioDTO.getSenha().equals("admin")) {
						table.remove(funcionarioDTO);
					}
				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (table.size() < 1) {
			return null;
		} else {
			this.funcionarioDTO.setTable(table);
			return this.funcionarioDTO;
		}
	}

}
