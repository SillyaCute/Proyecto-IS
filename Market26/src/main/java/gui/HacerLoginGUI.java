package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import businessLogic.BLFacade;
import domain.Seller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.Color;

public class HacerLoginGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCorreo;
	private JTextField textContraseña;

	public HacerLoginGUI(MainGUI mainGUI) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 250));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.CreateProduct"));
		
		setTitle("Register");
		getContentPane().setLayout(null);
		
		JLabel lblCorreo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Correo"));
		lblCorreo.setBounds(10, 22, 92, 20);
		getContentPane().add(lblCorreo);
		
		JLabel lblContraseña = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Contrasena"));
		lblContraseña.setBounds(10, 52, 92, 20);
		getContentPane().add(lblContraseña);
		
		textCorreo = new JTextField();
		textCorreo.setText((String) null);
		textCorreo.setBounds(112, 23, 250, 20);
		getContentPane().add(textCorreo);
		textCorreo.setColumns(10);
		
		textContraseña = new JTextField();
		textContraseña.setText((String) null);
		textContraseña.setColumns(10);
		textContraseña.setBounds(112, 53, 250, 20);
		getContentPane().add(textContraseña);
		
		JButton btnHacerLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("HacerLogin.HacerLogin"));
		btnHacerLogin.setBounds(60, 142, 239, 31);
		getContentPane().add(btnHacerLogin);
		
		JLabel lblMostrarError = new JLabel();
		lblMostrarError.setForeground(Color.RED);
		lblMostrarError.setBounds(25, 112, 337, 20);
		getContentPane().add(lblMostrarError);
		
		JLabel lblMostrarExito = new JLabel();
		lblMostrarExito.setForeground(Color.GREEN);
		lblMostrarExito.setBounds(25, 112, 337, 20);
		getContentPane().add(lblMostrarExito);
		
		btnHacerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String error = check_fields_Errors();
				
				if(error != null) {
					lblMostrarError.setText(error);
				}else {
					lblMostrarError.setText(null);
					mainGUI.setUsuario(facade.getUsuario(textCorreo.getText()));
					lblMostrarExito.setText(ResourceBundle.getBundle("Etiquetas").getString("HacerLogin.Exito"));
					
					new Timer(3000, new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        dispose();
					    }
					}) {{
					    setRepeats(false);
					}}.start();
				}
			}
		});
		
	}
	
	private String check_fields_Errors() {
		if ((textCorreo.getText().length()==0) || (textContraseña.getText().length()==0)) {
			return ResourceBundle.getBundle("Etiquetas").getString("HacerLogin.ErrorVacio");
		} else {
			BLFacade facade = MainGUI.getBusinessLogic();
			if (!facade.usuarioExistente(textCorreo.getText())) {
				return ResourceBundle.getBundle("Etiquetas").getString("HacerLogin.ErrorGmail");
			}else {
				if(!facade.contraseñaCorrecta(textCorreo.getText(), textContraseña.getText())) {
					return ResourceBundle.getBundle("Etiquetas").getString("HacerLogin.ErrorContraseña");
				}else {
					return null;
				}
			}
		}
	}
}
