package Controller;

import DTO.ProdutoDTO;
import Model.Produto;

public class ControllerProduto {

	private Produto produto;
	
	public ControllerProduto () {
		this.produto = new Produto();
	}
	
	public void cadastrarProduto (ProdutoDTO produtoDTO) {
		produto.cadastrarProduto(produtoDTO);
	}
	
	public void atualizarProduto (ProdutoDTO produtoDTO) {
		produto.atualizarProduto(produtoDTO);
	}
	
	public ProdutoDTO recuperarProduto (ProdutoDTO produtoDTO) {
		return produto.recuperarProduto(produtoDTO);
	}
	
	public void deletarProduto (ProdutoDTO produtoDTO) {
		produto.deletarProduto(produtoDTO);;
	}

	public ProdutoDTO tableProduto() {
		return produto.tableProduto();
	}

	public ProdutoDTO filtroProduto(ProdutoDTO produtoDTO) {
		return produto.filtroProduto(produtoDTO);
	}

	public boolean verificarEstoque(ProdutoDTO produtoDTO) {
		return produto.verificarEstoque(produtoDTO);
	}

	public void PDF() {
		produto.PDF();
	}
}
