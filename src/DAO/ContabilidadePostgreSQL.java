package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ContabilidadeDTO;
import DTO.VendaDTO;

public class ContabilidadePostgreSQL {

	private ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();
	private ConnectionBD connectionBD;

	public ContabilidadePostgreSQL () {
		checkConnection();
	}

	public void checkConnection () {
		connectionBD = ConnectionBD.getInstance();
	}

	public void finishConnection () {
		connectionBD.closeConnection();
	}

	public void createContabilidade (ContabilidadeDTO contabilidadeDTO) {
		
		String mes = "'" + contabilidadeDTO.getMesAtual().toString() + "'";
		String lucro = "'" + contabilidadeDTO.getLucroMensal() + "'";
		String quant = "'" + contabilidadeDTO.getQtdVendidos() + "'";
		String query = "( " + lucro + ", " + quant + ", " + mes + " )";
		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("insert into contabilidade (lucro, quant_vendidos, mes_atual) values " + query);
				/*String id_venda = "'" + contabilidadeDTO.getId_venda() + "'";
				String queryC = "( " + mes + ", " + id_venda + " )";
				connectionBD.getConnection().executeUpdate("insert into vendas_da_contabilidade (data_contabilidade, id_venda) values " + queryC);*/
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void updateContabilidade (ContabilidadeDTO contabilidadeDTO) {

		String mes = "'" + contabilidadeDTO.getMesAtual().toString() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				boolean novaContabilidade = true;
				ResultSet set = connectionBD.getConnection().executeQuery("select * from contabilidade where mes_atual = " + mes);
				if (!set.next()) {
					novaContabilidade = false;
					createContabilidade(contabilidadeDTO);
				} else {
					set = connectionBD.getConnection().executeQuery("select * from contabilidade where mes_atual = " + mes);
					if (set.next() && novaContabilidade) {
						contabilidadeDTO.setLucroMensal(contabilidadeDTO.getLucroMensal() + set.getFloat(2));
						contabilidadeDTO.setQtdVendidos(contabilidadeDTO.getQtdVendidos() + set.getInt(3));
						String lucro = "'" + contabilidadeDTO.getLucroMensal() + "'";
						String quant = "'" + contabilidadeDTO.getQtdVendidos() + "'";
						connectionBD.getConnection().executeUpdate("update contabilidade set lucro = " + lucro + " where mes_atual = " + mes);
						connectionBD.getConnection().executeUpdate("update contabilidade set quant_vendidos = " + quant + " where mes_atual = " + mes);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ContabilidadeDTO readContabilidade (ContabilidadeDTO contabilidadeDTO) {

		String mes = "'" + contabilidadeDTO.getMesAtual() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from contabilidade where mes_atual = " + mes);
				if (set.next()) {
					this.contabilidadeDTO.setMesAtual(set.getDate(1).toString());
					this.contabilidadeDTO.setLucroMensal(set.getFloat(2));
					this.contabilidadeDTO.setQtdVendidos(set.getInt(3));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return this.contabilidadeDTO;
	}
	
	public ContabilidadeDTO table () {

		ArrayList<ContabilidadeDTO> table = new ArrayList<ContabilidadeDTO>();

		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from contabilidade order by mes_atual");
				while (set.next()) {
					ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();
					contabilidadeDTO.setMesAtual(set.getDate(1).toString());
					contabilidadeDTO.setLucroMensal(set.getFloat(2));
					contabilidadeDTO.setQtdVendidos(set.getInt(3));
					table.add(contabilidadeDTO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (table.isEmpty()) {
			return null;
		} else {
			this.contabilidadeDTO.setTable(table);
			return this.contabilidadeDTO;
		}
	}
	
	public String dateNow(VendaDTO vendaDTO) {
		
		String dtAtual = "";
		String id = "'" + vendaDTO.getIdVenda() + "'";
		
		if (connectionBD.isConnectionValid()) {
			try {
				ResultSet set = connectionBD.getConnection().executeQuery("select * from venda where id_venda = " + id);
				if (set.next()) {
					dtAtual = set.getDate(5).toString();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dtAtual;
	}

}
