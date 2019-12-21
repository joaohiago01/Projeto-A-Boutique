package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerCliente;
import DTO.ClienteDTO;

public class ReceiverFiltroClientes {

	public void action (String filtroCliente, DefaultTableModel modelClientes, JTable tableClientes) {
		ControllerCliente controllerCliente = new ControllerCliente();
		//String filtroCliente = viewVendedor.textFieldFiltroClientes.getText();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setFiltro(filtroCliente);
		clienteDTO = controllerCliente.filtroCliente(clienteDTO);
		if (clienteDTO != null) {
			modelClientes = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int columIndex) {
					return false;
				}  
			};

			modelClientes.addColumn("ID");
			modelClientes.addColumn("Nome");

			ContextStrategy contextStrategyClientes = new ContextStrategy();
			Iterator iteratorClientes = contextStrategyClientes.kindIterator(clienteDTO.getTable());

			while (iteratorClientes.hasNext()) {
				String cliente[] = iteratorClientes.next().toString().split("%");
				if (cliente != null) {
					Object[] row = new Object[] {
							cliente[0],
							cliente[1]
					};
					modelClientes.addRow(row);
				}
			}
			tableClientes.setModel(modelClientes);
		}
	}
}
