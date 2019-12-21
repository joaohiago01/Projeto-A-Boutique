package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerCliente;
import Controller.ControllerGerente;
import Controller.ControllerProduto;
import Controller.ControllerVendedor;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class ViewVendedor extends ViewPadrao {

	protected JTable tableProdutos, tableClientes;
	protected DefaultTableModel modelProdutos, modelClientes;
	protected JTextField textFieldFiltroProdutos, textFieldFiltroClientes;
	protected JButton btnAdicionarAoCarrinho, btnVerCarrinho, buttonFiltro, btnCancelarCompra, btnCadastrarCliente, btnExcluirCliente, btnEditarCliente, buttonFiltroClientes, btnSair, btnRelatorioDeVendas;
	protected String vendedor;
	private ArrayList<ProdutoDTO> produtoDTOs = new ArrayList<ProdutoDTO>();
	private ClienteDTO clienteDTO;
	private VendaDTO vendaDTO;
	private ProdutoDTO produtoDTO;
	private ControllerCliente controllerCliente = new ControllerCliente();
	private ControllerVendedor controllerVendedor = new ControllerVendedor();
	private ControllerProduto controllerProduto = new ControllerProduto();
	private ControllerGerente controllerGerente = new ControllerGerente();

	public ViewVendedor(String vendedor, VendaDTO vendaDTO) {

		super();
		this.setSize(1360, 720);
		this.setLocationRelativeTo(null);
		this.vendedor = vendedor;
		this.vendaDTO = vendaDTO;
		JLabel lblVendedor = new JLabel("VENDEDOR(A): " + vendedor);
		lblVendedor.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblVendedor.setBounds(5, 15, 500, 20);
		getContentPane().add(lblVendedor);

		ProdutoDTO DTOproduto = controllerProduto.tableProduto();
		if (DTOproduto != null) {
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
			Iterator iteratorProdutos = contextStrategyProdutos.kindIterator(DTOproduto.getTable());

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
		}
		tableProdutos = new JTable(modelProdutos);
		tableProdutos.setToolTipText("CLIQUE DUAS VEZES PARA MAIS INFORMAÇÕES");
		tableProdutos.setBounds(0, 0, 1, 1);
		getContentPane().add(tableProdutos);

		JScrollPane scrollPaneProdutos = new JScrollPane(tableProdutos);
		scrollPaneProdutos.setBounds(6, 188, 600, 515);
		getContentPane().add(scrollPaneProdutos);

		JLabel lblProdutos = new JLabel("PRODUTOS");
		lblProdutos.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblProdutos.setBounds(255, 105, 129, 25);
		getContentPane().add(lblProdutos);

		textFieldFiltroProdutos = new JTextField();
		textFieldFiltroProdutos.setToolTipText("DIGITE O TIPO DE PRODUTO QUE ESTÁ BUSCANDO");
		textFieldFiltroProdutos.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldFiltroProdutos.setBounds(5, 155, 225, 35);
		getContentPane().add(textFieldFiltroProdutos);
		textFieldFiltroProdutos.setColumns(10);

		ClienteDTO DTOcliente = controllerCliente.tableCliente();
		if (DTOcliente != null) {
			modelClientes = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int columIndex) {
					return false;
				}  
			};

			modelClientes.addColumn("ID");
			modelClientes.addColumn("Nome");

			ContextStrategy contextStrategyClientes = new ContextStrategy();
			Iterator iteratorClientes = contextStrategyClientes.kindIterator(DTOcliente.getTable());

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
		}
		tableClientes = new JTable(modelClientes);
		tableClientes.setBounds(0, 0, 1, 1);
		tableClientes.setToolTipText("CLIQUE DUAS VEZES PARA MAIS INFORMAÇÕES");
		getContentPane().add(tableClientes);

		JScrollPane scrollPaneClientes = new JScrollPane(tableClientes);
		scrollPaneClientes.setBounds(620, 188, 450, 515);
		getContentPane().add(scrollPaneClientes);

		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblClientes.setBounds(779, 105, 129, 25);
		getContentPane().add(lblClientes);

		JLabel lblVendas = new JLabel("VENDAS");
		lblVendas.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblVendas.setBounds(1175, 105, 129, 25);
		getContentPane().add(lblVendas);

		btnAdicionarAoCarrinho = new JButton("ADICIONAR A CESTA");
		btnAdicionarAoCarrinho.setIcon(new ImageIcon("images/icons8-comprar-32.png"));
		btnAdicionarAoCarrinho.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdicionarAoCarrinho.setBackground(Color.WHITE);
		btnAdicionarAoCarrinho.setToolTipText("SELECIONE UM PRODUTO");
		btnAdicionarAoCarrinho.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdicionarAoCarrinho.setBounds(1090, 185, 250, 35);
		getContentPane().add(btnAdicionarAoCarrinho);

		btnVerCarrinho = new JButton("CESTA DE PRODUTOS");
		btnVerCarrinho.setIcon(new ImageIcon("images/icons8-sacola-de-compras-32.png"));
		btnVerCarrinho.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerCarrinho.setBackground(Color.WHITE);
		btnVerCarrinho.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVerCarrinho.setBounds(1090, 230, 250, 35);
		getContentPane().add(btnVerCarrinho);

		btnCancelarCompra = new JButton("CANCELAR VENDA");
		btnCancelarCompra.setIcon(new ImageIcon("images/icons8-devolver-compra-32.png"));
		btnCancelarCompra.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelarCompra.setBackground(Color.WHITE);
		btnCancelarCompra.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCancelarCompra.setBounds(1090, 275, 250, 35);
		getContentPane().add(btnCancelarCompra);

		btnCadastrarCliente = new JButton("CADASTRAR CLIENTE");
		btnCadastrarCliente.setIcon(new ImageIcon("images/icons8-adicionar-usuário-masculino-32.png"));
		btnCadastrarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrarCliente.setBackground(Color.WHITE);
		btnCadastrarCliente.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCadastrarCliente.setBounds(1090, 320, 250, 35);
		getContentPane().add(btnCadastrarCliente);

		btnExcluirCliente = new JButton("EXCLUIR CLIENTE");
		btnExcluirCliente.setIcon(new ImageIcon("images/icons8-excluir-usuário-macho-24.png"));
		btnExcluirCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnExcluirCliente.setBackground(Color.WHITE);
		btnExcluirCliente.setToolTipText("SELECIONE UM CLIENTE");
		btnExcluirCliente.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnExcluirCliente.setBounds(1090, 365, 250, 35);
		getContentPane().add(btnExcluirCliente);

		btnEditarCliente = new JButton("EDITAR CLIENTE");
		btnEditarCliente.setIcon(new ImageIcon("images/icons8-editar-usuário-masculino-32.png"));
		btnEditarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditarCliente.setBackground(Color.WHITE);
		btnEditarCliente.setToolTipText("SELECIONE UM CLIENTE");
		btnEditarCliente.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEditarCliente.setBounds(1090, 410, 250, 35);
		getContentPane().add(btnEditarCliente);

		textFieldFiltroClientes = new JTextField();
		textFieldFiltroClientes.setToolTipText("DIGITE O NOME DO CLIENTE QUE ESTÁ BUSCANDO");
		textFieldFiltroClientes.setBounds(620, 155, 225, 35);
		getContentPane().add(textFieldFiltroClientes);
		textFieldFiltroClientes.setColumns(10);

		buttonFiltroClientes = new JButton("FILTRAR CLIENTE");
		buttonFiltroClientes.setIcon(new ImageIcon("images/icons8-cliente-de-pesquisa-24.png"));
		buttonFiltroClientes.setFont(new Font("SansSerif", Font.BOLD, 15));
		buttonFiltroClientes.setBackground(Color.WHITE);
		buttonFiltroClientes.setBounds(845, 155, 222, 35);
		getContentPane().add(buttonFiltroClientes);

		buttonFiltro = new JButton("FILTRAR PRODUTO");
		buttonFiltro.setIcon(new ImageIcon("images/icons8-pesquisar-24.png"));
		buttonFiltro.setFont(new Font("SansSerif", Font.BOLD, 15));
		buttonFiltro.setBackground(Color.WHITE);
		buttonFiltro.setBounds(230, 155, 224, 35);
		getContentPane().add(buttonFiltro);

		btnRelatorioDeVendas = new JButton("RELAT\u00D3RIO DE VENDAS");
		btnRelatorioDeVendas.setIcon(new ImageIcon("images/icons8-baixar-relatório-gráfico-24.png"));
		btnRelatorioDeVendas.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorioDeVendas.setBackground(Color.WHITE);
		btnRelatorioDeVendas.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnRelatorioDeVendas.setBounds(1090, 455, 250, 35);
		getContentPane().add(btnRelatorioDeVendas);

		btnSair = new JButton("SAIR");
		btnSair.setIcon(new ImageIcon("images/icons8-logout-arredondado-acima-32.png"));
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);
		btnSair.setBackground(Color.WHITE);
		btnSair.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSair.setBounds(1090, 500, 250, 35);
		getContentPane().add(btnSair);

		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteVendedor ouvinteVendedor = new OuvinteVendedor(this);
		this.buttonFiltroClientes.addActionListener(ouvinteVendedor);
		this.buttonFiltro.addActionListener(ouvinteVendedor);
		this.btnAdicionarAoCarrinho.addActionListener(ouvinteVendedor);
		this.btnCadastrarCliente.addActionListener(ouvinteVendedor);
		this.btnCancelarCompra.addActionListener(ouvinteVendedor);
		this.btnEditarCliente.addActionListener(ouvinteVendedor);
		this.btnExcluirCliente.addActionListener(ouvinteVendedor);
		this.btnRelatorioDeVendas.addActionListener(ouvinteVendedor);
		this.btnSair.addActionListener(ouvinteVendedor);
		this.btnVerCarrinho.addActionListener(ouvinteVendedor);
		this.buttonFiltro.addActionListener(ouvinteVendedor);
		this.buttonFiltroClientes.addActionListener(ouvinteVendedor);
		OuvinteTableCliente ouvinteTableCliente = new OuvinteTableCliente(this);
		this.tableClientes.addMouseListener(ouvinteTableCliente);
		OuvinteTableProduto ouvinteTableProduto = new OuvinteTableProduto(this);
		this.tableProdutos.addMouseListener(ouvinteTableProduto);
	}

	public void filtrarClientes (Filtro filtro, String textFiltro, DefaultTableModel model, JTable table) {
		InvokerFiltro invokerFiltro = new InvokerFiltro();
		invokerFiltro.setFiltro(filtro, textFiltro, model, table);
	}

	public void filtrarProdutos (Filtro filtro, String textFiltro, DefaultTableModel model, JTable table) {
		InvokerFiltro invokerFiltro = new InvokerFiltro();
		invokerFiltro.setFiltro(filtro, textFiltro, model, table);
	}

	public ClienteDTO clienteSelecionado () {
		if (tableClientes.getSelectedRow() != -1) {
			clienteDTO = new ClienteDTO();
			clienteDTO.setIdCliente(Integer.parseInt(tableClientes.getValueAt(tableClientes.getSelectedRow(), 0).toString()));
			clienteDTO = controllerCliente.recuperarCliente(clienteDTO);
		}
		return clienteDTO;
	}

	public void iniciarVenda () {

		if (vendaDTO == null && tableClientes.getSelectedRow() != -1 && tableProdutos.getSelectedRow() != -1) {
			int confirmacao = JOptionPane.showConfirmDialog(this, "DESEJA INICAR UMA VENDA?");
			if (confirmacao == JOptionPane.YES_OPTION) {
				vendaDTO = new VendaDTO();
				vendaDTO.setIdCliente(clienteSelecionado().getIdCliente());
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
				funcionarioDTO.setNome(vendedor);
				try {
					funcionarioDTO = controllerGerente.recuperarFuncionario(funcionarioDTO);
				} catch (FuncionarioNaoExisteException e) {}
				vendaDTO.setIdVendedor(funcionarioDTO.getIdFuncionario());
				vendaDTO.setProdutos(produtoDTOs);
			}
		} else if (vendaDTO == null && tableClientes.getSelectedRow() == -1 && tableProdutos.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this, "SELECIONE UM CLIENTE E UM PRODUTO PARA INICIAR UMA VENDA");
		} else if (tableClientes.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "SELECIONE UM CLIENTE");
		} else if (tableProdutos.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "SELECIONE UM PRODUTO");
		}
	}

	public ProdutoDTO produtoSelecionado () {
		if (tableProdutos.getSelectedRow() != -1) {
			produtoDTO = new ProdutoDTO();
			produtoDTO.setId_produto(Integer.parseInt(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 0).toString()));
			produtoDTO = controllerProduto.recuperarProduto(produtoDTO);
		} 
		return produtoDTO;
	}

	public boolean adicionarACesta (ProdutoDTO produtoDTO) {

		if (controllerProduto.verificarEstoque(produtoDTO)) {
			for (ProdutoDTO produto: vendaDTO.getProdutos()) {
				if (produto.getNome().equals(produtoDTO.getNome())) {
					produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() - produtoDTO.getQtdPedida());
					controllerProduto.atualizarProduto(produtoDTO);
					produtoDTO.setQtdPedida(produtoDTO.getQtdPedida() + produto.getQtdPedida());
					vendaDTO.getProdutos().remove(produto);
					vendaDTO.getProdutos().add(produtoDTO);
					return true;
				}
			}
			vendaDTO.getProdutos().add(produtoDTO);
			produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() - produtoDTO.getQtdPedida());
			controllerProduto.atualizarProduto(produtoDTO);
			return true;
		}
		return false;
	}

	public void cancelarVenda () {

		for (ProdutoDTO produtoDTO : vendaDTO.getProdutos()) {
			produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + produtoDTO.getQtdPedida());
			controllerProduto.atualizarProduto(produtoDTO);
		}
	}

	private class OuvinteVendedor implements ActionListener {

		private ViewVendedor viewVendedor;

		public OuvinteVendedor (ViewVendedor viewVendedor) {
			this.viewVendedor = viewVendedor;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {

			case "SAIR":
				if (vendaDTO != null)
					cancelarVenda();
				viewVendedor.dispose();
				new ViewInicial();
				break;

			case "FILTRAR CLIENTE":

				viewVendedor.filtrarClientes(new FiltroClientes(), textFieldFiltroClientes.getText(), modelClientes, tableClientes);
				viewVendedor.repaint();

				break;

			case "FILTRAR PRODUTO":

				viewVendedor.filtrarProdutos(new FiltroProdutos(), textFieldFiltroProdutos.getText(), modelProdutos, tableProdutos);
				viewVendedor.repaint();

				break;

			case "RELATÓRIO DE VENDAS":

				int confirm = JOptionPane.showConfirmDialog(viewVendedor, "DESEJA IMPRIMIR O RELATÓRIO DE VENDAS?");
				if (confirm == JOptionPane.YES_OPTION) {
					Object[] data = new Object[4];
					data[0] = "RELATÓRIO DO DIA";
					data[1] = "RELATÓRIO DA ÚLTIMA SEMANA";
					data[2] = "RELATÓRIO DO MÊS";
					data[3] = "RELATÓRIO COMPLETO";

					Object opcData = JOptionPane.showInputDialog(viewVendedor, "ESCOLHA UMA OPÇÃO: ","VENDAS", JOptionPane.PLAIN_MESSAGE, null, data, data[0]);
					String opcs = opcData.toString();
					controllerVendedor.PDF(vendedor, opcs);
				}
				break;

			case "CADASTRAR CLIENTE":
				new ViewCadastroCliente(null, viewVendedor);
				break;

			case "EDITAR CLIENTE":
				try {
					if (tableClientes.getSelectedRow() != -1) {
						ClienteDTO clienteDTO = clienteSelecionado();
						if (clienteDTO != null)
							new ViewCadastroCliente(clienteDTO, viewVendedor);
					} else {
						JOptionPane.showMessageDialog(viewVendedor, "SELECIONE UM CLIENTE", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e2) {}
				break;

			case "EXCLUIR CLIENTE":
				try {
					if (tableClientes.getSelectedRow() != -1) {
						ClienteDTO clienteDTO = clienteSelecionado();
						if (clienteDTO != null) {
							int confirmacao = JOptionPane.showConfirmDialog(viewVendedor, "TEM CERTEZA QUE DESEJA EXCLUIR ESTE CLIENTE?");
							if (confirmacao == JOptionPane.YES_OPTION) {
								controllerCliente.deletarCliente(clienteSelecionado());
								viewVendedor.dispose();
								new ViewVendedor(vendedor, vendaDTO);
							}
						}
					} else {
						JOptionPane.showMessageDialog(viewVendedor, "SELECIONE UM CLIENTE", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e2) {}
				break;

			case "CANCELAR VENDA":
				try {
					if (vendaDTO != null) {
						int confirmacao = JOptionPane.showConfirmDialog(viewVendedor, "TEM CERTEZA QUE DESEJA CANCELAR ESTA VENDA?\nTODOS OS DADOS SERÃO PERDIDOS");
						if (confirmacao == JOptionPane.YES_OPTION) {
							cancelarVenda();
							controllerVendedor.cancelarVenda(vendaDTO);
							viewVendedor.dispose();
							new ViewVendedor(vendedor, null);
						}
					} else {
						JOptionPane.showMessageDialog(viewVendedor, "NENHUMA VENDA FOI INICIADA", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e2) {

				}
				break;

			case "ADICIONAR A CESTA":
				iniciarVenda();
				if (vendaDTO != null && produtoSelecionado() != null && clienteSelecionado() != null) {
					ProdutoDTO produtoDTO = produtoSelecionado();
					String quant = JOptionPane.showInputDialog("QUANTAS UNIDADES DESTE PRODUTO DESEJA VENDER: ");
					int qtd = Integer.parseInt(quant);
					produtoDTO.setQtdPedida(qtd);
					if (adicionarACesta(produtoDTO)) {

						JOptionPane.showMessageDialog(viewVendedor, "PRODUTO ADICIONADO A CESTA", "SUCESSO", JOptionPane.PLAIN_MESSAGE);

						viewVendedor.dispose();
						ViewVendedor viewVendedor = new ViewVendedor(vendedor, vendaDTO);
						viewVendedor.setVisible(false);
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setIdCliente(vendaDTO.getIdCliente());
						clienteDTO = controllerCliente.recuperarCliente(clienteDTO);
						clienteDTO.setFiltro(Integer.toString(clienteDTO.getIdCliente()));
						clienteDTO = controllerCliente.filtroCliente(clienteDTO);
						if (clienteDTO != null) {
							viewVendedor.modelClientes = new DefaultTableModel() {
								public boolean isCellEditable(int rowIndex, int columIndex) {
									return false;
								}  
							};

							viewVendedor.modelClientes.addColumn("ID");
							viewVendedor.modelClientes.addColumn("Nome");

							ContextStrategy contextStrategyClientes = new ContextStrategy();
							Iterator iteratorClientes = contextStrategyClientes.kindIterator(clienteDTO.getTable());

							while (iteratorClientes.hasNext()) {
								String cliente[] = iteratorClientes.next().toString().split("%");
								if (cliente != null) {
									Object[] row = new Object[] {
											cliente[0],
											cliente[1]
									};
									viewVendedor.modelClientes.addRow(row);
								}
							}
							viewVendedor.tableClientes.setModel(viewVendedor.modelClientes);
							viewVendedor.repaint();
							viewVendedor.tableClientes.selectAll();
							viewVendedor.setVisible(true);
						}

					}
					else
						JOptionPane.showMessageDialog(viewVendedor, "QUANTIDADE EXCEDEU O NÚMERO DISPONÍVEL NO ESTOQUE", "ERRO", JOptionPane.WARNING_MESSAGE);
				}
				break;

			case "CESTA DE PRODUTOS":
				if (vendaDTO != null) {
					viewVendedor.dispose();
					new ViewCestaProdutos(vendaDTO, vendedor);
				} else {
					JOptionPane.showMessageDialog(viewVendedor, "A CESTA ESTÁ VAZIA", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
		}
	}

	private class OuvinteTableCliente implements MouseListener {

		private ViewVendedor viewVendedor;

		public OuvinteTableCliente (ViewVendedor viewVendedor) {
			this.viewVendedor = viewVendedor;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				ClienteDTO clienteDTO = clienteSelecionado();
				JOptionPane.showMessageDialog(viewVendedor, "NOME: " + clienteDTO.getNome() + "\nE-MAIL: " + clienteDTO.getEmail() + "\nTELEFONE: " + clienteDTO.getTelefone(),"DADOS DO CLIENTE", JOptionPane.PLAIN_MESSAGE);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {	}

		@Override
		public void mousePressed(MouseEvent e) {	}

		@Override
		public void mouseReleased(MouseEvent e) {}

	}

	private class OuvinteTableProduto implements MouseListener {

		private ViewVendedor viewVendedor;

		public OuvinteTableProduto (ViewVendedor viewVendedor) {
			this.viewVendedor = viewVendedor;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				ProdutoDTO produtoDTO = produtoSelecionado();
				JOptionPane.showMessageDialog(viewVendedor, "NOME: " + produtoDTO.getNome() + "\nDESCRIÇÃO: " + produtoDTO.getDescricao() + "\nPREÇO: " + produtoDTO.getPreco() + "\nQUANT. ESTOQUE: " + produtoDTO.getQtdEstoque(),"DADOS DO PRODUTO", JOptionPane.PLAIN_MESSAGE);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

	}
}
