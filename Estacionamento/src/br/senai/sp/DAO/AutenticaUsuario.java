package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.Usuario;
import br.senai.sp.utils.FabricaConexao;

public class AutenticaUsuario {

	
	//AUTENTICAR USUARIO
	public Usuario validaUsuario(String usuario, String senha){
		
		Usuario user = new Usuario();
		
		String sql = "SELECT * FROM tbl_usuario WHERE usuario = ? AND senha = ?;";
		
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setString(1, usuario);
			stm.setString(2, senha);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setNome(rs.getString("nome"));
				user.setUsuario(rs.getString("usuario"));
				user.setSenha(rs.getString("senha"));
				user.setTelefone(rs.getString("telefone"));
				user.setPrivilegio(rs.getString("privilegio"));
				user.setAtivo(rs.getBoolean("ativo"));
			}
			fab.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
}
