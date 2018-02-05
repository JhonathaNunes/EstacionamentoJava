package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.DAO.CaixaDAO;
import br.senai.sp.models.Caixa;
import br.senai.sp.models.Usuario;
import br.senai.sp.utils.DataHora;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmMain extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JMenu mnPreos;
	private JTable table;
	private JDesktopPane desktopPane;
	/**
	 * Create the frame.
	 */
	public FrmMain(Usuario user) {
		
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/br/senai/sp/imagens/IconeApp.png")));
		setTitle("Gerenciador de Estacionamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 571);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("");
		menuBar.add(menu);

		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(FrmMain.class.getResource("/br/senai/sp/imagens/exit.png")));
		mnSistema.add(mntmSair);

		mntmSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION);

				if (res == 0) {
					dispose();
				}

			}
		});

		JMenu mnCaixa = new JMenu("Caixa");
		menuBar.add(mnCaixa);

		JMenuItem mntmFecharCaixa = new JMenuItem("Fechar Caixa");
		mntmFecharCaixa.setIcon(new ImageIcon(FrmMain.class.getResource("/br/senai/sp/imagens/fechar_caixa.png")));
		mnCaixa.add(mntmFecharCaixa);

		JMenu mnEstacionamento = new JMenu("Estacionamento");
		menuBar.add(mnEstacionamento);

		JMenuItem mntmEntradaDeVeculos = new JMenuItem("Entrada de Ve\u00EDculos");
		mntmEntradaDeVeculos
				.setIcon(new ImageIcon(FrmMain.class.getResource("/br/senai/sp/imagens/carro_entrando.png")));
		mnEstacionamento.add(mntmEntradaDeVeculos);
		mntmEntradaDeVeculos.setToolTipText("F11");
		
		
		mntmEntradaDeVeculos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				abrirEntradaVeiculos();
				
			}
		});

		JMenuItem mntmSadaDeVeculos = new JMenuItem("Sa\u00EDda de Ve\u00EDculos");
		mntmSadaDeVeculos.setIcon(new ImageIcon(FrmMain.class.getResource("/br/senai/sp/imagens/carro_saindo.png")));
		mnEstacionamento.add(mntmSadaDeVeculos);
		mntmEntradaDeVeculos.setToolTipText("F12");
		
		JMenu mnUsuario = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsuario);
		
		if (user.getPrivilegio().equals("O")){
			mnUsuario.setEnabled(false);
		}else{
			mnUsuario.setEnabled(true);
		}
		
		JMenuItem mntmCadastroUsurios = new JMenuItem("Cadastro Usu\u00E1rios");
		mnUsuario.add(mntmCadastroUsurios);

		mntmCadastroUsurios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				abrirCadUsuario();
				
			}
		});
		
		mnPreos = new JMenu("Pre\u00E7os");
		menuBar.add(mnPreos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		if (user.getPrivilegio().equals("A")) {
			mnPreos.setEnabled(true);
		} else {
			mnPreos.setEnabled(false);
		}

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBorder(new TitledBorder(null, "Ve\u00EDculos no Estacionamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
							.addGap(4)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(15))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Veiculo", "Hora Entrada", "Data Entrada", "Dono", "Tempo"
			}
		));
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsurio.setForeground(Color.WHITE);

		JLabel lblJwcorp = new JLabel("JWCorp\u00A9");
		lblJwcorp.setForeground(Color.WHITE);
		lblJwcorp.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNome = new JLabel(user.getUsuario());
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblUsurio, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 603, Short.MAX_VALUE)
						.addComponent(lblJwcorp, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsurio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblJwcorp, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		addKeyListener(this);
	}
	
	void abrirEntradaVeiculos(){
		FrmEntradaVeiculos enterVeicu = new FrmEntradaVeiculos();
		enterVeicu.setVisible(true);
		desktopPane.add(enterVeicu);
	}
	
	void abrirCadUsuario(){
		FrmUsuario frmUsr = new FrmUsuario();
		frmUsr.setVisible(true);
		desktopPane.add(frmUsr);
	}

	@Override
	public void keyPressed(KeyEvent tecla) {
		System.out.println(tecla.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent tecla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent tecla) {
		// TODO Auto-generated method stub
		
	}
	
	private void abrirCaixa(Usuario user){
		CaixaDAO cxDAO = new CaixaDAO();
		
		
		
		FrmAbrirCaixa frmCx = new FrmAbrirCaixa();
		frmCx.setVisible(true);
	}

}
