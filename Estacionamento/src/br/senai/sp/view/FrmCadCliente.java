package br.senai.sp.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import br.senai.sp.DAO.ClienteDAO;
import br.senai.sp.models.Cliente;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCadCliente extends JDialog {
	private JTextField txtNome;
	private JTextField txtTel;
	private JTextField txtLog;
	private JTextField txtNum;
	private JTextField txtBairro;
	private JTextField txtCity;
	private JTextField txtEstado;
	private JTextField txtCEP;
	/**
	 * Create the dialog.
	 */
	public FrmCadCliente(Cliente client) {
		
		setModal(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 408, 287);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 392, 41);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastroDeCliente.setForeground(new Color(0, 153, 255));
		lblCadastroDeCliente.setBounds(10, 11, 221, 25);
		panel.add(lblCadastroDeCliente);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 52, 46, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 70, 247, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(269, 52, 46, 14);
		getContentPane().add(lblTelefone);
		
		txtTel = new JTextField();
		txtTel.setBounds(267, 70, 115, 20);
		getContentPane().add(txtTel);
		txtTel.setColumns(10);
		txtTel.requestFocus();
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(10, 101, 105, 14);
		getContentPane().add(lblLogradouro);
		
		txtLog = new JTextField();
		txtLog.setBounds(10, 121, 196, 20);
		getContentPane().add(txtLog);
		txtLog.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(211, 101, 46, 14);
		getContentPane().add(lblNmero);
		
		txtNum = new JTextField();
		txtNum.setBounds(211, 121, 46, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 152, 46, 14);
		getContentPane().add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(10, 172, 196, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(211, 152, 46, 14);
		getContentPane().add(lblCidade);
		
		txtCity = new JTextField();
		txtCity.setBounds(211, 172, 115, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(336, 159, 46, 14);
		getContentPane().add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(336, 172, 46, 20);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(269, 101, 46, 14);
		getContentPane().add(lblCep);
		
		txtCEP = new JTextField();
		txtCEP.setBounds(269, 121, 115, 20);
		getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarCliente();
				dispose();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmCadCliente.class.getResource("/br/senai/sp/imagens/salvar.png")));
		btnSalvar.setBounds(323, 203, 59, 36);
		getContentPane().add(btnSalvar);
		
		preencheNome(client);
		
		
	}
	
	public void cadastrarCliente(){
		Cliente cliente = new Cliente();
		
		cliente.setNome(txtNome.getText());
		cliente.setTelefone(txtTel.getText());
		cliente.setLogradouro(txtLog.getText());
		cliente.setNumero(Integer.parseInt(txtNum.getText()));
		cliente.setCep(txtCEP.getText());
		cliente.setBairro(txtBairro.getText());
		cliente.setCidade(txtCity.getText());
		cliente.setEstado(txtEstado.getText());
		
		ClienteDAO cDao = new ClienteDAO();
		
		if (cDao.cadastrarCliente(cliente) == false) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso!!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void preencheNome(Cliente cliente){
		txtNome.setText(cliente.getNome());
	}
}
