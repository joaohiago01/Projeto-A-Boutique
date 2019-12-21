package Model;

import DAO.FuncionarioAdapterPostgreSQL;
import DAO.FuncionarioInterface;
import DAO.VendaAdapterPostgreSQL;
import DAO.VendaInterface;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.VendaDTO;
import View.FuncionarioNaoExisteException;

public class Vendedor {

	private VendaInterface vendaInterface = new VendaAdapterPostgreSQL();
	private FuncionarioInterface funcionarioInterface = new FuncionarioAdapterPostgreSQL();
	
	public VendaDTO realizarVenda (VendaDTO vendaDTO) {
		return vendaInterface.createVenda(vendaDTO);
	}

	public void cancelarVenda (VendaDTO vendaDTO) {
		vendaInterface.deleteVenda(vendaDTO);
	}

	/*public void atualizarVenda (VendaDTO vendaDTO) {
		vendaInterface.updateVenda(vendaDTO);
	}*/

	public VendaDTO recuperarVenda (VendaDTO vendaDTO) {
		return vendaInterface.readVenda(vendaDTO);
	}

	public void notaFiscal (FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, VendaDTO vendaDTO) {
		PDF pdf = new PDF();
		pdf.gerarPDFNotaFiscal(funcionarioDTO, clienteDTO, vendaDTO);
	}
	
	public void PDF(String vendedorNome, String opcs) {
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setNome(vendedorNome);
		try {
			funcionarioDTO = funcionarioInterface.readFuncionario(funcionarioDTO);
			VendaDTO vendaDTO = new VendaDTO();
			vendaDTO = vendaInterface.PDF(funcionarioDTO, opcs);
			PDF pdf = new PDF();
			pdf.gerarPDFVendedor(funcionarioDTO, vendaDTO);
			
		} catch (FuncionarioNaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
