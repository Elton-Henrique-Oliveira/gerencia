package br.elton.bau.services;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import br.elton.usuarios.entities.UsuarioEntity;

@SuppressWarnings("serial")
public class MovimentacaoService extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioEntity usuarioTeste = new UsuarioEntity();
					usuarioTeste.setNome_usuario("teste");
					
					MovimentacaoService frame = new MovimentacaoService(usuarioTeste);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MovimentacaoService(UsuarioEntity pUsuario) {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Seja bem vindo " + pUsuario.getNome_usuario());
		setBounds(100, 100, 685, 363);
	}

}
