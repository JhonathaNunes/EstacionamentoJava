package br.senai.sp.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import br.senai.sp.DAO.CaixaDAO;
import br.senai.sp.models.Caixa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAbrirCaixa extends JDialog {
	private JTextField txtDindin;

	/**
	 * Create the dialog.
	 */
	public FrmAbrirCaixa() {
		setModal(true);
		setBounds(100, 100, 288, 224);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 323, 46);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAbrirCaixa = new JLabel("Abrir Caixa");
		lblAbrirCaixa.setForeground(new Color(0, 153, 255));
		lblAbrirCaixa.setFont(new Font("Arial", Font.BOLD, 15));
		lblAbrirCaixa.setBounds(10, 11, 110, 24);
		panel.add(lblAbrirCaixa);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 57, 64, 71);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(FrmAbrirCaixa.class.getResource("/br/senai/sp/imagens/cash-register.png")));
		
		JLabel lblNewLabel = new JLabel("Dinheiro:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(81, 74, 64, 14);
		getContentPane().add(lblNewLabel);
		
		txtDindin = new JTextField();
		txtDindin.setBounds(101, 90, 71, 20);
		getContentPane().add(txtDindin);
		txtDindin.setColumns(10);
		
		JLabel lblR = new JLabel("R$");
		lblR.setFont(new Font("Arial", Font.PLAIN, 12));
		lblR.setBounds(81, 90, 22, 20);
		getContentPane().add(lblR);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmAbrirCaixa.class.getResource("/br/senai/sp/imagens/salvar.png")));
		btnSalvar.setBounds(211, 128, 51, 46);
		getContentPane().add(btnSalvar);

	}
	
	public void abrirCaixa(){
		Caixa caixa = new Caixa();
		CaixaDAO dao = new CaixaDAO();
		caixa.setValorAbertura(Double.parseDouble(txtDindin.getText()));
		
		if (dao.abrirCaixa(caixa) == true){
				JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!!", "Caixa Aberto!!",
						JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Erro na tentativa de abrir caixa!!",
						"Caixa n�o aberto", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
