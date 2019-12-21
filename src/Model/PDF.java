package Model;

import DTO.ClienteDTO;
import DTO.ContabilidadeDTO;
import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class PDF {

	public void gerarPDFProduto (ProdutoDTO produtoDTO) {
		PDFProduto pdfProduto = new PDFProduto();
		pdfProduto.pdfProduto(produtoDTO);
	}
	
	public void gerarPDFContabilidade (ContabilidadeDTO contabilidadeDTO) {
		PDFContabilidade pdfContabilidade = new PDFContabilidade();
		pdfContabilidade.pdfContabilidade(contabilidadeDTO);
	}
	
	public void gerarPDFVendedor (FuncionarioDTO funcionarioDTO, VendaDTO vendaDTO) {
		PDFVendedor pdfVendedor = new PDFVendedor();
		pdfVendedor.pdfVendedor(funcionarioDTO, vendaDTO);
	}
	
	public void gerarPDFNotaFiscal (FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, VendaDTO vendaDTO) {
		PDFNotaFiscal pdfNotaFiscal = new PDFNotaFiscal();
		pdfNotaFiscal.pdfNotaFiscal(funcionarioDTO, clienteDTO, vendaDTO);
	}
}
