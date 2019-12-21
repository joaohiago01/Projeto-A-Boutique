package Controller;

import DTO.ClienteDTO;
import Model.Cliente;
import View.ClienteDuplicadoExcpetion;

public class ControllerCliente {

	private Cliente cliente;
	
	public ControllerCliente () {
		this.cliente = new Cliente();
	}
	
	public void cadastrarCliente (ClienteDTO clienteDTO) throws ClienteDuplicadoExcpetion {
		cliente.cadastrarCliente(clienteDTO);
	}
	
	public void atualizarCliente (ClienteDTO clienteDTO) {
		cliente.atualizarCliente(clienteDTO);
	}
	
	public ClienteDTO recuperarCliente (ClienteDTO clienteDTO) {
		return cliente.recuperarCliente(clienteDTO);
	}
	
	public void deletarCliente (ClienteDTO clienteDTO) {
		cliente.deletarCliente(clienteDTO);
	}

	public ClienteDTO tableCliente() {
		return cliente.tableCliente();
	}

	public ClienteDTO filtroCliente(ClienteDTO clienteDTO) {
		return cliente.filtroCliente(clienteDTO);
	}
}
