package View;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import Controller.ControllerCliente;
import DTO.ClienteDTO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class ViewCadastroCliente extends ViewPadrao {

	private JTextField textFieldNome, textFieldTelefone, textFieldEmail;
	private JButton btnVoltar, btnCadastrar;
	private ClienteDTO clienteDTO;
	private ViewVendedor viewVendedor;

	public ViewCadastroCliente(ClienteDTO clienteDTO, ViewVendedor viewVendedor) {

		super();
		this.clienteDTO = clienteDTO;
		this.viewVendedor = viewVendedor;
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);

		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCliente.setBounds(210, 5, 250, 30);
		getContentPane().add(lblCliente);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNome.setBounds(15, 65, 50, 25);
		getContentPane().add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldNome.setBounds(75, 65, 400, 30);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblEmail.setBounds(15, 110, 50, 25);
		getContentPane().add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldEmail.setBounds(75, 115, 300, 30);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTelefone.setBounds(15, 164, 70, 20);
		getContentPane().add(lblTelefone);
		
		try {
			MaskFormatter maskTel = new MaskFormatter("#####.####");
			textFieldTelefone = new JFormattedTextField(maskTel);
			textFieldTelefone.setFont(new Font("SansSerif", Font.PLAIN, 14));
			textFieldTelefone.setBounds(95, 165, 380, 30);
			getContentPane().add(textFieldTelefone);
			textFieldTelefone.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setIcon(new ImageIcon("images/icons8-voltar-30.png"));
		btnVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVoltar.setBounds(75, 225, 170, 35);
		getContentPane().add(btnVoltar);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setIcon(new ImageIcon("images/icons8-adicionar-usuário-masculino-32.png"));
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCadastrar.setBounds(260, 225, 170, 35);
		getContentPane().add(btnCadastrar);
		
		JLabel lblOpcional = new JLabel("OPCIONAL");
		lblOpcional.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblOpcional.setBounds(385, 115, 100, 25);
		getContentPane().add(lblOpcional);

		CadastrarOuEditar();
		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteCliente ouvinteCliente = new OuvinteCliente(this);
		this.btnCadastrar.addActionListener(ouvinteCliente);
		this.btnVoltar.addActionListener(ouvinteCliente);
	}

	public void CadastrarOuEditar() {
		if (clienteDTO != null) {
			btnCadastrar.setText("EDITAR");
			btnCadastrar.setIcon(new ImageIcon("images/icons8-editar-usuário-masculino-32.png"));
			btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldNome.setText(clienteDTO.getNome());
			textFieldEmail.setText(clienteDTO.getEmail());
			textFieldTelefone.setText(clienteDTO.getTelefone());
		}
	}

	public String nomeCliente () {
		return textFieldNome.getText();
	}

	public String emailCliente () {
		return textFieldEmail.getText();
	}

	public String telefoneCliente () {
		return textFieldTelefone.getText();
	}

	public boolean camposValidos () {
		if (textFieldNome.getText().equals("") || textFieldTelefone.getText().equals("")) {
			return false;
		}
		return true;
	}

	public class OuvinteCliente implements ActionListener {

		private ViewCadastroCliente viewCadastroCliente;
		private ControllerCliente controllerCliente = new ControllerCliente();

		public OuvinteCliente (ViewCadastroCliente viewCadastroCliente) {
			this.viewCadastroCliente = viewCadastroCliente;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {
			case "VOLTAR":
				viewCadastroCliente.dispose();
				break;

			case "CADASTRAR":
				try {
					if (camposValidos()) {
						clienteDTO = new ClienteDTO();
						clienteDTO.setNome(nomeCliente());
						clienteDTO.setEmail(emailCliente());
						clienteDTO.setTelefone(telefoneCliente());
						controllerCliente.cadastrarCliente(clienteDTO);
						JOptionPane.showMessageDialog(viewCadastroCliente, "CLIENTE CADASTRADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
						int opc = JOptionPane.showConfirmDialog(viewCadastroCliente, "DESEJA CONTINUAR CADASTRANDO CLIENTES?");
						if (opc == JOptionPane.YES_OPTION) {
							viewCadastroCliente.textFieldEmail.setText("");
							viewCadastroCliente.textFieldNome.setText("");
							viewCadastroCliente.textFieldTelefone.setText("");
						}
						else {
							viewCadastroCliente.dispose();
							viewVendedor.dispose();
							new ViewVendedor(viewVendedor.vendedor, null);
						}
					} else {
						JOptionPane.showMessageDialog(viewCadastroCliente, "CAMPOS EM BRANCO", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ClienteDuplicadoExcpetion e1) {
					JOptionPane.showMessageDialog(viewCadastroCliente, "POR FAVOR INSIRA UM NOME DIFERENTE", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "EDITAR":
				try {
					if (camposValidos()) {
						clienteDTO.setNome(nomeCliente());
						clienteDTO.setEmail(emailCliente());
						clienteDTO.setTelefone(telefoneCliente());
						controllerCliente.atualizarCliente(clienteDTO);
						JOptionPane.showMessageDialog(viewCadastroCliente, "CLIENTE EDITADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
						viewCadastroCliente.dispose();
						viewVendedor.dispose();
						new ViewVendedor(viewVendedor.vendedor, null);
					} else {
						JOptionPane.showMessageDialog(viewCadastroCliente, "CAMPOS EM BRANCO", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(viewCadastroCliente, e2.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
		}


	}
}
