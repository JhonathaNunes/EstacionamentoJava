package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.Veiculo;
import br.senai.sp.utils.FabricaConexao;

public class VeiculoDAO {

	public boolean cadVeiculo(Veiculo veic){
		 boolean cadastrado;
		 
		 String sql = "INSERT INTO tbl_veiculo (placa, cor, modelo, ano, idCliente, idTipoVeiculo) VALUES (?, ?, ?, ?, ?, ?);";
		 
		 FabricaConexao fab = new FabricaConexao();
		 Connection con = fab.abrirConexao();
		 
		 try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, veic.getPlaca());
			stm.setString(2, veic.getCor());
			stm.setString(3, veic.getModelo());
			stm.setString(4, veic.getAno());
			stm.setInt(5, veic.getIdCliente());
			stm.setInt(6, veic.getIdTipoVeiculo());
			
			stm.execute();
			fab.fecharConexao();
			
			cadastrado = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cadastrado = false;
		}
		 
		 return cadastrado;
	}
	
	public Veiculo procuraVeiculo(Veiculo veic){
		String sql = "SELECT modelo, cor, idTipoVeiculo, idCliente, ano FROM tbl_veiculo WHERE placa = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setString(1, veic.getPlaca());
			
			rs = stm.executeQuery();
			
			while(rs.next()){
				veic.setAno(rs.getString("ano"));
				veic.setModelo(rs.getString("modelo"));
				veic.setCor(rs.getString("cor"));
				veic.setIdTipoVeiculo(rs.getInt("idTipoVeiculo"));
				veic.setIdCliente(rs.getInt("idCliente"));

			}
			
			fab.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return veic;
	}
	
}
