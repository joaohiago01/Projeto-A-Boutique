package DAO;

import DTO.ClienteDTO;
import View.ClienteDuplicadoExcpetion;

public interface ClienteInterface {

	public void createCliente (ClienteDTO clienteDTO) throws ClienteDuplicadoExcpetion ;
	
	public void deleteCliente (ClienteDTO clienteDTO);
	
	public void updateCliente (ClienteDTO clienteDTO);
	
	public ClienteDTO readCliente (ClienteDTO clienteDTO);

	public ClienteDTO table();

	public ClienteDTO filtro(ClienteDTO clienteDTO);
	
}
