package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FiltroProdutos implements Filtro {

	@Override
	public void filtrar(String filtroProduto, DefaultTableModel modelProdutos, JTable tableProdutos) {
		ReceiverFiltroProdutos receiverFiltroProdutos = new ReceiverFiltroProdutos();
		receiverFiltroProdutos.action(filtroProduto, modelProdutos, tableProdutos);
	}
}
