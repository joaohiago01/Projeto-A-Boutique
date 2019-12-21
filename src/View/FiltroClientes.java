package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FiltroClientes implements Filtro {

	@Override
	public void filtrar(String filtroCliente, DefaultTableModel modelClientes, JTable tableClientes) {
		ReceiverFiltroClientes receiverFiltroClientes = new ReceiverFiltroClientes();
		receiverFiltroClientes.action(filtroCliente, modelClientes, tableClientes);
	}
}
