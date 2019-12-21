package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.ControllerGerente;
import DTO.FuncionarioDTO;

public class ViewInicial extends ViewPadrao {

	private JButton btnEntrar, btnSair;
	private JPasswordField passwordFieldSenha;
	private JTextField textFieldLogin;

	public ViewInicial() {
		super();
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("A Boutique");

		ImageIcon logoLoja = new ImageIcon("images/A3BB1CBD-0992-4041-B1A1-1E085460F0D0.png");
		this.setIconImage(logoLoja.getImage());
		JLabel logo = new JLabel(logoLoja);
		logo.setBounds(175, 29, 250, 200);
		getContentPane().add(logo);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLogin.setBounds(155, 240, 50, 25);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSenha.setBounds(155, 290, 50, 25);
		getContentPane().add(lblSenha);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(205, 240, 200, 30);
		getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(205, 290, 200, 30);
		getContentPane().add(passwordFieldSenha);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setIcon(new ImageIcon("images/icons8-logon-arredondado-para-baixo-32.png"));
		btnEntrar.setHorizontalAlignment(SwingConstants.LEFT);
		//btnEntrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnEntrar.setBounds(50, 340, 250, 35);
		getContentPane().add(btnEntrar);

		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("images/icons8-logout-arredondado-acima-32.png"));
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);
		//btnSair.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSair.setBackground(Color.WHITE);
		btnSair.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSair.setBounds(300, 340, 250, 35);
		getContentPane().add(btnSair);

		/*btnAjuda = new JButton("Precisa De Ajuda?");
		btnAjuda.setForeground(Color.BLACK);
		btnAjuda.setBackground(Color.WHITE);
		btnAjuda.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAjuda.setBounds(180, 375, 250, 30);
		getContentPane().add(btnAjuda);*/

		this.addListener();

		setVisible(true);
	}

	public String senhaFuncionario() {
		return passwordFieldSenha.getText();
	}

	public String loginFuncionario () {
		return textFieldLogin.getText();
	}

	public void addListener () {
		OuvinteViewInicial ouvinteViewInicial = new OuvinteViewInicial(this);
		//btnAjuda.addActionListener(ouvinteViewInicial);
		btnEntrar.addActionListener(ouvinteViewInicial);
		btnSair.addActionListener(ouvinteViewInicial);
	}

	public class OuvinteViewInicial implements ActionListener {

		private ViewInicial viewInicial;

		public OuvinteViewInicial (ViewInicial viewInicial) {
			this.viewInicial = viewInicial;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String button = e.getActionCommand();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			ControllerGerente controllerGerente = new ControllerGerente();

			switch (button) {
			case "Entrar":
				funcionarioDTO.setSenha(senhaFuncionario());
				funcionarioDTO.setNome(loginFuncionario());

				try {
					funcionarioDTO = controllerGerente.recuperarFuncionario(funcionarioDTO);
					if (loginFuncionario().equals("admin") && senhaFuncionario().equals("admin")) {
						viewInicial.dispose();
						new ViewGerente(loginFuncionario());

					} else {
						if (funcionarioDTO.getSenha().equals(senhaFuncionario())) {
							FabricaFuncionarios funcionarios = new FabricaFuncionarios();
							funcionarios.createMethod(funcionarioDTO.getCargo());
							FuncionarioCargo funcionarioCargo  = funcionarios.cargo();

							if (funcionarioCargo instanceof CargoGerente) {
								viewInicial.dispose();
								new ViewGerente(loginFuncionario());
							} else if (funcionarioCargo instanceof CargoVendedor) {
								viewInicial.dispose();
								new ViewVendedor(loginFuncionario(), null);
							}
							
						} else {
							JOptionPane.showMessageDialog(viewInicial, "SENHA INVÁLIDA", "ERRO", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (FuncionarioNaoExisteException e2) {
					JOptionPane.showMessageDialog(viewInicial, e2.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}

				break;

			case "Sair":
				viewInicial.dispose();
				controllerGerente.fecharConexao();
				//FECHAR CONEXÃO
				break;
			}
		}
	}

	public static void main(String[] args) {
		new ViewInicial();
	}

}
