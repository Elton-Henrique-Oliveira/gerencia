package br.elton.menu.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.elton.bau.services.ListagemService;
import br.elton.usuarios.entities.UsuarioEntity;
import br.elton.usuarios.services.CriaService2;
import br.elton.usuarios.services.CriarService;

import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class WindowPrincipalService extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	static WindowPrincipalService frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioEntity usuarioTeste = new UsuarioEntity();
					usuarioTeste.setNome_usuario("Teste");
					
					frame = new WindowPrincipalService(usuarioTeste);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WindowPrincipalService(UsuarioEntity pUsuario) {
		setTitle("Seja bem vindo " + pUsuario.getNome_usuario());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 920);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setBackground(SystemColor.scrollbar);
		desktopPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JButton btnNewButton = new JButton("");
		desktopPane.setLayer(btnNewButton, 1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriaService2 cria = new CriaService2();
				contentPane.add(cria);
				cria.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(WindowPrincipalService.class.getResource("/br/elton/imagens/pessoal.png")));
		btnNewButton.setBounds(30, 31, 89, 59);
		desktopPane.add(btnNewButton);
	}
}
