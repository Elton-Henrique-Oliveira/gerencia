package br.elton.usuarios.usercases;

import java.util.ArrayList;

import br.elton.usuarios.entities.*;
import br.elton.usuarios.repository.UsuarioRepository;

public class UsuarioUserCase {

	private UsuarioEntity usuarioEntity = new UsuarioEntity();
	private ArrayList<UsuarioEntity> listaUsuarios = new ArrayList<>();
	
	public UsuarioEntity verificaLogin(int pEmpresa, String pUsuarioLogin, String pUsuarioSenha) {

		String msg_erro = "";
		
		msg_erro = verificaUsuarioSenhaPreenchida(pUsuarioLogin, pUsuarioSenha);
		
		if(msg_erro.trim().equals("") == true) {
			
			listaUsuarios = verificaUsuarioSenhaBanco(pEmpresa, pUsuarioLogin, pUsuarioSenha);
			
			return listaUsuarios.get(0);
			
		}else {
			
			usuarioEntity.setMsg_erro(msg_erro);

			return usuarioEntity;
		}
	}
	
	public UsuarioEntity alterarSenha(int pEmpresa, UsuarioEntity pUsuario ,String pSenhaAlterada1, String pSenhaAlterada2) {

		String msg_erro = "";
		
		msg_erro = verificaSenhasIguais(pSenhaAlterada1, pSenhaAlterada2);
		
		if(msg_erro.trim().equals("") == true) {
			
			atualizaSenhaUsuarioBanco(pEmpresa, pUsuario, pSenhaAlterada1);
			usuarioEntity.setMsg_erro("");
			
			return usuarioEntity;
			
		}else {
			
			usuarioEntity.setMsg_erro(msg_erro);

			return usuarioEntity;
		}
	}
	
	public String verificaUsuarioSenhaPreenchida(String pUsuarioLogin, String pUsuarioSenha) {
		
		String msg_erro = "";
		
		if (pUsuarioLogin.trim().equals("") == true) {
			msg_erro += "\nO usuário não pode ser vazio!!";
		}
		if (pUsuarioSenha.trim().equals("") == true) {
			msg_erro += "\nA senha não pode ser vazia!!";
		}
		
		return msg_erro;
	}
	
	public String verificaSenhasIguais(String pSenhaAlterada1, String pSenhaAlterada2) {
		
		String msg_erro = "";
		
		if (pSenhaAlterada1.trim().equals("") == true) {
			msg_erro += "\nO usuário não pode ser vazio!!";
		}
		if (pSenhaAlterada2.trim().equals("") == true) {
			msg_erro += "\nA senha não pode ser vazia!!";
		}
		if(pSenhaAlterada1.equals(pSenhaAlterada2) == false) {
			msg_erro += "\nAs senhas não são iguais!!";
		}
		
		return msg_erro;
	}

	private ArrayList<UsuarioEntity> verificaUsuarioSenhaBanco(int pEmpresa, String pUsuarioLogin, String pUsuarioSenha) {
		
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<UsuarioEntity> usuarioBusca = new ArrayList();
		
		usuarioBusca = usuarioRepository.consultaUsuario(1, pEmpresa, pUsuarioLogin, pUsuarioSenha);

		return usuarioBusca;
	}
	
	private void atualizaSenhaUsuarioBanco(int pEmpresa, UsuarioEntity pUsuario ,String pSenhaAlterada) {
		
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		
		usuarioRepository.alteraSenhaUsuario(pEmpresa, pUsuario, pSenhaAlterada);
	}
}
