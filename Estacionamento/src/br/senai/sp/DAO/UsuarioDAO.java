package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.Usuario;
import br.senai.sp.utils.FabricaConexao;

public class UsuarioDAO {

	public boolean cadUsuario(Usuario user) {
		boolean cadastrado;

		String sql = "INSERT INTO tbl_usuario (nome, telefone, usuario, senha, privilegio, ativo) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";

		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);

			stm.setString(1, user.getNome());
			stm.setString(2, user.getTelefone());
			stm.setString(3, user.getUsuario());
			stm.setString(4, user.getSenha());
			stm.setString(5, user.getPrivilegio());
			stm.setBoolean(6, user.isAtivo());

			stm.execute();
			
			fab.fecharConexao();
			cadastrado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			cadastrado = false;
		}

		return cadastrado;

	}
	
	public boolean editUsuario(Usuario user){
		boolean editado;
		
		String sql = "UPDATE tbl_usuario SET nome = ?, telefone = ?, usuario = ?, senha = ?, privilegio = ?, ativo=? WHERE idUsuario = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, user.getNome());
			stm.setString(2, user.getTelefone());
			stm.setString(3, user.getUsuario());
			stm.setString(4, user.getSenha());
			stm.setString(5, user.getPrivilegio());
			stm.setBoolean(6, user.isAtivo());
			stm.setInt(7, user.getIdUsuario());
			stm.execute();
			
			fab.fecharConexao();
			editado = true;
			
		} catch (SQLException e) {
			editado = false;
			e.printStackTrace();
		}
		
		return editado;
		
	}
	
	public boolean delUsuario(Usuario user){
		boolean apagado;
		
		String sql = "DELETE FROM tbl_usuario WHERE idUsuario = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setInt(1, user.getIdUsuario());
			stm.execute();
			fab.fecharConexao();
			apagado = true;
		} catch (SQLException e) {
			apagado = false;
			e.printStackTrace();
		}
		
		
		return apagado;
	}
	
	public Usuario detalhesUsuario(Usuario user){
		
		String sql = "SELECT nome, telefone, usuario, senha, privilegio, ativo FROM tbl_usuario WHERE idUsuario = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setInt(1, user.getIdUsuario()); ;
			rs = stm.executeQuery();
			
			while (rs.next()){
				user.setNome(rs.getString("nome"));
				user.setTelefone(rs.getString("telefone"));
				user.setUsuario(rs.getString("usuario"));
				user.setSenha(rs.getString("senha"));
				user.setPrivilegio(rs.getString("privilegio"));
				user.setAtivo(rs.getBoolean("ativo"));
				
			}
			fab.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
		
	}

	public ArrayList<Usuario> criarTblUsuario() {

		ArrayList<Usuario> user = new ArrayList<>();

		String sql = "SELECT idUsuario, nome, telefone, usuario, privilegio FROM tbl_usuario;";

		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		ResultSet rs;

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);

			rs = stm.executeQuery();

			while (rs.next()) {
				Usuario usr = new Usuario();
				
				usr.setIdUsuario(rs.getInt("idUsuario"));
				usr.setNome(rs.getString("nome"));
				usr.setTelefone(rs.getString("telefone"));
				usr.setUsuario(rs.getString("usuario"));
				usr.setPrivilegio(rs.getString("privilegio"));

				user.add(usr);

			}

			fab.fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
}