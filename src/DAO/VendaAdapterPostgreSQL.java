package DAO;

import DTO.FuncionarioDTO;
import DTO.VendaDTO;

public class VendaAdapterPostgreSQL extends VendaPostgreSQL implements VendaInterface {

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
	public VendaDTO createVenda(VendaDTO vendaDTO) {
		// TODO Auto-generated method stub
		return super.createVenda(vendaDTO);
	}

	@Override
	public void deleteVenda(VendaDTO vendaDTO) {
		// TODO Auto-generated method stub
		super.deleteVenda(vendaDTO);
	}

	/*@Override
	public void updateVenda(VendaDTO vendaDTO) {
		// TODO Auto-generated method stub
		super.updateVenda(vendaDTO);
	}*/

	@Override
	public VendaDTO readVenda(VendaDTO vendaDTO) {
		// TODO Auto-generated method stub
		return super.readVenda(vendaDTO);
	}

	@Override
	public VendaDTO PDF(FuncionarioDTO funcionarioDTO, String dataOpc) {
		// TODO Auto-generated method stub
		return super.PDF(funcionarioDTO, dataOpc);
	}
	
}
