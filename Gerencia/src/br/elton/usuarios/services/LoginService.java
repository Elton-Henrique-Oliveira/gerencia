package br.elton.usuarios.services;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.elton.menu.services.WindowPrincipalService;
import br.elton.usuarios.entities.UsuarioEntity;
import br.elton.usuarios.usercases.UsuarioUserCase;

@SuppressWarnings("serial")
public class LoginService extends JFrame {

	private JPanel contentPane;
	private JTextField imput_usuario;
	private JPasswordField imput_senha;
	static LoginService frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginService();
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
	public LoginService() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LoginService.class.getResource("/br/elton/imagens/pessoal.png")));
		setTitle("Seja bem vindo");
		setBackground(SystemColor.controlDkShadow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 296);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel painel_principal = new JPanel();
		painel_principal.setBackground(SystemColor.controlDkShadow);
		contentPane.add(painel_principal, BorderLayout.CENTER);
		painel_principal.setLayout(null);

		JLabel lbl_usuario = new JLabel("Usuario");
		lbl_usuario.setForeground(SystemColor.menu);
		lbl_usuario.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_usuario.setBounds(38, 50, 86, 33);
		painel_principal.add(lbl_usuario);

		JLabel lbl_senha = new JLabel("Senha");
		lbl_senha.setForeground(SystemColor.menu);
		lbl_senha.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_senha.setBounds(38, 94, 86, 33);
		painel_principal.add(lbl_senha);

		imput_usuario = new JTextField();
		imput_usuario.setBackground(SystemColor.windowBorder);
		imput_usuario.setForeground(SystemColor.window);
		imput_usuario.setFont(new Font("Arial", Font.PLAIN, 15));
		imput_usuario.setBounds(134, 50, 265, 33);
		painel_principal.add(imput_usuario);
		imput_usuario.setColumns(10);

		JButton btn_logar = new JButton("ENTRAR");
		btn_logar.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation" })
			public void actionPerformed(ActionEvent e) {

				UsuarioEntity usuarioBusca = new UsuarioEntity();
				UsuarioUserCase usuarioUserCase = new UsuarioUserCase();

				usuarioBusca = usuarioUserCase.verificaLogin(1, imput_usuario.getText(), imput_senha.getText());
				if (usuarioBusca.getMsg_erro().trim().equals("") == true) {
					
					WindowPrincipalService window = new WindowPrincipalService(usuarioBusca);
					window.setVisible(true);
					window.setLocationRelativeTo(null);

					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, usuarioBusca.getMsg_erro());
				}
			}
		});
		btn_logar.setForeground(SystemColor.inactiveCaptionText);
		btn_logar.setFont(new Font("Candara Light", Font.BOLD, 15));
		btn_logar.setBounds(77, 162, 156, 43);
		painel_principal.add(btn_logar);

		JButton btn_alterar = new JButton("ALTERAR");
		btn_alterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioBusca = new UsuarioEntity();
				UsuarioUserCase usuarioUserCase = new UsuarioUserCase();

				usuarioBusca = usuarioUserCase.verificaLogin(1, imput_usuario.getText(), imput_senha.getText());
				if (usuarioBusca.getMsg_erro().trim().equals("") == true) {
					AlterarService window = new AlterarService(usuarioBusca);
					window.setVisible(true);
					window.setLocationRelativeTo(null);
					
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, usuarioBusca.getMsg_erro());
				}
			}
		});
		btn_alterar.setForeground(SystemColor.inactiveCaptionText);
		btn_alterar.setFont(new Font("Candara Light", Font.BOLD, 15));
		btn_alterar.setBounds(243, 162, 156, 43);
		painel_principal.add(btn_alterar);

		imput_senha = new JPasswordField();
		imput_senha.setBackground(SystemColor.windowBorder);
		imput_senha.setForeground(SystemColor.window);
		imput_senha.setFont(new Font("Arial", Font.PLAIN, 15));
		imput_senha.setBounds(134, 94, 220, 33);
		painel_principal.add(imput_senha);

		JLabel lbl_senha_mostra = new JLabel("");
		lbl_senha_mostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				manipulaSenha();
			}
		});
		lbl_senha_mostra.setIcon(new ImageIcon(LoginService.class.getResource("/br/elton/imagens/pngwing.com_3.png")));
		lbl_senha_mostra.setForeground(SystemColor.textHighlight);
		lbl_senha_mostra.setBackground(Color.WHITE);
		lbl_senha_mostra.setBounds(364, 98, 30, 29);
		painel_principal.add(lbl_senha_mostra);
	}

	private void manipulaSenha() {
		if (imput_senha.getEchoChar() != '\u0000') { // mascara e desmacara senha
			imput_senha.setEchoChar('\u0000');
		} else {
			imput_senha.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
		}
	}
}
