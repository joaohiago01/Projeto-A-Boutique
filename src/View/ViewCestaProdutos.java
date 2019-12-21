package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerCliente;
import Controller.ControllerProduto;
import DTO.ClienteDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class ViewCestaProdutos extends ViewPadrao {

	private VendaDTO vendaDTO;
	private JTable tableProdutos;
	private JButton btnVoltar,  btnRemoverProduto, btnFinalizarVenda;
	private String vendedor;
	private float valorTotal;
	private ControllerCliente controllerCliente = new ControllerCliente();
	private ControllerProduto controllerProduto = new ControllerProduto();

	public ViewCestaProdutos(VendaDTO vendaDTO, String vendedor) {

		super();
		this.vendaDTO = vendaDTO;
		this.vendedor = vendedor;

		this.setLocationRelativeTo(null);

		JLabel lblCestaDeProdutos = new JLabel("CESTA DE PRODUTOS");
		lblCestaDeProdutos.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCestaDeProdutos.setBounds(390, 5, 250, 30);
		getContentPane().add(lblCestaDeProdutos);

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(this.vendaDTO.getIdCliente());
		JLabel lblCliente = new JLabel("CLIENTE: " + controllerCliente.recuperarCliente(clienteDTO).getNome());
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblCliente.setBounds(5, 80, 500, 25);
		getContentPane().add(lblCliente);

		JLabel lblProdutos = new JLabel("PRODUTOS: ");
		lblProdutos.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblProdutos.setBounds(5, 130, 250, 25);
		getContentPane().add(lblProdutos);

		ContextStrategy contextStrategyValorTotal = new ContextStrategy();
		Iterator iteratorValorTotal = contextStrategyValorTotal.kindIterator(vendaDTO.getProdutos());

		while (iteratorValorTotal.hasNext()) {
			ProdutoDTO produtoDTO = (ProdutoDTO) iteratorValorTotal.next();
			produtoDTO.setPrecoPorQuant(produtoDTO.getPreco() * produtoDTO.getQtdPedida());
			valorTotal += produtoDTO.getPrecoPorQuant();
		}

		DefaultTableModel modelProdutos = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columIndex) {
				return false;
			}  
		};

		modelProdutos.addColumn("Nome");
		modelProdutos.addColumn("Preço Unitário");
		modelProdutos.addColumn("Quantidade");
		modelProdutos.addColumn("Valor");

		ContextStrategy contextStrategyProdutos = new ContextStrategy();
		Iterator iterator = contextStrategyProdutos.kindIterator(vendaDTO.getProdutos());

		while (iterator.hasNext()) {
			ProdutoDTO produtoDTO = (ProdutoDTO) iterator.next();
			produtoDTO.setPrecoPorQuant(produtoDTO.getPreco() * produtoDTO.getQtdPedida());
			Object[] row = new Object[] {
					produtoDTO.getNome(),
					produtoDTO.getPreco(),
					produtoDTO.getQtdPedida(),
					produtoDTO.getPrecoPorQuant()
			};

			modelProdutos.addRow(row);
		}

		tableProdutos = new JTable(modelProdutos);
		tableProdutos.setToolTipText("CLIQUE DUAS VEZES PARA MAIS INFORMAÇÕES");
		tableProdutos.setBounds(0, 0, 1, 1);
		getContentPane().add(tableProdutos);

		JScrollPane scrollPaneProdutos = new JScrollPane(tableProdutos);
		scrollPaneProdutos.setBounds(130, 130, 865, 400);
		getContentPane().add(scrollPaneProdutos);

		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setIcon(new ImageIcon("images/icons8-voltar-30.png"));
		btnVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVoltar.setBounds(90, 560, 250, 35);
		getContentPane().add(btnVoltar);

		btnRemoverProduto = new JButton("REMOVER PRODUTO");
		btnRemoverProduto.setIcon(new ImageIcon("images/icons8-excluir-26.png"));
		btnRemoverProduto.setHorizontalAlignment(SwingConstants.LEFT);
		btnRemoverProduto.setBackground(Color.WHITE);
		btnRemoverProduto.setToolTipText("SELECIONE UM PRODUTO");
		btnRemoverProduto.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnRemoverProduto.setBounds(350, 560, 250, 35);
		getContentPane().add(btnRemoverProduto);

		btnFinalizarVenda = new JButton("FINALIZAR VENDA");
		btnFinalizarVenda.setIcon(new ImageIcon("images/icons8-finalizar-pedido-24.png"));
		btnFinalizarVenda.setHorizontalAlignment(SwingConstants.LEFT);
		btnFinalizarVenda.setBackground(Color.WHITE);
		btnFinalizarVenda.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnFinalizarVenda.setBounds(610, 560, 250, 35);
		getContentPane().add(btnFinalizarVenda);

		JLabel lblValorTotal = new JLabel("VALOR TOTAL: " + valorTotal);
		lblValorTotal.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblValorTotal.setBounds(565, 80, 305, 25);
		getContentPane().add(lblValorTotal);

		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteCestaProdutos ouvinteCestaProdutos = new OuvinteCestaProdutos(this);
		this.btnFinalizarVenda.addActionListener(ouvinteCestaProdutos);
		this.btnRemoverProduto.addActionListener(ouvinteCestaProdutos);
		this.btnVoltar.addActionListener(ouvinteCestaProdutos);
		OuvinteTableCesta ouvinteTableCesta = new OuvinteTableCesta(this);
		this.tableProdutos.addMouseListener(ouvinteTableCesta);
	}

	public ProdutoDTO produtoSelecionado () {

		ContextStrategy contextStrategyProdutos = new ContextStrategy();
		Iterator iterator = contextStrategyProdutos.kindIterator(vendaDTO.getProdutos());

		while (iterator.hasNext()) {
			ProdutoDTO produto = (ProdutoDTO) iterator.next();
			if (produto.getNome().equals(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 0).toString())) {
				return produto;
			}
		}
		return null;
	}

	public class OuvinteCestaProdutos implements ActionListener {

		private ViewCestaProdutos viewCestaProdutos;

		public OuvinteCestaProdutos (ViewCestaProdutos viewCestaProdutos) {
			this.viewCestaProdutos = viewCestaProdutos;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {
			case "VOLTAR":
				viewCestaProdutos.dispose();
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
			
				break;

			case "REMOVER PRODUTO":

				ContextStrategy contextStrategyProdutos = new ContextStrategy();
				Iterator iterator = contextStrategyProdutos.kindIterator(vendaDTO.getProdutos());
				Object[] opcs = new Object[Integer.parseInt(tableProdutos.getValueAt(tableProdutos.getSelectedRow(), 2).toString())];
				for (int i = 0; i <= opcs.length-1; i++) {
					opcs[i] = i+1;
				}
				
				int opc = JOptionPane.showConfirmDialog(viewCestaProdutos, "TEM CERTEZA QUE DESEJA REMOVER ESTE PRODUTO?");
				if (opc == JOptionPane.YES_OPTION) {
					Object quant = JOptionPane.showInputDialog(viewCestaProdutos, "QUANTAS UNIDADES DESTE PRODUTO DESEJA REMOVER?","REMOVER PRODUTO", JOptionPane.PLAIN_MESSAGE, null, opcs, opcs[0]);
					int qtd = Integer.parseInt(quant.toString());
					
					while (iterator.hasNext()) {
						ProdutoDTO produtoDTO = (ProdutoDTO) iterator.next();
						if (produtoDTO.getNome().equals(produtoSelecionado().getNome())) {
							produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + qtd);
							controllerProduto.atualizarProduto(produtoDTO);
							if (qtd >= produtoDTO.getQtdPedida()) {
								vendaDTO.getProdutos().remove(produtoDTO);
							} else {
								produtoDTO.setQtdPedida(produtoDTO.getQtdPedida() - qtd);
								vendaDTO.getProdutos().remove(produtoDTO);
								vendaDTO.getProdutos().add(produtoDTO);
							}
							JOptionPane.showMessageDialog(viewCestaProdutos, "PRODUTO REMOVIDO COM SUCESSO DA CESTA", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
							viewCestaProdutos.dispose();
							new ViewCestaProdutos(vendaDTO, viewCestaProdutos.vendedor);
							break;
						}
					}
				}
				break;

			case "FINALIZAR VENDA":

				opc = JOptionPane.showConfirmDialog(viewCestaProdutos, "TEM CERTEZA QUE DESEJA FINALIZAR ESTA VENDA?");
				if (opc == JOptionPane.YES_OPTION) {
					viewCestaProdutos.vendaDTO.setPrecoTotal(valorTotal);
					viewCestaProdutos.dispose();
					new ViewVenda(vendaDTO);
				}

				break;
			}
		}
	}

	public class OuvinteTableCesta implements MouseListener {

		private ViewCestaProdutos viewCestaProdutos;

		public OuvinteTableCesta (ViewCestaProdutos viewCestaProdutos) {
			this.viewCestaProdutos = viewCestaProdutos;
		}
		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				ProdutoDTO produtoDTO = produtoSelecionado();
				JOptionPane.showMessageDialog(viewCestaProdutos, "NOME: " + produtoDTO.getNome() + "\nDESCRIÇÃO: " + produtoDTO.getDescricao() + "\nPREÇO: " + produtoDTO.getPreco() + "\nQUANT. ESTOQUE: " + produtoDTO.getQtdEstoque(),"DADOS DO PRODUTO", JOptionPane.PLAIN_MESSAGE);
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
}
