package br.elton.usuarios.services;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CriarService extends JInternalFrame {
	private JTextField textField;
	private final List<JLabel> lista;
	private JPanel panel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarService frame = new CriarService();
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
	public CriarService() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 762, 437);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		
		lista = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
		//	adicionarLabel();
		}
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(38, 38, 143, 26);
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(141, 38, 194, 26);
		panel_2.add(textField);
		textField.setColumns(10);
		

	}
	
	public void adicionarLabel() {
        int n = lista.size() + 1;
        String name = "cor " + n;
        JLabel label = new JLabel(name);
        panel_2.add(label);
        label.setBounds(38, n * 38 + 20, 143, 26);
        lista.add(label);
    }
}
