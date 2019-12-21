package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ClienteDTO;
import View.ClienteDuplicadoExcpetion;

public class ClientePostgreSQL {

	private ClienteDTO clienteDTO = new ClienteDTO();
	private ConnectionBD connectionBD;

	public ClientePostgreSQL () {
		checkConnection();
	}

	public void checkConnection () {
		connectionBD = ConnectionBD.getInstance();
	}

	public void finishConnection () {
		connectionBD.closeConnection();
	}

	public void createCliente (ClienteDTO clienteDTO) throws ClienteDuplicadoExcpetion {

		String nome = "'" + clienteDTO.getNome() +"'";
		String email = "'" + clienteDTO.getEmail() + "'";
		String telefone = "'" + clienteDTO.getTelefone() + "'";
		String query = "( " + nome + ", " + email + ", " + telefone + " )";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from cliente");
				boolean flag = true;
				while (set.next()) {
					if (set.getString(2).equalsIgnoreCase(clienteDTO.getNome())) {
						flag = false;
						throw new ClienteDuplicadoExcpetion();
					}
				}
				if (flag)
					connectionBD.getConnection().executeUpdate("insert into cliente (nome, email, telefone) values " + query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void deleteCliente (ClienteDTO clienteDTO) {

		String id = "'"+ clienteDTO.getIdCliente() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("delete from cliente where id_cliente = " + id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateCliente (ClienteDTO clienteDTO) {

		String id = "'"+ clienteDTO.getIdCliente() + "'";
		String nome = "'" + clienteDTO.getNome() +"'";
		String email = "'" + clienteDTO.getEmail() + "'";
		String telefone = "'" + clienteDTO.getTelefone() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from cliente where id_cliente = " + id);
				if (set.next()) {
					connectionBD.getConnection().executeUpdate("update cliente set nome = " + nome + " where id_cliente = " + id);
					connectionBD.getConnection().executeUpdate("update cliente set email = " + email + " where id_cliente = " + id);
					connectionBD.getConnection().executeUpdate("update cliente set telefone = " + telefone + " where id_cliente = " + id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ClienteDTO readCliente (ClienteDTO clienteDTO) {

		String id = "'"+ clienteDTO.getIdCliente() + "'";

		if (connectionBD.isConnectionValid()) {
			ResultSet set;
			try {
				set = connectionBD.getConnection().executeQuery("select * from cliente where id_cliente = " + id);
				if (set.next()) {
					this.clienteDTO.setIdCliente(set.getInt(1));
					this.clienteDTO.setNome(set.getString(2));
					this.clienteDTO.setEmail(set.getString(3));
					this.clienteDTO.setTelefone(set.getString(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.clienteDTO;
	}

	public ClienteDTO table() {

		String tableCliente = "";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from cliente");
				while (set.next()) {
					tableCliente += set.getInt(1) + "%" + set.getString(2) + "#";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (tableCliente.equals("")) {
			return null;
		} else {
			String table[] = tableCliente.split("#");
			this.clienteDTO.setTable(table);
			return this.clienteDTO;
		}
	}

	public ClienteDTO filtro (ClienteDTO clienteDTO) {

		String tableCliente = "";
		String nome = "";
		if (clienteDTO.getFiltro().equals("")) {
			nome = "";
		} else {
			nome = clienteDTO.getFiltro().toLowerCase();
		}

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = null;
				try {
					if (Integer.parseInt(clienteDTO.getFiltro()) == clienteDTO.getIdCliente())
						set = connectionBD.getConnection().executeQuery("select * from cliente where id_cliente = " + "'"+clienteDTO.getFiltro()+"'");		
				} catch (NumberFormatException e) {set = connectionBD.getConnection().executeQuery("select * from cliente where lower(nome) like " + "'%"+nome+"%'");}

				while (set.next()) {
					tableCliente += set.getInt(1) + "%" + set.getString(2) + "#";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (tableCliente.equals("")) {
			return null;
		} else {
			String table[] = tableCliente.split("#");
			this.clienteDTO.setTable(table);
			return this.clienteDTO;
		}
	}
}
