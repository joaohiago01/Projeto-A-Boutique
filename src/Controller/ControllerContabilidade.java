package Controller;

import DTO.ContabilidadeDTO;
import DTO.VendaDTO;
import Model.Contabilidade;

public class ControllerContabilidade {

	private Contabilidade contabilidade;
	
	public ControllerContabilidade () {
		this.contabilidade = new Contabilidade();
	}
	
	public void criarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		contabilidade.criarContabilidade(contabilidadeDTO);
	}
	
	public void atualizarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		contabilidade.atualizarContabilidade(contabilidadeDTO);
	}
	
	public ContabilidadeDTO recuperarContabilidade (ContabilidadeDTO contabilidadeDTO) {
		return contabilidade.recuperarContabilidade(contabilidadeDTO);
	}

	public ContabilidadeDTO tableContabilidade() {
		return contabilidade.tableContabilidade();
	}

	public String dataAtual(VendaDTO vendaDTO) {
		return contabilidade.dataAtual(vendaDTO);
	}

	public void PDF() {
		contabilidade.PDF();
	}
}
