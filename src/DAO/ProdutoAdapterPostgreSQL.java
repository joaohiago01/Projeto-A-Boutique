package DAO;

import DTO.ProdutoDTO;

public class ProdutoAdapterPostgreSQL extends ProdutoPostgreSQL implements ProdutoInterface {

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
	public void createProduto(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		super.createProduto(produtoDTO);
	}

	@Override
	public void deleteProduto(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		super.deleteProduto(produtoDTO);
	}

	@Override
	public void updateProduto(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		super.updateProduto(produtoDTO);
	}

	@Override
	public ProdutoDTO readProduto(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		return super.readProduto(produtoDTO);
	}

	@Override
	public ProdutoDTO table() {
		// TODO Auto-generated method stub
		return super.table();
	}

	@Override
	public ProdutoDTO filtro(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		return super.filtro(produtoDTO);
	}

	
}
