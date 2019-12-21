package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerProduto;
import DTO.ProdutoDTO;

public class ReceiverFiltroProdutos {

	public void action (String filtroProduto, DefaultTableModel modelProdutos, JTable tableProdutos) {
		ControllerProduto controllerProduto = new ControllerProduto();
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setFiltro(filtroProduto);
		produtoDTO = controllerProduto.filtroProduto(produtoDTO);
		if (produtoDTO != null) {
			modelProdutos = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int columIndex) {
					return false;
				}  
			};

			modelProdutos.addColumn("ID");
			modelProdutos.addColumn("Nome");
			modelProdutos.addColumn("Preço");
			modelProdutos.addColumn("Quantidade No Estoque");

			ContextStrategy contextStrategyProdutos = new ContextStrategy();
			Iterator iteratorProdutos = contextStrategyProdutos.kindIterator(produtoDTO.getTable());

			while (iteratorProdutos.hasNext()) {
				String produto[] = iteratorProdutos.next().toString().split("%");
				if (produto != null) {
					Object[] row = new Object[] {
							produto[0],
							produto[1],
							produto[2],
							produto[3]
					};
					modelProdutos.addRow(row);
				}
			}
			tableProdutos.setModel(modelProdutos);
		}	
	}
}
