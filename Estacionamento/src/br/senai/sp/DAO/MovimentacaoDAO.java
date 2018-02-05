package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.fabric.FabricCommunicationException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.Movimentacao;
import br.senai.sp.models.TipoCobranca;
import br.senai.sp.models.Veiculo;
import br.senai.sp.utils.DataHora;
import br.senai.sp.utils.FabricaConexao;

public class MovimentacaoDAO {

	public boolean entradaVeiculo(Veiculo veic, TipoCobranca tpCobr){
		boolean entra = false;
		
		String sql = "INSERT INTO tbl_movimentacao (placa, dataEntrada, horaEntrada, idCobranca) VALUES (?,?,?,?);";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, veic.getPlaca());
			stm.setDate(2, DataHora.dataParaMysql());
			stm.setString(3, DataHora.horaAtual());
			stm.setInt(4, tpCobr.getIdCobranca());
			
			stm.execute();
			
			entra = true;
			fab.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entra;
	}
	
	public int procuraVeicIn(Movimentacao mov){
		String sql = "SELECT idMovimentacao FROM tbl_movimentacao WHERE placa = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, mov.getPlaca());
			
			rs = stm.executeQuery();
			
			while(rs.next()){
				mov.setIdMovimentacao(rs.getInt("idMovimentacao"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mov.getIdMovimentacao();
	}
	
}
