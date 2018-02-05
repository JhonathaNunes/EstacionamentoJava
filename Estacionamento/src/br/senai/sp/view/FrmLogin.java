package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.DAO.AutenticaUsuario;
import br.senai.sp.models.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/br/senai/sp/imagens/IconeApp.png")));
		setResizable(false);
		setTitle("Autentica\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsurio.setBounds(10, 52, 65, 14);
		contentPane.add(lblUsurio);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(66, 50, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setBounds(10, 86, 52, 14);
		contentPane.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(66, 83, 86, 20);
		contentPane.add(txtSenha);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 263, 39);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(0, 153, 255));
		lblLogin.setFont(new Font("Arial", Font.BOLD, 16));
		lblLogin.setBounds(0, 0, 73, 39);
		panel.add(lblLogin);

		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario user = new Usuario();
				AutenticaUsuario autent = new AutenticaUsuario();
				user = autent.validaUsuario(txtUsuario.getText(), txtSenha.getText());

				if (user.isAtivo()) {
					FrmMain frmMain = new FrmMain(user);
					frmMain.setVisible(true);
					frmMain.setExtendedState(MAXIMIZED_BOTH);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Usuario ou Senha incorretos!\n\n"
									+ "Se o erro persistir entre em contato com o ADMINISTRADOR do Sistema",
							"Falha na Autentica��o", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmLogin.class.getResource("/br/senai/sp/imagens/confirma.png")));
		btnSalvar.setBounds(124, 114, 42, 39);
		contentPane.add(btnSalvar);

		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirma��o",
						JOptionPane.YES_NO_OPTION);

				if (res == 0) {
					dispose();
				}
			}
		});
		btnSair.setIcon(new ImageIcon(FrmLogin.class.getResource("/br/senai/sp/imagens/sair.png")));
		btnSair.setBounds(190, 114, 42, 39);
		contentPane.add(btnSair);
	}
}