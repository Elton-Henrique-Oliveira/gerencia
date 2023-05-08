package br.elton.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class conexao_banco {
	
	public Connection conectaBD() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://45.152.44.204/u400453406_Gerencia?user=u400453406_banco_gerencia&password=97v:XE/jP";
			conn = DriverManager.getConnection(url);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}
		return conn;
	}
	
}
