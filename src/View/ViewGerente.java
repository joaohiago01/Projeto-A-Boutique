package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerContabilidade;
import Controller.ControllerGerente;
import Controller.ControllerProduto;
import DTO.ContabilidadeDTO;
import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;

public class ViewGerente extends ViewPadrao {

	private JTextField textFieldFiltro;
	private JButton btnFuncionrios, btnProdutos, btnContabilidade, btnPromoes, btnSair, buttonFiltro, btnSelecioneUmaOpo, btnRelatrioDeProdutos, btnRelatrioDaContabilidade;
	protected String vendedor;
	private boolean POF;
	private JComboBox comboBoxOpcoes;
	private DefaultTableModel model;
	private JTable table;
	private ControllerGerente controllerGerente = new ControllerGerente();
	private ControllerProduto controllerProduto = new ControllerProduto();
	private ControllerContabilidade controllerContabilidade = new ControllerContabilidade();

	public ViewGerente(String vendedor) {

		super();
		this.setSize(1360, 520);
		this.setLocationRelativeTo(null);
		this.vendedor = vendedor;

		JLabel lblVendedor = new JLabel("GERENTE: " + vendedor);
		lblVendedor.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblVendedor.setBounds(5, 15, 500, 20);
		getContentPane().add(lblVendedor);

		btnFuncionrios = new JButton("FUNCION\u00C1RIOS");
		btnFuncionrios.setIcon(new ImageIcon("images/icons8-grupos-de-usuários-26.png"));
		btnFuncionrios.setHorizontalAlignment(SwingConstants.LEFT);
		btnFuncionrios.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnFuncionrios.setBackground(Color.WHITE);
		btnFuncionrios.setToolTipText("CLIQUE PARA VER OS FUNCIONÁRIOS");
		btnFuncionrios.setBounds(5, 100, 250, 35);
		getContentPane().add(btnFuncionrios);

		btnProdutos = new JButton("PRODUTOS");
		btnProdutos.setIcon(new ImageIcon("images/icons8-joalheria-32.png"));
		btnProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		btnProdutos.setBackground(Color.WHITE);
		btnProdutos.setToolTipText("CLIQUE PARA VER OS PRODUTOS");
		btnProdutos.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnProdutos.setBounds(5, 160, 250, 35);
		getContentPane().add(btnProdutos);

		btnContabilidade = new JButton("CONTABILIDADE");
		btnContabilidade.setIcon(new ImageIcon("images/icons8-contabilidade-24.png"));
		btnContabilidade.setHorizontalAlignment(SwingConstants.LEFT);
		btnContabilidade.setBackground(Color.WHITE);
		btnContabilidade.setToolTipText("CLIQUE PARA VER A CONTABILIDADE");
		btnContabilidade.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnContabilidade.setBounds(5, 220, 250, 35);
		getContentPane().add(btnContabilidade);

		btnPromoes = new JButton("VENDEDOR");
		btnPromoes.setBackground(Color.WHITE);
		btnPromoes.setIcon(new ImageIcon("images/icons8-usuário-mulher-com-círculo-26.png"));
		btnPromoes.setHorizontalAlignment(SwingConstants.LEFT);
		btnPromoes.setToolTipText("CLIQUE PARA ACESSAR A TELA DO VENDEDOR");
		btnPromoes.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnPromoes.setBounds(5, 280, 250, 35);
		getContentPane().add(btnPromoes);

		btnSair = new JButton("SAIR");
		btnSair.setIcon(new ImageIcon("images/icons8-voltar-30.png"));
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);
		btnSair.setBackground(Color.WHITE);
		btnSair.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSair.setBounds(5, 450, 250, 35);
		getContentPane().add(btnSair);

		table = new JTable(model);
		table.setBounds(0, 0, 1, 1);
		getContentPane().add(table);

		JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.setBounds(305, 100, 1000, 390);
		getContentPane().add(scrollPaneTable);

		textFieldFiltro = new JTextField();
		textFieldFiltro.setBounds(305, 65, 300, 30);
		getContentPane().add(textFieldFiltro);
		textFieldFiltro.setColumns(10);

		buttonFiltro = new JButton("FILTRAR");
		buttonFiltro.setIcon(new ImageIcon("images/icons8-pesquisar-24.png"));
		buttonFiltro.setFont(new Font("SansSerif", Font.BOLD, 15));
		buttonFiltro.setBackground(Color.WHITE);
		buttonFiltro.setFont(new Font("SansSerif", Font.BOLD, 15));
		buttonFiltro.setBounds(610, 65, 130, 35);
		getContentPane().add(buttonFiltro);

		comboBoxOpcoes = new JComboBox();
		comboBoxOpcoes.setBackground(Color.WHITE);
		comboBoxOpcoes.setFont(new Font("SansSerif", Font.BOLD, 15));
		comboBoxOpcoes.setBounds(1000, 65, 300, 35);
		getContentPane().add(comboBoxOpcoes);

		btnSelecioneUmaOpo = new JButton("SELECIONE UMA OP\u00C7\u00C3O:");
		btnSelecioneUmaOpo.setBackground(Color.WHITE);
		btnSelecioneUmaOpo.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSelecioneUmaOpo.setBounds(750, 65, 250, 35);
		getContentPane().add(btnSelecioneUmaOpo);

		btnRelatrioDeProdutos = new JButton("RELAT\u00D3RIO DE PRODUTOS");
		btnRelatrioDeProdutos.setIcon(new ImageIcon("images/icons8-baixar-relatório-gráfico-24.png"));
		btnRelatrioDeProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatrioDeProdutos.setBackground(Color.WHITE);
		btnRelatrioDeProdutos.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnRelatrioDeProdutos.setBounds(5, 340, 250, 35);
		getContentPane().add(btnRelatrioDeProdutos);

		btnRelatrioDaContabilidade = new JButton("RELAT\u00D3RIO DA CONTABILIDADE");
		btnRelatrioDaContabilidade.setIcon(new ImageIcon("images/icons8-baixar-relatório-gráfico-24.png"));
		btnRelatrioDaContabilidade.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatrioDaContabilidade.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnRelatrioDaContabilidade.setBackground(Color.WHITE);
		btnRelatrioDaContabilidade.setBounds(5, 395, 250, 35);
		getContentPane().add(btnRelatrioDaContabilidade);

		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteGerente ouvinteGerente = new OuvinteGerente(this);
		this.buttonFiltro.addActionListener(ouvinteGerente);
		this.btnContabilidade.addActionListener(ouvinteGerente);
		this.btnFuncionrios.addActionListener(ouvinteGerente);
		this.btnProdutos.addActionListener(ouvinteGerente);
		this.btnPromoes.addActionListener(ouvinteGerente);
		this.btnSair.addActionListener(ouvinteGerente);
		this.btnSelecioneUmaOpo.addActionListener(ouvinteGerente);
		this.btnRelatrioDeProdutos.addActionListener(ouvinteGerente);
		this.btnRelatrioDaContabilidade.addActionListener(ouvinteGerente);
	}

	public void filtrarProdutos (Filtro filtro, String textFiltro, DefaultTableModel model, JTable table) {
		InvokerFiltro invokerFiltro = new InvokerFiltro();
		invokerFiltro.setFiltro(filtro, textFiltro, model, table);
	}

	public void filtrarFuncionarios (Filtro filtro, String textFiltro, DefaultTableModel model, JTable table) {
		InvokerFiltro invokerFiltro = new InvokerFiltro();
		invokerFiltro.setFiltro(filtro, textFiltro, model, table);
	}

	public String opcaoSelecionada() {
		if (comboBoxOpcoes.getItemCount() > 0)
			return comboBoxOpcoes.getSelectedItem().toString();
		return null;
	}

	public Object rowSelect() {
		Object funcionarioOuProduto = null;
		try {
			if (POF()) {
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
				funcionarioDTO.setIdFuncionario(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
				funcionarioOuProduto = controllerGerente.recuperarFuncionario(funcionarioDTO);
			} else {
				ProdutoDTO produtoDTO = new ProdutoDTO();
				produtoDTO.setId_produto(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
				funcionarioOuProduto = controllerProduto.recuperarProduto(produtoDTO);
			}
		} catch (FuncionarioNaoExisteException e) {}
		return funcionarioOuProduto;

	}

	public boolean funcionarioOuProduto() {
		if (rowSelect() instanceof ProdutoDTO) {
			return true;
		} else if (rowSelect() instanceof FuncionarioDTO) {
			return false;
		}

		return true;
	}

	public boolean POF () {
		if (model.getColumnCount() == 5) {
			POF = true;
			return true;
		}
		POF = false;
		return false;
	}

	public class OuvinteGerente implements ActionListener {

		private ViewGerente viewGerente;

		public OuvinteGerente (ViewGerente viewGerente) {
			this.viewGerente = viewGerente;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {
			case "FILTRAR":

				if (POF) {

					if (!textFieldFiltro.getText().equals("")) {

						viewGerente.filtrarFuncionarios(new FiltroFuncionarios(), textFieldFiltro.getText(), model, table);
						viewGerente.repaint();

					}

				} else {
					if (!textFieldFiltro.getText().equals("")) {

						viewGerente.filtrarProdutos(new FiltroProdutos(), textFieldFiltro.getText(), model, table);
						viewGerente.repaint();

					}
				}
				break;

			case "FUNCIONÁRIOS":

				FuncionarioDTO funcionarioDTO = controllerGerente.tableFuncionarios();
				textFieldFiltro.setText("");

				if (comboBoxOpcoes.getItemCount() == 0) 
					comboBoxOpcoes.addItem("CADASTRAR");

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

				if (funcionarioDTO != null) {

					if (comboBoxOpcoes.getItemCount() == 1) {
						comboBoxOpcoes.addItem("REMOVER");
						comboBoxOpcoes.addItem("EDITAR");
					}

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

				} else {
					JOptionPane.showMessageDialog(viewGerente, "NÃO EXISTEM FUNCIONÁRIOS CADASTRADOS");
				}

				viewGerente.table.setModel(model);
				viewGerente.repaint();
				POF();
				break;

			case "PRODUTOS":

				ProdutoDTO produtoDTO = controllerProduto.tableProduto();
				textFieldFiltro.setText("");

				if (comboBoxOpcoes.getItemCount() == 0) 
					comboBoxOpcoes.addItem("CADASTRAR");

				model = new DefaultTableModel() {
					public boolean isCellEditable(int rowIndex, int columIndex) {
						return false;
					}  
				};

				model.addColumn("ID");
				model.addColumn("Nome");
				model.addColumn("Preço");
				model.addColumn("Quantidade No Estoque");

				if (produtoDTO != null) {

					if (comboBoxOpcoes.getItemCount() == 1) {
						comboBoxOpcoes.addItem("REMOVER");
						comboBoxOpcoes.addItem("EDITAR");
					}

					ContextStrategy contextStrategyProdutos = new ContextStrategy();
					Iterator iteratorProduto = contextStrategyProdutos.kindIterator(produtoDTO.getTable());

					while (iteratorProduto.hasNext()) {
						String produto[] = iteratorProduto.next().toString().split("%");
						if (produto != null) {
							Object[] row = new Object[] {
									produto[0],
									produto[1],
									produto[2],
									produto[3]
							};
							model.addRow(row);
						}
					}

				} else {
					JOptionPane.showMessageDialog(viewGerente, "NÃO EXISTEM PRODUTOS CADASTRADOS");
				}

				viewGerente.table.setModel(model);
				viewGerente.repaint();
				POF();
				break;

			case "CONTABILIDADE":

				comboBoxOpcoes.removeAllItems();
				
				ContabilidadeDTO contabilidadeDTO = controllerContabilidade.tableContabilidade();
				textFieldFiltro.setText("");

				model = new DefaultTableModel() {
					public boolean isCellEditable(int rowIndex, int columIndex) {
						return false;
					}  
				};

				model.addColumn("Mês/Ano");
				model.addColumn("Lucro Mensal");
				model.addColumn("Quant. Produtos Vendidos");

				if (contabilidadeDTO != null) {
					ContextStrategy contextStrategyContabilidade = new ContextStrategy();
					Iterator iteratorContabilidade = contextStrategyContabilidade.kindIterator(contabilidadeDTO.getTable());

					while (iteratorContabilidade.hasNext()) {
						ContabilidadeDTO DTOcontabilidade = (ContabilidadeDTO) iteratorContabilidade.next();
						if (contabilidadeDTO != null) {
							String[] data = DTOcontabilidade.getMesAtual().split("-");
							String dt = data[1] +"/"+ data[0];
							Object[] row = new Object[] {
									dt,
									DTOcontabilidade.getLucroMensal(),
									DTOcontabilidade.getQtdVendidos()
							};

							model.addRow(row);
						}
					}

				} else {
					JOptionPane.showMessageDialog(viewGerente, "AINDA NÃO EXISTE CONTABILIDADE FEITA NO SISTEMA");
				}

				viewGerente.table.setModel(model);
				viewGerente.repaint();
				POF();
				break;

			case "VENDEDOR":

				int c = JOptionPane.showConfirmDialog(viewGerente, "TEM CERTEZA QUE DESEJA ACESSAR A TELA DO VENDEDOR?");
				if (c == JOptionPane.YES_OPTION) {
					viewGerente.dispose();
					new ViewVendedor(vendedor, null);
				}

				break;

			case "SELECIONE UMA OPÇÃO:":

				String opc = opcaoSelecionada();

				if (opc != null) {
					switch (opc) {
					case "CADASTRAR":

						if (POF())
							new ViewCadastroFuncionario(null, viewGerente);
						else
							new ViewCadastroProduto(null, viewGerente);

						break;

					case "REMOVER":

						if (table.getSelectedRow() != -1) {
							int remover = JOptionPane.showConfirmDialog(viewGerente, "TEM CERTEZA QUE DESEJA REMOVER?");
							if (remover == JOptionPane.YES_OPTION) {
								if (POF()) {
									controllerGerente.demitirVendedor((FuncionarioDTO) rowSelect());
								} else {
									controllerProduto.deletarProduto((ProdutoDTO) rowSelect());
								}
								viewGerente.dispose();
								new ViewGerente(vendedor);
							}
						} else {
							JOptionPane.showMessageDialog(viewGerente, "SELECIONE UMA LINHA DA TABELA");
						}
						break;

					case "EDITAR":

						if (table.getSelectedRow() != -1) {
							if (POF())
								new ViewCadastroFuncionario((FuncionarioDTO)rowSelect(), viewGerente);
							else
								new ViewCadastroProduto((ProdutoDTO)rowSelect(), viewGerente);
							break;
						} else {
							JOptionPane.showMessageDialog(viewGerente, "SELECIONE UMA LINHA DA TABELA");
						}
					}
				}
				break;

			case "SAIR":
				viewGerente.dispose();
				new ViewInicial();
				break;

			case "RELATÓRIO DE PRODUTOS":

				if (controllerProduto.tableProduto() != null) {
					int confirm = JOptionPane.showConfirmDialog(viewGerente, "DESEJA IMPRIMIR UM RELATÓRIO DOS PRODUTOS?");
					if (confirm == JOptionPane.YES_OPTION) {
						controllerProduto.PDF();
					}
				} else {
					JOptionPane.showMessageDialog(viewGerente, "NÃO EXISTEM PRODUTOS CADASTRADOS");
				}
				break;

			case "RELATÓRIO DA CONTABILIDADE":

				if (controllerContabilidade.tableContabilidade() != null) {
					int confirm = JOptionPane.showConfirmDialog(viewGerente, "DESEJA IMPRIMIR UM RELATÓRIO DAS CONTABILIDADES?");
					if (confirm == JOptionPane.YES_OPTION) {
						controllerContabilidade.PDF();
					}
				} else {
					JOptionPane.showMessageDialog(viewGerente, "AINDA NÃO EXISTE CONTABILIDADE FEITA NO SISTEMA");
				}
				break;
			}
		}
	}

}
