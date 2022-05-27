package br.com.senai.manutencaosenaiapi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.service.LoginService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@Component
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField edtLogin;
	private JPasswordField pfSenha;
	
	@Autowired
	private LoginService loginService;
	
	private JComboBox<String> cbPerfil;
	
	private void carregarOpcoes() {
		this.cbPerfil.addItem("Atendente");
		this.cbPerfil.addItem("Técnico");
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogin = new JLabel("Login");
		
		edtLogin = new JTextField();
		edtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		
		pfSenha = new JPasswordField();
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String login = edtLogin.getText();
					 String senha = new String(pfSenha.getPassword());
					Login loginEncontrado = loginService.logar(
							login, senha, cbPerfil.getSelectedItem().toString());
					if (loginEncontrado.getPerfil().equals(cbPerfil.getItemAt(0))) {
						JOptionPane.showMessageDialog(contentPane, 
								"Seja bem vindo "+ loginEncontrado.getLogin()+
								"\nPerfil: "+cbPerfil.getSelectedItem());
					}else {
						JOptionPane.showMessageDialog(contentPane, "Técnico acessado");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});
		
		cbPerfil = new JComboBox<String>();
		this.carregarOpcoes();
		
		JLabel lblPerfil = new JLabel("Perfil");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cbPerfil, 0, 189, Short.MAX_VALUE)
						.addComponent(lblPerfil)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(pfSenha, Alignment.LEADING)
							.addComponent(lblLogin, Alignment.LEADING)
							.addComponent(edtLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addComponent(lblSenha, Alignment.LEADING)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(btnLogar, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addGap(110))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPerfil)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
