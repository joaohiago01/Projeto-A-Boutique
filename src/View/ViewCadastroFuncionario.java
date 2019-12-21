package View;

import DTO.FuncionarioDTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import org.postgresql.util.PSQLException;

import Controller.ControllerGerente;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class ViewCadastroFuncionario extends ViewPadrao {

	private FuncionarioDTO funcionarioDTO;
	private JTextField textFieldNome, textFieldEmail, textFieldTelefone;
	private JPasswordField textFieldSenha;
	private JRadioButton rdbtnVendedor, rdbtnGerente;
	private JButton btnVoltar, btnCadastrar;
	private ViewGerente viewGerente;
	private ControllerGerente controllerGerente = new ControllerGerente();

	public ViewCadastroFuncionario(FuncionarioDTO funcionarioDTO, ViewGerente viewGerente) {
		
		super();
		this.funcionarioDTO = funcionarioDTO;
		this.viewGerente = viewGerente;
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);

		JLabel lblFuncionrio = new JLabel("FUNCION\u00C1RIO");
		lblFuncionrio.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblFuncionrio.setBounds(230, 5, 150, 30);
		getContentPane().add(lblFuncionrio);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNome.setBounds(5, 90, 50, 25);
		getContentPane().add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(55, 90, 500, 30);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSenha.setBounds(5, 150, 50, 25);
		getContentPane().add(lblSenha);

		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(55, 150, 500, 30);
		getContentPane().add(textFieldSenha);
		textFieldSenha.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setBounds(5, 210, 50, 25);
		getContentPane().add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(55, 210, 450, 30);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTelefone.setBounds(5, 260, 70, 25);
		getContentPane().add(lblTelefone);
		
		try {
			MaskFormatter maskTel = new MaskFormatter("#####.####");
			textFieldTelefone = new JFormattedTextField(maskTel);
			textFieldTelefone.setBounds(80, 260, 475, 30);
			getContentPane().add(textFieldTelefone);
			textFieldTelefone.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		rdbtnVendedor = new JRadioButton("Vendedor");
		rdbtnVendedor.setSelected(true);
		rdbtnVendedor.setFont(new Font("SansSerif", Font.BOLD, 15));
		rdbtnVendedor.setBounds(80, 320, 110, 25);
		getContentPane().add(rdbtnVendedor);

		rdbtnGerente = new JRadioButton("Gerente");
		rdbtnGerente.setFont(new Font("SansSerif", Font.BOLD, 15));
		rdbtnGerente.setBounds(230, 320, 110, 25);
		getContentPane().add(rdbtnGerente);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnGerente);
		buttonGroup.add(rdbtnVendedor);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCargo.setBounds(5, 320, 50, 25);
		getContentPane().add(lblCargo);

		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setIcon(new ImageIcon("images/icons8-voltar-30.png"));
		btnVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVoltar.setBounds(31, 380, 250, 35);
		getContentPane().add(btnVoltar);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setIcon(new ImageIcon("images/icons8-adicionar-usuário-masculino-32.png"));
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCadastrar.setBounds(301, 380, 250, 35);
		getContentPane().add(btnCadastrar);
		
		JLabel lblOpcional = new JLabel("OPCIONAL");
		lblOpcional.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblOpcional.setBounds(510, 210, 100, 25);
		getContentPane().add(lblOpcional);

		cadastrarOuEditar();
		addListener();
		setVisible(true);
	}

	public void addListener () {
		OuvinteFuncionario ouvinteFuncionario = new OuvinteFuncionario(this);
		this.btnVoltar.addActionListener(ouvinteFuncionario);
		this.btnCadastrar.addActionListener(ouvinteFuncionario);
		this.rdbtnGerente.addActionListener(ouvinteFuncionario);
		this.rdbtnVendedor.addActionListener(ouvinteFuncionario);
	}

	public String cargoSelecionado() {
		if (rdbtnGerente.isSelected()) {
			return "GERENTE";
		} else {
			return "VENDEDOR";
		}
	}

	public void cadastrarOuEditar () {
		if (funcionarioDTO != null) {
			btnCadastrar.setText("EDITAR");
			btnCadastrar.setIcon(new ImageIcon("images/icons8-editar-usuário-masculino-32.png"));
			btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldNome.setText(funcionarioDTO.getNome());
			textFieldSenha.setText(funcionarioDTO.getSenha());
			textFieldEmail.setText(funcionarioDTO.getEmail());
			textFieldTelefone.setText(funcionarioDTO.getTelefone());
			if (funcionarioDTO.getCargo().equals("GERENTE")) {
				rdbtnGerente.setSelected(true);
			} else {
				rdbtnVendedor.setSelected(true);
			}
		}
	}

	public boolean camposValidos() {
		if (textFieldNome.getText().equals("") || textFieldSenha.getText().equals("") || textFieldTelefone.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public class OuvinteFuncionario implements ActionListener {

		private ViewCadastroFuncionario viewCadastroFuncionario;

		public OuvinteFuncionario (ViewCadastroFuncionario viewCadastroFuncionario) {
			this.viewCadastroFuncionario = viewCadastroFuncionario;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();

			switch (button) {
			case "VOLTAR":
				viewCadastroFuncionario.dispose();
				break;

			case "CADASTRAR":

				if (camposValidos()) {
					try {
						FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
						funcionarioDTO.setNome(textFieldNome.getText());
						funcionarioDTO.setSenha(textFieldSenha.getText());
						funcionarioDTO.setEmail(textFieldEmail.getText());
						funcionarioDTO.setTelefone(textFieldTelefone.getText());
						funcionarioDTO.setCargo(cargoSelecionado());
						controllerGerente.cadastrarVendedor(funcionarioDTO);
						JOptionPane.showMessageDialog(viewCadastroFuncionario, "FUNCIONÁRIO CADASTRADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
						int confirm = JOptionPane.showConfirmDialog(viewCadastroFuncionario, "DESEJA CONTINUAR CADASTRANDO FUNCIONÁRIOS?");
						if (confirm == JOptionPane.YES_OPTION) {
							textFieldEmail.setText("");
							textFieldNome.setText("");
							textFieldSenha.setText("");
							textFieldTelefone.setText("");
						} else {
							viewCadastroFuncionario.dispose();
							viewGerente.dispose();
							new ViewGerente(viewGerente.vendedor);
						}
					} catch (FuncionarioDuplicadoException | PSQLException e2) {
						JOptionPane.showMessageDialog(viewCadastroFuncionario, "POR FAVOR INSIRA UM NOME DIFERENTE", "FUNCIONÁRIO JÁ EXISTE", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(viewCadastroFuncionario, "CAMPOS EM BRANCO", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				break;

			case "EDITAR":

				try {
					if (camposValidos()) {
						funcionarioDTO.setNome(textFieldNome.getText());
						funcionarioDTO.setSenha(textFieldSenha.getText());
						funcionarioDTO.setEmail(textFieldEmail.getText());
						funcionarioDTO.setTelefone(textFieldTelefone.getText());
						funcionarioDTO.setCargo(cargoSelecionado());
						controllerGerente.atualizarFuncionario(funcionarioDTO);
						JOptionPane.showMessageDialog(viewCadastroFuncionario, "FUNCIONÁRIO EDITADO COM SUCESSO", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
						viewCadastroFuncionario.dispose();
						viewGerente.dispose();
						new ViewGerente(viewGerente.vendedor);;
					} else {
						JOptionPane.showMessageDialog(viewCadastroFuncionario, "CAMPOS EM BRANCO");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(viewCadastroFuncionario, e2.getMessage());
				}
				break;
			}

		}
	}
}
