package DAO;

import DTO.ClienteDTO;
import View.ClienteDuplicadoExcpetion;

public class ClienteAdapterPostgreSQL extends ClientePostgreSQL implements ClienteInterface {

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
	public void createCliente(ClienteDTO clienteDTO) throws ClienteDuplicadoExcpetion {
		// TODO Auto-generated method stub
		super.createCliente(clienteDTO);
	}

	@Override
	public void deleteCliente(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub
		super.deleteCliente(clienteDTO);
	}

	@Override
	public void updateCliente(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub
		super.updateCliente(clienteDTO);
	}

	@Override
	public ClienteDTO readCliente(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub
		return super.readCliente(clienteDTO);
	}

	@Override
	public ClienteDTO table() {
		// TODO Auto-generated method stub
		return super.table();
	}

	@Override
	public ClienteDTO filtro(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub
		return super.filtro(clienteDTO);
	}

	
}
