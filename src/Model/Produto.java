package Model;

import DAO.ProdutoAdapterPostgreSQL;
import DAO.ProdutoInterface;
import DTO.ProdutoDTO;

public class Produto {

	private ProdutoInterface produtoInterface = new ProdutoAdapterPostgreSQL();

	public void cadastrarProduto (ProdutoDTO produtoDTO) {
		produtoInterface.createProduto(produtoDTO);
	}

	public void atualizarProduto (ProdutoDTO produtoDTO) {
		produtoInterface.updateProduto(produtoDTO);
	}

	public ProdutoDTO recuperarProduto (ProdutoDTO produtoDTO) {
		return produtoInterface.readProduto(produtoDTO);
	}

	public void deletarProduto (ProdutoDTO produtoDTO) {
		produtoInterface.deleteProduto(produtoDTO);
	}

	public ProdutoDTO tableProduto() {
		return produtoInterface.table();
	}

	public ProdutoDTO filtroProduto(ProdutoDTO produtoDTO) {
		return produtoInterface.filtro(produtoDTO);
	}

	public boolean verificarEstoque(ProdutoDTO produtoDTO) {
		ProdutoDTO DTOproduto = produtoInterface.readProduto(produtoDTO);
		if (produtoDTO.getQtdPedida() > DTOproduto.getQtdEstoque())
			return false;
		return true;
	}

	public void PDF() {
		
		PDF pdf = new PDF();
		pdf.gerarPDFProduto(produtoInterface.table());
		
	}

}
