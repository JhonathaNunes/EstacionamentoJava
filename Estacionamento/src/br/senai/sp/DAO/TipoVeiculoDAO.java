package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.TipoVeiculo;
import br.senai.sp.utils.FabricaConexao;

public class TipoVeiculoDAO {

	public void  mostrarTiposVeiculo(JComboBox cmbBoxTipoVeiculo){
		
		String sql = "SELECT descricao FROM tbl_tipoveiculo;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		
		cmbBoxTipoVeiculo.removeAllItems();  // remove todos os itens do combom
        DefaultComboBoxModel comboBoxMode = (DefaultComboBoxModel) cmbBoxTipoVeiculo.getModel();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while(rs.next()){
				String desc = rs.getString("descricao");
				cmbBoxTipoVeiculo.addItem(desc);
			}
			fab.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TipoVeiculo idTipoVeiculo(TipoVeiculo tpVeic){
		
		String sql = "SELECT idTipoVeiculo FROM tbl_tipoveiculo WHERE descricao = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, tpVeic.getDescricao());
			rs = stm.executeQuery();
			
			while(rs.next()){
				tpVeic.setIdTipoVeiculo(rs.getInt("idTipoVeiculo")); 
			}
			
			fab.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tpVeic;
	}
	
	
}