package View;

import DTO.ClienteDTO;
import DTO.ContabilidadeDTO;
import DTO.FuncionarioDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;
import Model.PDF;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Controller.ControllerCliente;
import Controller.ControllerContabilidade;
import Controller.ControllerGerente;
import Controller.ControllerProduto;
import Controller.ControllerVendedor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewVenda extends ViewPadrao {

	private VendaDTO vendaDTO;
	private JButton btnCancelarVenda, btnFecharVenda;
	private ControllerContabilidade controllerContabilidade = new ControllerContabilidade();
	private ControllerCliente controllerCliente = new ControllerCliente();
	private ControllerGerente controllerVendedor = new ControllerGerente();
	private ControllerVendedor controllerVenda = new ControllerVendedor();
	private ControllerProduto controllerProduto = new ControllerProduto();
	private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
	private ClienteDTO clienteDTO = new ClienteDTO();

	public ViewVenda(VendaDTO vendaDTO) {
		
		super();
		this.vendaDTO = vendaDTO;
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);

		JLabel lblVenda = new JLabel("VENDA");
		lblVenda.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblVenda.setBounds(310, 5, 250, 25);
		getContentPane().add(lblVenda);

		funcionarioDTO.setIdFuncionario(vendaDTO.getIdVendedor());
		try {
			funcionarioDTO = controllerVendedor.recuperarFuncionario(funcionarioDTO);
		} catch (FuncionarioNaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblVendedora = new JLabel("VENDEDOR(A): " + funcionarioDTO.getNome());
		lblVendedora.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblVendedora.setBounds(5, 90, 500, 25);
		getContentPane().add(lblVendedora);

		clienteDTO.setIdCliente(vendaDTO.getIdCliente());
		clienteDTO = controllerCliente.recuperarCliente(clienteDTO);
		JLabel lblCliente = new JLabel("CLIENTE: " + clienteDTO.getNome());
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCliente.setBounds(5, 140, 500, 25);
		getContentPane().add(lblCliente);

		JLabel lblProdutos = new JLabel("PRODUTOS: ");
		lblProdutos.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblProdutos.setBounds(5, 190, 150, 25);
		getContentPane().add(lblProdutos);

		String produtos[] = new String[this.vendaDTO.getProdutos().size()];
		int i = 0;
		for (ProdutoDTO produtoDTO: this.vendaDTO.getProdutos()) {
			produtos[i] = produtoDTO.getNome() + " | Quant. " + produtoDTO.getQtdPedida();
			i++;
		}
		JComboBox comboBox = new JComboBox(produtos);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		comboBox.setBounds(110, 190, 300, 30);
		getContentPane().add(comboBox);

		JLabel lblValorTotal = new JLabel("VALOR TOTAL: " + vendaDTO.getPrecoTotal() + " R$");
		lblValorTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblValorTotal.setBounds(430, 190, 300, 25);
		getContentPane().add(lblValorTotal);

		btnCancelarVenda = new JButton("CANCELAR VENDA");
		btnCancelarVenda.setIcon(new ImageIcon("images/icons8-excluir-26.png"));
		btnCancelarVenda.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelarVenda.setBackground(Color.WHITE);
		btnCancelarVenda.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCancelarVenda.setBounds(100, 270, 250, 35);
		getContentPane().add(btnCancelarVenda);

		btnFecharVenda = new JButton("FECHAR VENDA");
		btnFecharVenda.setIcon(new ImageIcon("images/icons8-finalizar-pedido-24.png"));
		btnFecharVenda.setHorizontalAlignment(SwingConstants.LEFT);
		btnFecharVenda.setBackground(Color.WHITE);
		btnFecharVenda.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnFecharVenda.setBounds(360, 270, 250, 35);
		getContentPane().add(btnFecharVenda);

		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteVenda ouvinteVenda = new OuvinteVenda(this);
		this.btnCancelarVenda.addActionListener(ouvinteVenda);
		this.btnFecharVenda.addActionListener(ouvinteVenda);
	}

	public class OuvinteVenda implements ActionListener {

		private ViewVenda viewVenda;

		public OuvinteVenda (ViewVenda viewVenda) {
			this.viewVenda = viewVenda;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {
			case "CANCELAR VENDA":
				int confirmacao = JOptionPane.showConfirmDialog(viewVenda, "TEM CERTEZA QUE DESEJA CANCELAR ESTA VENDA?\nTODOS OS DADOS SERÃO PERDIDOS");
				if (confirmacao == JOptionPane.YES_OPTION) {
					viewVenda.dispose();
					for (ProdutoDTO produtoDTO : vendaDTO.getProdutos()) {
						produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + produtoDTO.getQtdPedida());
						controllerProduto.atualizarProduto(produtoDTO);
					}
					controllerVenda.cancelarVenda(vendaDTO);
					new ViewVendedor(funcionarioDTO.getNome(), null);
				}
				break;

			case "FECHAR VENDA":
				confirmacao = JOptionPane.showConfirmDialog(viewVenda, "TEM CERTEZA QUE DESEJA ENCERRAR ESTA VENDA?");
				if (confirmacao == JOptionPane.YES_OPTION) {
					VendaDTO DTOvenda = controllerVenda.realizarVenda(vendaDTO);
					int qtdVendidos = 0;
					for (ProdutoDTO produtosPedidos: vendaDTO.getProdutos()) {
						qtdVendidos += produtosPedidos.getQtdPedida();
					}
					ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();
					contabilidadeDTO.setLucroMensal(vendaDTO.getPrecoTotal());
					contabilidadeDTO.setQtdVendidos(qtdVendidos);
					contabilidadeDTO.setMesAtual(controllerContabilidade.dataAtual(DTOvenda));
					contabilidadeDTO.setId_venda(DTOvenda.getIdVenda());
					controllerContabilidade.atualizarContabilidade(contabilidadeDTO);
					JOptionPane.showMessageDialog(viewVenda, "CLIENTE: " + clienteDTO.getNome() + "\n" + "VENDEDOR(A): " + funcionarioDTO.getNome() + "\nVALOR: R$" + vendaDTO.getPrecoTotal(), "DADOS DA VENDA", JOptionPane.PLAIN_MESSAGE);
					String troco = JOptionPane.showInputDialog("DIGITE O VALOR PARA CALCULAR O TROCO DO CLIENTE: ");
					//String trocoFormat[] = troco.split(",");
					float trocoCliente = Float.parseFloat(troco);
					trocoCliente = trocoCliente - vendaDTO.getPrecoTotal();
					if (trocoCliente >= 0) {
						JOptionPane.showMessageDialog(viewVenda, "O CLIENTE IRÁ RECEBER " + trocoCliente + " R$ DE TROCO");
					}
					else 
						JOptionPane.showMessageDialog(viewVenda, "FALTAM " + trocoCliente + " R$ A SEREM PAGOS");
					JOptionPane.showMessageDialog(viewVenda, "EMITINDO NOTA FISCAL...", "NOTA FISCAL", JOptionPane.PLAIN_MESSAGE);
					//NOTA FISCAL
					vendaDTO.setData_venda(controllerContabilidade.dataAtual(vendaDTO));
					controllerVenda.notaFiscal(funcionarioDTO, clienteDTO, vendaDTO);
					JOptionPane.showMessageDialog(viewVenda, "OBRIGADO(A) POR COMPRAR NA LOJA A BOUTIQUE", "A BOUTIQUE", JOptionPane.PLAIN_MESSAGE);
					viewVenda.dispose();
					new ViewVendedor(funcionarioDTO.getNome(), null);
					break;
				}
			}
		}

	}

}

