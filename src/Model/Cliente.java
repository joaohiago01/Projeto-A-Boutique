package Model;

import DAO.ClienteAdapterPostgreSQL;
import DAO.ClienteInterface;
import DTO.ClienteDTO;
import View.ClienteDuplicadoExcpetion;

public class Cliente {

	private ClienteInterface clienteInterface = new ClienteAdapterPostgreSQL();
	
	public void cadastrarCliente (ClienteDTO clienteDTO) throws ClienteDuplicadoExcpetion {
		clienteInterface.createCliente(clienteDTO);
	}
	
	public void atualizarCliente (ClienteDTO clienteDTO) {
		clienteInterface.updateCliente(clienteDTO);
	}
	
	public ClienteDTO recuperarCliente (ClienteDTO clienteDTO) {
		return clienteInterface.readCliente(clienteDTO);
	}
	
	public void deletarCliente (ClienteDTO clienteDTO) {
		clienteInterface.deleteCliente(clienteDTO);
	}

	public ClienteDTO tableCliente() {
		return clienteInterface.table();
	}

	public ClienteDTO filtroCliente(ClienteDTO clienteDTO) {
		return clienteInterface.filtro(clienteDTO);
	}
}
