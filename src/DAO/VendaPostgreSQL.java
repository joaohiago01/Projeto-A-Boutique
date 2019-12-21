package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class VendaPostgreSQL {

	private VendaDTO vendaDTO = new VendaDTO();
	private ConnectionBD connectionBD;

	public VendaPostgreSQL () {
		checkConnection();
	}

	public void checkConnection () {
		connectionBD = ConnectionBD.getInstance();
	}

	public void finishConnection () {
		connectionBD.closeConnection();
	}

	public VendaDTO createVenda (VendaDTO vendaDTO) {

		String id_cliente = "'" + vendaDTO.getIdCliente() + "'";
		String id_vendedor = "'" + vendaDTO.getIdVendedor() + "'";
		String preco = "'" + vendaDTO.getPrecoTotal() + "'";
		String query = "( " + id_cliente + ", " + id_vendedor + ", " + preco + " )";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("insert into venda (id_cliente, id_vendedor, preco_total) values " + query);
				ResultSet set = connectionBD.getConnection().executeQuery("select * from venda order by id_venda");
				int id_venda = 0;
				while (set.next()) {
					id_venda = set.getRow();
				}
				vendaDTO.setIdVenda(id_venda);
				for (ProdutoDTO produtoDTO: vendaDTO.getProdutos()) {
					String queryP = "( " + "'"+produtoDTO.getId_produto()+"'" + ", " + "'"+id_venda+"'" + ", " + "'"+produtoDTO.getQtdPedida()+"'" +  " )";
					connectionBD.getConnection().executeUpdate("insert into produtos_da_venda (id_produto, id_venda, quant_pedida) values " + queryP);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vendaDTO;

	}

	public void deleteVenda (VendaDTO vendaDTO) {

		String id = "'" + vendaDTO.getIdVenda() + "'";

		if (connectionBD.isConnectionValid()) {
			try {
				connectionBD.getConnection().executeUpdate("delete from venda where id_venda = " + id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public VendaDTO readVenda (VendaDTO vendaDTO) {

		String id = "'" + vendaDTO.getIdVenda() + "'";

		if (connectionBD.isConnectionValid()) {
			ResultSet set;
			try {
				set = connectionBD.getConnection().executeQuery("select * from venda where id_venda = " + id);
				if (set.next()) {
					this.vendaDTO.setIdVenda(set.getInt(1));
					this.vendaDTO.setIdCliente(set.getInt(2));
					this.vendaDTO.setIdVendedor(set.getInt(3));
					this.vendaDTO.setPrecoTotal(set.getFloat(4));
					this.vendaDTO.setData_venda(set.getDate(5).toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return this.vendaDTO;
	}
	
	public VendaDTO PDF (FuncionarioDTO funcionarioDTO, String dataOpc) {

		String PDF = "";
		String vendedor = "";
		if (connectionBD.isConnectionValid()) {
			try {
				
				ResultSet set = null;
				switch (dataOpc) {
				case "RELATÓRIO DO DIA":
					set = connectionBD.getConnection().executeQuery("select C.nome, V.preco_total, V.data_venda, V.id_venda, F.nome from cliente as C inner join venda as V on (V.id_cliente = C.id_cliente) inner join (select * from funcionario where id_funcionario = " + funcionarioDTO.getIdFuncionario() + ") as F on (F.id_funcionario = V.id_vendedor)"
									+ " where V.data_venda = current_date order by V.id_venda");
					break;

				case "RELATÓRIO DA ÚLTIMA SEMANA":
					set = connectionBD.getConnection().executeQuery("select C.nome, V.preco_total, V.data_venda, V.id_venda, F.nome from cliente as C inner join venda as V on (V.id_cliente = C.id_cliente) inner join (select * from funcionario where id_funcionario = " + funcionarioDTO.getIdFuncionario() + ") as F on (F.id_funcionario = V.id_vendedor)"
									+ " where V.data_venda between (current_date - INTERVAL '7 DAY') and current_date order by V.id_venda");
					break;

				case "RELATÓRIO DO MÊS":
					set = connectionBD.getConnection().executeQuery("select C.nome, V.preco_total, V.data_venda, V.id_venda, F.nome from cliente as C inner join venda as V on (V.id_cliente = C.id_cliente) inner join (select * from funcionario where id_funcionario = " + funcionarioDTO.getIdFuncionario() + ") as F on (F.id_funcionario = V.id_vendedor)"
									+ " where V.data_venda >= (current_date - INTERVAL '1 MONTH') order by V.id_venda");
					break;
					
				case "RELATÓRIO COMPLETO":
					set = connectionBD.getConnection().executeQuery("select C.nome, V.preco_total, V.data_venda, V.id_venda, F.nome from cliente as C inner join venda as V on (V.id_cliente = C.id_cliente) inner join (select * from funcionario where id_funcionario = " + funcionarioDTO.getIdFuncionario() + ") as F on (F.id_funcionario = V.id_vendedor)");
					break;
				}
				
				while (set.next()) {
					String data[] = set.getDate(3).toString().split("-");
					PDF += "\n\n\nVENDA Nº " + set.getInt(4) + "\n\n\nCLIENTE: " + set.getString(1)
							+ "\n\nVALOR TOTAL DA VENDA: R$ " + set.getFloat(2) + "\n\nDATA DA VENDA: " + data[2] +"/"+ data[1] +"/"+ data[0] + "#";
					vendedor = set.getString(5);
				}
				//+ "\n\nVENDEDOR: " + set.getString(5)
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String[] PDFVendas = PDF.split("#");
		vendaDTO.setTable(PDFVendas);
		vendaDTO.setVendedor(vendedor);
		return vendaDTO;
	}
}
