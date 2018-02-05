package br.senai.sp.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class FabricaConexao {

	private Connection con;

	public Connection abrirConexao() {
		con = null;

		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/estacionamento", "root", "bcd127");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}

	public void fecharConexao() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
