package View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerGerente;
import DTO.FuncionarioDTO;

public class ReceiverFiltroFuncionario {

	public void action (String filtroFuncionario, DefaultTableModel model, JTable table) {
		ControllerGerente controllerGerente = new ControllerGerente();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setFiltro(filtroFuncionario);
		funcionarioDTO = controllerGerente.filtroFuncionario(funcionarioDTO);

		if (funcionarioDTO != null) {

			model = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int columIndex) {
					return false;
				}  
			};

			model.addColumn("ID");
			model.addColumn("Nome");
			model.addColumn("E-mail");
			model.addColumn("Telefone");
			model.addColumn("Cargo");

			ContextStrategy contextStrategyFuncionarios = new ContextStrategy();
			Iterator iterator = contextStrategyFuncionarios.kindIterator(funcionarioDTO.getTable());

			while (iterator.hasNext()) {
				FuncionarioDTO DTOfuncionario = (FuncionarioDTO) iterator.next();
				if (DTOfuncionario != null) {
					Object[] row = new Object[] {
							DTOfuncionario.getIdFuncionario(),
							DTOfuncionario.getNome(),
							DTOfuncionario.getEmail(),
							DTOfuncionario.getTelefone(),
							DTOfuncionario.getCargo()
					};

					model.addRow(row);
				}
			}
			table.setModel(model);
		}		
	}
}
