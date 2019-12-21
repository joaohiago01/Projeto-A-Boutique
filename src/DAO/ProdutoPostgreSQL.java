package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ProdutoDTO;

public class ProdutoPostgreSQL {

	private ProdutoDTO produtoDTO = new ProdutoDTO();
	private ConnectionBD connectionBD;

	public ProdutoPostgreSQL () {
		checkConnection();
	}

	public void checkConnection () {
		connectionBD = ConnectionBD.getInstance();
	}

	public void finishConnection () {
		connectionBD.closeConnection();
	}

	public void createProduto (ProdutoDTO produtoDTO) {

		String nome = "'" + produtoDTO.getNome() + "'";
		String descricao = "'" + produtoDTO.getDescricao() + "'";
		String preco = "'" + produtoDTO.getPreco() + "'";
		String quant_estoque = "'" + produtoDTO.getQtdEstoque() + "'";
		String query = "( " + nome + ", " + descricao + ", " + preco + ", " + quant_estoque + " )";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("insert into produto (nome, descricao, preco, quant_estoque) values " + query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteProduto (ProdutoDTO produtoDTO) {

		String id = "'" + produtoDTO.getId_produto() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("delete from produto where id_produto = " + id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateProduto (ProdutoDTO produtoDTO) {

		String id = "'" + produtoDTO.getId_produto() + "'";
		String nome = "'" + produtoDTO.getNome() + "'";
		String descricao = "'" + produtoDTO.getDescricao() + "'";
		String preco = "'" + produtoDTO.getPreco() + "'";
		String quant_estoque = "'" + produtoDTO.getQtdEstoque() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				//ResultSet set = connectionBD.getConnection().executeQuery("select * from produto where id_produto = "  + id);
				//while (set.next()) {
					connectionBD.getConnection().executeUpdate("update produto set nome = " + nome + " where id_produto = " + id);
					connectionBD.getConnection().executeUpdate("update produto set descricao = " + descricao + " where id_produto = " + id);
					connectionBD.getConnection().executeUpdate("update produto set preco = " + preco + " where id_produto = " + id);
					connectionBD.getConnection().executeUpdate("update produto set quant_estoque = " + quant_estoque + " where id_produto = " + id);
				//}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ProdutoDTO readProduto (ProdutoDTO produtoDTO) {

		String id = "'" + produtoDTO.getId_produto() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from produto where id_produto = " + id);
				if (set.next()) {
					this.produtoDTO.setId_produto(set.getInt(1));
					this.produtoDTO.setNome(set.getString(2));
					this.produtoDTO.setDescricao(set.getString(3));
					this.produtoDTO.setPreco(set.getFloat(4));
					this.produtoDTO.setQtdEstoque(set.getInt(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this.produtoDTO;
	}

	public ProdutoDTO table () {

		String tableProduto = "";

		if (connectionBD.isConnectionValid()) {
			ResultSet set;
			try {
				set = connectionBD.getConnection().executeQuery("select * from produto order by nome");
				while (set.next()) {
					tableProduto += set.getInt(1) + "%" + set.getString(2) + "%" + set.getFloat(4) + "%" + set.getInt(5) + "#";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (tableProduto.equals("")) {
			return null;
		} else {
			String table[] = tableProduto.split("#");
			this.produtoDTO.setTable(table);
			return this.produtoDTO;
		}
	}

	public ProdutoDTO filtro (ProdutoDTO produtoDTO) {

		String tableProduto = "";
		String descricao = "";
		if (produtoDTO.getFiltro().equals("")) {
			descricao = "";
		} else {
			descricao = produtoDTO.getFiltro().toLowerCase();
		}
		
		if (connectionBD.isConnectionValid()) {
			ResultSet set;
			try {
				set = connectionBD.getConnection().executeQuery("select * from produto where lower(descricao) like " + "'%"+descricao+"%'");
				while (set.next()) {
					tableProduto += set.getInt(1) + "%" + set.getString(2) + "%" + set.getFloat(4) + "%" + set.getInt(5) + "#";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (tableProduto.equals("")) {
			return null;
		} else {
			String table[] = tableProduto.split("#");
			this.produtoDTO.setTable(table);
			return this.produtoDTO;
		}
	}
}
