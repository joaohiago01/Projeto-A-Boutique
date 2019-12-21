package View;

import DTO.ProdutoDTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.ControllerProduto;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewCadastroProduto extends ViewPadrao {

	private ProdutoDTO produtoDTO;
	private JTextField textFieldNome, textFieldPreco, textFieldQuant;
	private JTextArea textAreaDescricao;
	private JButton btnVoltar, btnCadastrar;
	private ControllerProduto controllerProduto = new ControllerProduto();
	private ViewGerente viewGerente;
	
	public ViewCadastroProduto(ProdutoDTO produtoDTO, ViewGerente viewGerente) {
	
		super();
		this.produtoDTO = produtoDTO;
		this.viewGerente = viewGerente;
		this.setSize(700, 520);
		this.setLocationRelativeTo(null);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblProduto.setBounds(300, 5, 150, 30);
		getContentPane().add(lblProduto);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNome.setBounds(5, 90, 50, 25);
		getContentPane().add(lblNome);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDescrio.setBounds(5, 140, 80, 25);
		getContentPane().add(lblDescrio);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldNome.setBounds(55, 90, 600, 30);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textAreaDescricao.setBounds(90, 140, 565, 150);
		getContentPane().add(textAreaDescricao);
		
		JLabel lblQuantidadeDisponivelNo = new JLabel("Quantidade Disponivel No Estoque:");
		lblQuantidadeDisponivelNo.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblQuantidadeDisponivelNo.setBounds(5, 352, 300, 25);
		getContentPane().add(lblQuantidadeDisponivelNo);
		
		JLabel lblPreoUnitrio = new JLabel("Pre\u00E7o Unit\u00E1rio:");
		lblPreoUnitrio.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPreoUnitrio.setBounds(5, 302, 150, 25);
		getContentPane().add(lblPreoUnitrio);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldPreco.setBounds(120, 302, 100, 35);
		getContentPane().add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		textFieldQuant = new JTextField();
		textFieldQuant.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldQuant.setBounds(265, 352, 100, 35);
		getContentPane().add(textFieldQuant);
		textFieldQuant.setColumns(10);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setIcon(new ImageIcon("images/icons8-voltar-30.png"));
		btnVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVoltar.setBounds(100, 421, 250, 35);
		getContentPane().add(btnVoltar);
		
		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setIcon(new ImageIcon("images/icons8-adicionar-24.png"));
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCadastrar.setBounds(360, 421, 250, 35);
		getContentPane().add(btnCadastrar);
		
		cadastraOuEditar();
		addListener();
		setVisible(true);
	}
	
	public void addListener () {
		OuvinteProduto ouvinteProduto = new OuvinteProduto(this);
		this.btnCadastrar.addActionListener(ouvinteProduto);
		this.btnVoltar.addActionListener(ouvinteProduto);
	}

	public String nomeProduto () {
		return textFieldNome.getText();
	}
	
	public float precoProduto () {
		/*String p[] = textFieldPreco.getText().split(",");
		String preco = p[0] + "." + p[1];*/
		return Float.parseFloat(textFieldPreco.getText());
	}
	
	public int quantProduto () {
		return Integer.parseInt(textFieldQuant.getText());
	}
	
	public String descricaoProduto () {
		return textAreaDescricao.getText();
	}
	
	public void cadastraOuEditar () {
		if (produtoDTO != null) {
			btnCadastrar.setText("EDITAR");
			btnCadastrar.setIcon(new ImageIcon("images/icons8-editar-32.png"));
			btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
			textAreaDescricao.setText(produtoDTO.getDescricao());
			textFieldNome.setText(produtoDTO.getNome());
			textFieldPreco.setText(Float.toString(produtoDTO.getPreco()));
			textFieldQuant.setText(Integer.toString(produtoDTO.getQtdEstoque()));
		}
	}
	
	public boolean camposEmBranco () {
		if (textAreaDescricao.getText().equals("") || textFieldNome.getText().equals("") || textFieldPreco.getText().equals("") || textFieldQuant.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public class OuvinteProduto implements ActionListener {
		
		private ViewCadastroProduto viewCadastroProduto;
		
		public OuvinteProduto (ViewCadastroProduto viewCadastroProduto) {
			this.viewCadastroProduto = viewCadastroProduto;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String button = e.getActionCommand();
			
			switch (button) {
			case "VOLTAR":
				viewCadastroProduto.dispose();
				break;

			case "CADASTRAR":
				
				if (camposEmBranco()) {
					produtoDTO = new ProdutoDTO();
					produtoDTO.setDescricao(descricaoProduto());
					produtoDTO.setNome(nomeProduto());
					produtoDTO.setQtdEstoque(quantProduto());
					
					produtoDTO.setPreco(precoProduto());
					controllerProduto.cadastrarProduto(produtoDTO);
					JOptionPane.showMessageDialog(viewCadastroProduto, "PRODUTO CADASTRADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
					int confir = JOptionPane.showConfirmDialog(viewCadastroProduto, "DESEJA CONTINUAR CADASTRADNO PRODUTOS?");
					if (confir == JOptionPane.YES_OPTION) {
						textAreaDescricao.setText("");
						textFieldNome.setText("");
						textFieldPreco.setText("");
						textFieldQuant.setText("");
					} else {
						viewCadastroProduto.dispose();
						viewGerente.dispose();
						new ViewGerente(viewGerente.vendedor);
					}
				} else {
					JOptionPane.showMessageDialog(viewCadastroProduto, "CAMPOS EM BRANCO", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				
				break;
				
			case "EDITAR":
				
				if (camposEmBranco()) {
					produtoDTO.setDescricao(descricaoProduto());
					produtoDTO.setNome(nomeProduto());
					produtoDTO.setQtdEstoque(quantProduto());
					produtoDTO.setPreco(precoProduto());
					controllerProduto.atualizarProduto(produtoDTO);
					JOptionPane.showMessageDialog(viewCadastroProduto, "PRODUTO EDITADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
					viewCadastroProduto.dispose();
					viewGerente.dispose();
					new ViewGerente(viewGerente.vendedor);
				} else {
					JOptionPane.showMessageDialog(viewCadastroProduto, "CAMPOS EM BRANCO", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
		}	
	}
}
