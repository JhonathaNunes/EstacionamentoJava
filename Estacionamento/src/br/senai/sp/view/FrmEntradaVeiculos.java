package br.senai.sp.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import br.senai.sp.DAO.ClienteDAO;
import br.senai.sp.DAO.MovimentacaoDAO;
import br.senai.sp.DAO.TipoCobrancaDAO;
import br.senai.sp.DAO.TipoVeiculoDAO;
import br.senai.sp.DAO.VeiculoDAO;
import br.senai.sp.models.Cliente;
import br.senai.sp.models.Movimentacao;
import br.senai.sp.models.TipoCobranca;
import br.senai.sp.models.TipoVeiculo;
import br.senai.sp.models.Veiculo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmEntradaVeiculos extends JInternalFrame {
	private JTextField txtPlaca;
	private JTextField txtModelo;
	private JTextField txtDono;
	private JTextField txtCor;
	private JComboBox cbTipo;
	private JComboBox cbCobranca;
	private boolean veicExiste;
	private boolean clienteExiste;

	/**
	 * Create the frame.
	 */
	public FrmEntradaVeiculos() {
		setClosable(true);
		setTitle("Entrada de Ve\u00EDculos");
		setFrameIcon(new ImageIcon(FrmEntradaVeiculos.class.getResource("/br/senai/sp/imagens/IconeApp.png")));
		setBounds(100, 100, 290, 335);
		getContentPane().setLayout(null);

		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(10, 45, 46, 14);
		getContentPane().add(lblPlaca);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 274, 34);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblEntradaDeVeculos = new JLabel("Entrada de Ve\u00EDculos");
		lblEntradaDeVeculos.setForeground(new Color(0, 153, 255));
		lblEntradaDeVeculos.setFont(new Font("Arial", Font.BOLD, 14));
		lblEntradaDeVeculos.setBounds(10, 0, 162, 34);
		panel.add(lblEntradaDeVeculos);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(52, 43, 123, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);

		JLabel lblOp = new JLabel("Cobran\u00E7a:");
		lblOp.setBounds(10, 88, 71, 14);
		getContentPane().add(lblOp);

		cbCobranca = new JComboBox();
		cbCobranca.setBounds(10, 105, 86, 20);
		getContentPane().add(cbCobranca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(106, 88, 46, 14);
		getContentPane().add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(106, 105, 158, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 140, 46, 14);
		getContentPane().add(lblTipo);

		cbTipo = new JComboBox();
		cbTipo.setBounds(10, 154, 86, 20);
		getContentPane().add(cbTipo);

		JLabel lblDono = new JLabel("Dono:");
		lblDono.setBounds(10, 202, 46, 14);
		getContentPane().add(lblDono);

		txtDono = new JTextField();
		txtDono.setBounds(10, 215, 254, 20);
		getContentPane().add(txtDono);
		txtDono.setColumns(10);

		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(106, 140, 46, 14);
		getContentPane().add(lblCor);

		txtCor = new JTextField();
		txtCor.setBounds(106, 154, 158, 20);
		getContentPane().add(txtCor);
		txtCor.setColumns(10);

		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscaCliente();

				if (veicExiste == false && ! txtModelo.getText().equals("") && ! txtPlaca.getText().equals("")) {
					cadastrarVeiculo();
				}

				entradaVeiculo();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmEntradaVeiculos.class.getResource("/br/senai/sp/imagens/salvar.png")));
		btnSalvar.setBounds(213, 264, 51, 34);
		getContentPane().add(btnSalvar);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscaVeiculo();
				if (txtModelo.getText().equals("")) {
					veicExiste = false;
				} else {
					veicExiste = true;
				}
			}
		});
		btnProcurar.setBounds(185, 42, 79, 23);
		getContentPane().add(btnProcurar);

		colocaTipoVeic();
		colocarTipoCobranca();
	}

	public void colocaTipoVeic() {
		TipoVeiculoDAO veicDAO = new TipoVeiculoDAO();
		veicDAO.mostrarTiposVeiculo(cbTipo);
	}

	public void cadastrarVeiculo() {
		Veiculo veic = new Veiculo();
		Cliente client = new Cliente();
		setIDCliente(client);
		
		veic.setIdCliente(client.getIdCliente());
		veic.setCor(txtCor.getText());
		veic.setModelo(txtModelo.getText());
		veic.setPlaca(txtPlaca.getText());

		setIdTipo(veic);

		VeiculoDAO veicDAO = new VeiculoDAO();

		if (veicDAO.cadVeiculo(veic) == false) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar veiculo", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso", "Sucesso!!",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void setIdTipo(Veiculo veic) {
		TipoVeiculo tpVeic = new TipoVeiculo();

		tpVeic.setDescricao((String) cbTipo.getSelectedItem());

		TipoVeiculoDAO tpDAO = new TipoVeiculoDAO();
		tpDAO.idTipoVeiculo(tpVeic);

		veic.setIdTipoVeiculo(tpVeic.getIdTipoVeiculo());
	}

	public void colocarTipoCobranca() {
		TipoCobrancaDAO tpCobrDAO = new TipoCobrancaDAO();
		tpCobrDAO.mostrarTiposCobr(cbCobranca);
	}

	public void buscaVeiculo() {
		Veiculo veic = new Veiculo();
		veic.setPlaca(txtPlaca.getText());

		VeiculoDAO vDAO = new VeiculoDAO();

		vDAO.procuraVeiculo(veic);

		Cliente clie = new Cliente();
		clie.setIdCliente(veic.getIdCliente());
		ClienteDAO.selecionaCliente(clie);

		txtModelo.setText(veic.getModelo());
		txtCor.setText(veic.getCor());
		cbTipo.setSelectedIndex(veic.getIdTipoVeiculo() - 1);
		txtDono.setText(clie.getNome());

	}
	
	public TipoCobranca setIdCobranca(TipoCobranca tpCobr) {

		tpCobr.setDescricao((String) cbCobranca.getSelectedItem());

		TipoCobrancaDAO tpDAO = new TipoCobrancaDAO();
		tpDAO.setIdCobranca(tpCobr);
		
		return tpCobr;
	}

	public void entradaVeiculo(){
		Veiculo veic = new Veiculo();
		
		veic.setCor(txtCor.getText());
		veic.setModelo(txtModelo.getText());
		veic.setPlaca(txtPlaca.getText());
		
		TipoCobranca tpCobr = new TipoCobranca();
		setIdCobranca(tpCobr);
		MovimentacaoDAO movDAO = new MovimentacaoDAO();
		
		Movimentacao mov = new Movimentacao();
		mov.setPlaca(veic.getPlaca());
		
		if (movDAO.procuraVeicIn(mov) == 0){
			if (movDAO.entradaVeiculo(veic, tpCobr) == false){
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar entrada", "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Entrada cadastrada com sucesso", "Sucesso!!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "O ve�culo j� esta no estacionamento!!", "Aten��o", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void abrirCadUsuario(Cliente client){
		FrmCadCliente frmCliente = new FrmCadCliente(client);
		frmCliente.setLocationRelativeTo(this);
		frmCliente.setVisible(true);
	}
	
	public void buscaCliente(){
		Cliente client = new Cliente();
		client.setNome(txtDono.getText());
		
		ClienteDAO.confereCliente(client);
		
		if (client.getIdCliente() == 0){
			int res = JOptionPane.showConfirmDialog(null,
					"Cliente n�o cadastrado no sistema,\n\n Deseja cadastra-lo?", "Cliente n�o Cadastrado",
					JOptionPane.YES_NO_OPTION);
			if (res == 0) {
				abrirCadUsuario(client);
			}
		}
	}
	
	public void setIDCliente(Cliente client){
		client.setNome(txtDono.getText());
		ClienteDAO.confereCliente(client);
	}

}
