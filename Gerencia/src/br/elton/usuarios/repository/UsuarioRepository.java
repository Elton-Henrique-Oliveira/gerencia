package br.elton.usuarios.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.elton.conexao.conexao_banco;
import br.elton.usuarios.entities.UsuarioEntity;

public class UsuarioRepository {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;

	private String sql = "";

	public ArrayList<UsuarioEntity> consultaUsuario(int pIndicador, int pEmpresa, String pUsuario, String pSenha) {

		// pIndicador == 1 :> consulta por usuario/senha
		// pIndicador != 1 :> consulta todos usuarios cadastrados
		// retorna uma lista com os usuarios encontrados no banco

		ArrayList<UsuarioEntity> lista_retorno = new ArrayList<>();

		sql = "select *";
		sql += " from dbusuarios";
		if (pIndicador == 1) {
			sql += " where empresa = " + pEmpresa + "   and usuario_login = '" + pUsuario + "'"
					+ "   and usuario_senha = '" + pSenha + "';";
		}

		conn = new conexao_banco().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next() == false) {

				UsuarioEntity objetoUsuario = new UsuarioEntity();
				if (pIndicador == 1) {
					objetoUsuario.setMsg_erro("Usuario/senha incorretas!!");
				}
				if (pIndicador != 1) {
					objetoUsuario.setMsg_erro("Nenhum usuário encontrado!!");
				}

				lista_retorno.add(objetoUsuario);
			}
			rs.beforeFirst();

			while (rs.next()) {

				UsuarioEntity objetoUsuario = new UsuarioEntity();
				objetoUsuario.setCodigo_usuario(rs.getInt("codigo_usuario"));
				objetoUsuario.setData_nascimento(manipulaData(rs.getInt("data_nascimento")));
				objetoUsuario.setEmail(rs.getString("email"));
				objetoUsuario.setEmpresa(rs.getInt("empresa"));
				objetoUsuario.setGrupo(rs.getInt("grupo"));
				objetoUsuario.setNome_usuario(rs.getString("nome_usuario"));
				objetoUsuario.setSexo(rs.getInt("sexo"));
				objetoUsuario.setTelefone(rs.getString("telefone"));
				objetoUsuario.setUsuario_login(rs.getString("usuario_login"));
				objetoUsuario.setUsuario_senha(rs.getString("usuario_senha"));
				objetoUsuario.setNivel(rs.getInt("tipo_nivel"));
				objetoUsuario.setMsg_erro("");
				lista_retorno.add(objetoUsuario);

			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca_usuarios" + error);
			System.out.println(sql + "\nbusca_usuarios" + error);
		}
		
		return lista_retorno;
	}

	public void alteraSenhaUsuario(int pEmpresa, UsuarioEntity pUsuario, String pSenhaAlterada) {

		sql = "update dbusuarios";
		sql += " set ";
		sql += "	usuario_senha = ?";
		sql += " where ";
		sql += " 	empresa = ? and";
		sql += " 	codigo_usuario = ?;";

		conn = new conexao_banco().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pSenhaAlterada);
			pstm.setInt(2, pEmpresa);
			pstm.setInt(3, pUsuario.getCodigo_usuario());
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "ALTERAÇÃO SENHA USUARIO" + error);
		}
	}

	private String manipulaData(int pData) {
		String data_retorno = "";
		String number = String.valueOf(pData);
		int contador = 0;

		char[] digits1 = number.toCharArray();

		for (int i = 0; i < digits1.length; i++) {
			contador++;
			data_retorno += digits1[i];
			if (contador == 2 || contador == 4) {
				data_retorno += "/";
			}
		}

		return data_retorno;
	}
}
