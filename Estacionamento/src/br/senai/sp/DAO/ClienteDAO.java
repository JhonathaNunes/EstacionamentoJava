package br.senai.sp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.senai.sp.models.Cliente;
import br.senai.sp.models.Usuario;
import br.senai.sp.utils.FabricaConexao;

public class ClienteDAO {

	public boolean cadastrarCliente(Cliente cliente) {
		boolean cadastrado;

		String sql = "INSERT INTO tbl_cliente (nome, telefone, logradouro, numero, bairro, "
				+ "cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getTelefone());
			stm.setString(3, cliente.getLogradouro());
			stm.setInt(4, cliente.getNumero());
			stm.setString(5, cliente.getBairro());
			stm.setString(6, cliente.getCidade());
			stm.setString(7, cliente.getEstado());
			stm.setString(8, cliente.getCep());

			stm.execute();
			fab.fecharConexao();

			cadastrado = true;
		} catch (SQLException e) {
			e.printStackTrace();

			cadastrado = false;
		}

		return cadastrado;
	}

	public static Cliente selecionaCliente(Cliente cliente) {

		String sql = "SELECT nome FROM tbl_cliente WHERE idCliente = ?;";

		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		ResultSet rs;

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setInt(1, cliente.getIdCliente());
			
			rs = stm.executeQuery();
			
			while (rs.next()) {
				cliente.setNome(rs.getString("nome"));
			}
			fab.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public static Cliente confereCliente(Cliente cliente){
		
		String sql = "SELECT idcliente FROM tbl_cliente WHERE nome = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		ResultSet rs;

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			
			rs = stm.executeQuery();
			
			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idcliente"));
			}
			fab.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public boolean editCliente(Cliente client){
		boolean editado;
		
		String sql = "UPDATE tbl_cliente SET nome = ?, telefone = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE idCliente = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			
			stm.setString(1, client.getNome());
			stm.setString(2, client.getTelefone());
			stm.setString(3, client.getLogradouro());
			stm.setInt(4, client.getNumero());
			stm.setString(5, client.getBairro());
			stm.setString(6, client.getCidade());
			stm.setString(7, client.getEstado());
			stm.setString(8, client.getCep());
			stm.setInt(9, client.getIdCliente());

			stm.execute();
			
			fab.fecharConexao();
			editado = true;
			
		} catch (SQLException e) {
			editado = false;
			e.printStackTrace();
		}
		
		return editado;
		
	}
	
	public boolean delCliente(Cliente client){
		boolean apagado;
		
		String sql = "DELETE FROM tbl_cliente WHERE idcliente = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setInt(1, client.getIdCliente());
			stm.execute();
			fab.fecharConexao();
			apagado = true;
		} catch (SQLException e) {
			apagado = false;
			e.printStackTrace();
		}
		
		
		return apagado;
	}
	
	public Cliente detalhesCliente(Cliente client){
		
		String sql = "SELECT nome, telefone, logradouro, numero, bairro, cidade, estado, cep FROM tbl_cliente WHERE idcliente = ?;";
		
		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();
		
		ResultSet rs;
		
		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setInt(1, client.getIdCliente()); ;
			rs = stm.executeQuery();
			
			while (rs.next()){
				client.setNome(rs.getString("nome"));
				client.setTelefone(rs.getString("telefone"));
				client.setLogradouro(rs.getString("logradouro"));
				client.setNumero(rs.getInt("numero"));
				client.setBairro(rs.getString("bairro"));
				client.setCidade(rs.getString("cidade"));
				client.setEstado(rs.getString("estado"));
				client.setCep(rs.getString("cep"));
				
			}
			fab.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
		
		
	}

	public ArrayList<Cliente> criarTblCliente() {

		ArrayList<Cliente> client = new ArrayList<>();

		String sql = "SELECT idcliente, nome, telefone, logradouro, numero, bairro, cidade, estado, cep FROM tbl_cliente;";

		FabricaConexao fab = new FabricaConexao();
		Connection con = fab.abrirConexao();

		ResultSet rs;

		try {
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);

			rs = stm.executeQuery();

			while (rs.next()) {
				Cliente clnt = new Cliente();
				
				clnt.setIdCliente(rs.getInt("idUsuario"));
				clnt.setNome(rs.getString("nome"));
				clnt.setTelefone(rs.getString("telefone"));

				client.add(clnt);

			}

			fab.fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}
}
