package Model;

import DAO.ContabilidadeAdapterPostgreSQL;
import DAO.ContabilidadeInterface;
import DTO.ContabilidadeDTO;
import DTO.VendaDTO;

public class Contabilidade {

	private ContabilidadeInterface contabilidadeInterface = new ContabilidadeAdapterPostgreSQL();
	
	public void criarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		contabilidadeInterface.createContabilidade(contabilidadeDTO);
	}
	
	public void atualizarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		contabilidadeInterface.updateContabilidade(contabilidadeDTO);
	}
	
	public ContabilidadeDTO recuperarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		return contabilidadeInterface.readContabilidade(contabilidadeDTO);
	}

	public ContabilidadeDTO tableContabilidade() {
		return contabilidadeInterface.table();
	}

	public String dataAtual(VendaDTO vendaDTO) {
		return contabilidadeInterface.dateNow(vendaDTO);
	}

	public void PDF() {
		
		PDF pdf = new PDF();
		pdf.gerarPDFContabilidade(contabilidadeInterface.table());
		
	}

}
