package DAO;

import DTO.ContabilidadeDTO;
import DTO.VendaDTO;

public interface ContabilidadeInterface {

	public void createContabilidade (ContabilidadeDTO contabilidadeDTO);
	
	//public void deleteContabilidade (ContabilidadeDTO contabilidadeDTO);
	
	public void updateContabilidade (ContabilidadeDTO contabilidadeDTO);
	
	public ContabilidadeDTO readContabilidade (ContabilidadeDTO contabilidadeDTO);
	
	public ContabilidadeDTO table ();

	public String dateNow(VendaDTO vendaDTO);
	
}
