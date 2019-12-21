package DAO;

import DTO.FuncionarioDTO;
import DTO.VendaDTO;

public interface VendaInterface {

	public VendaDTO createVenda (VendaDTO vendaDTO);
	
	public void deleteVenda (VendaDTO vendaDTO);
	
	//public void updateVenda (VendaDTO vendaDTO);
	
	public VendaDTO readVenda (VendaDTO vendaDTO);

	public VendaDTO PDF(FuncionarioDTO funcionarioDTO, String opcs);
	
}
