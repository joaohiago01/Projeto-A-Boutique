package DAO;

import DTO.ProdutoDTO;

public interface ProdutoInterface {

	public void createProduto (ProdutoDTO produtoDTO);
	
	public void deleteProduto (ProdutoDTO produtoDTO);
	
	public void updateProduto (ProdutoDTO produtoDTO);
	
	public ProdutoDTO readProduto (ProdutoDTO produtoDTO);

	public ProdutoDTO table();

	public ProdutoDTO filtro(ProdutoDTO produtoDTO);
	
}
