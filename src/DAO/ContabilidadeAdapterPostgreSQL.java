package DAO;

import DTO.ContabilidadeDTO;
import DTO.VendaDTO;

public class ContabilidadeAdapterPostgreSQL extends ContabilidadePostgreSQL implements ContabilidadeInterface {

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
	public void createContabilidade(ContabilidadeDTO contabilidadeDTO) {
		// TODO Auto-generated method stub
		super.createContabilidade(contabilidadeDTO);
	}

	/*@Override
	public void deleteContabilidade(ContabilidadeDTO contabilidadeDTO) {
		// TODO Auto-generated method stub
		super.deleteContabilidade(contabilidadeDTO);
	}*/

	@Override
	public void updateContabilidade(ContabilidadeDTO contabilidadeDTO) {
		// TODO Auto-generated method stub
		super.updateContabilidade(contabilidadeDTO);
	}

	@Override
	public ContabilidadeDTO readContabilidade(ContabilidadeDTO contabilidadeDTO) {
		// TODO Auto-generated method stub
		return super.readContabilidade(contabilidadeDTO);
	}

	@Override
	public ContabilidadeDTO table() {
		// TODO Auto-generated method stub
		return super.table();
	}

	@Override
	public String dateNow(VendaDTO vendaDTO) {
		// TODO Auto-generated method stub
		return super.dateNow(vendaDTO);
	}
	
	
}
