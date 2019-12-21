package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InvokerFiltro {

	public void setFiltro (Filtro filtro,String text, DefaultTableModel model, JTable table) {
		filtro.filtrar(text, model, table);
	}
}
