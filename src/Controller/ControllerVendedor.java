package Controller;

import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.VendaDTO;
import Model.Vendedor;

public class ControllerVendedor {

	private Vendedor vendedor;

	public ControllerVendedor () {
		this.vendedor = new Vendedor();
	}

	public VendaDTO realizarVenda (VendaDTO vendaDTO) {
		return vendedor.realizarVenda(vendaDTO);
	}

	public void cancelarVenda (VendaDTO vendaDTO) {
		vendedor.cancelarVenda(vendaDTO);
	}

	/*public void atualizarVenda (VendaDTO vendaDTO) {
		vendedor.atualizarVenda(vendaDTO);
	}*/

	public VendaDTO recuperarVenda (VendaDTO vendaDTO) {
		return vendedor.recuperarVenda(vendaDTO);
	}

	public void PDF(String vendedorNome, String opcs) {
		vendedor.PDF(vendedorNome, opcs);
	}

	public void notaFiscal(FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, VendaDTO vendaDTO) {
		vendedor.notaFiscal(funcionarioDTO, clienteDTO, vendaDTO);
	}
}
