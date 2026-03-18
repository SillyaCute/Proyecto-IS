package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import businessLogic.BLFacade;
import configuration.UtilDate;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;

public class RegisterGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCorreo;
	private JTextField textNombre;
	private JTextField textContraseña;
	JRadioButton rdbtnVendedor;
	JRadioButton rdbtnComprador;
	private final ButtonGroup tipoUsuario = new ButtonGroup();


	public RegisterGUI() {
		

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 250));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.CreateProduct"));
		
		setTitle("Register");
		getContentPane().setLayout(null);
			
		JLabel lblNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Nombre"));
		lblNombre.setBounds(6, 24, 92, 20);
		getContentPane().add(lblNombre);
		
		JLabel lblCorreo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Correo"));
		lblCorreo.setBounds(6, 54, 92, 20);
		getContentPane().add(lblCorreo);
		
		JLabel lblContraseña = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Contrasena"));
		lblContraseña.setBounds(6, 84, 92, 20);
		getContentPane().add(lblContraseña);
		
		textNombre = new JTextField();
		textNombre.setText((String) null);
		textNombre.setColumns(10);
		textNombre.setBounds(108, 25, 250, 20);
		getContentPane().add(textNombre);
		
		textCorreo = new JTextField();
		textCorreo.setText((String) null);
		textCorreo.setBounds(108, 55, 250, 20);
		getContentPane().add(textCorreo);
		textCorreo.setColumns(10);
		
		textContraseña = new JTextField();
		textContraseña.setText((String) null);
		textContraseña.setColumns(10);
		textContraseña.setBounds(108, 85, 250, 20);
		getContentPane().add(textContraseña);
		
		JButton btnRegistrarse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Registrarse"));
		btnRegistrarse.setBounds(60, 172, 255, 31);
		getContentPane().add(btnRegistrarse);
		
		rdbtnVendedor = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Vendedor")); //$NON-NLS-1$ //$NON-NLS-2$
		rdbtnVendedor.setBounds(60, 116, 102, 20);
		getContentPane().add(rdbtnVendedor);
		tipoUsuario.add(rdbtnVendedor);
		
		rdbtnComprador = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Comprador")); //$NON-NLS-1$ //$NON-NLS-2$
		rdbtnComprador.setBounds(213, 116, 102, 20);
		getContentPane().add(rdbtnComprador);
		tipoUsuario.add(rdbtnComprador);
		
		JLabel lblMostrarError = new JLabel();
		lblMostrarError.setForeground(Color.red);
		lblMostrarError.setBounds(21, 142, 337, 20);
		getContentPane().add(lblMostrarError);
		
		JLabel lblMostrarExito = new JLabel();
		lblMostrarExito.setForeground(Color.GREEN);
		lblMostrarExito.setBounds(21, 142, 337, 20);
		getContentPane().add(lblMostrarExito);
		
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = null;
				String error = check_fields_Errors();
				
				if(error != null) {
					lblMostrarError.setText(error);
				}else {
					lblMostrarError.setText(null);
					BLFacade facade = MainGUI.getBusinessLogic();
					if(rdbtnVendedor.isSelected()) {
						tipo = "Vendedor";
					}else if(rdbtnComprador.isSelected()){
						tipo = "Comprador";
					}else {
						
					}
					facade.crearUsuario(textNombre.getText(), textCorreo.getText(), textContraseña.getText(), tipo);
					lblMostrarExito.setText(ResourceBundle.getBundle("Etiquetas").getString("Registrarse.Exito"));
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
		if ((textCorreo.getText().length()==0) || (textNombre.getText().length()==0)  || (textContraseña.getText().length()==0)) {
			return ResourceBundle.getBundle("Etiquetas").getString("Registrarse.ErrorVacio");
		} else {
			BLFacade facade = MainGUI.getBusinessLogic();
			if (!(textCorreo.getText().endsWith("@gmail.com"))) {
				return ResourceBundle.getBundle("Etiquetas").getString("Registrarse.ErrorGmail");
			}else if(facade.usuarioExistente(textCorreo.getText())){
				return ResourceBundle.getBundle("Etiquetas").getString("Registrarse.ErrorDuplicado");
			}else {
				if(!rdbtnVendedor.isSelected() && !rdbtnComprador.isSelected()) {
					return ResourceBundle.getBundle("Etiquetas").getString("Registrarse.ErrorTipo");
				}else {
					return null;
				}
			}
		}
	}
}
