package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface Filtro {

	public void filtrar (String filtro, DefaultTableModel model, JTable table);
	
}
