package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FiltroFuncionarios implements Filtro {

	@Override
	public void filtrar(String filtroFuncionario, DefaultTableModel model, JTable table) {
		ReceiverFiltroFuncionario receptorFiltroFuncionario = new ReceiverFiltroFuncionario();
		receptorFiltroFuncionario.action(filtroFuncionario, model, table);
	}
}
