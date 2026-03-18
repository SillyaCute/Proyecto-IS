package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import businessLogic.BLFacade;
import domain.Comprador;
import domain.Seller;
import domain.Usuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
    private Usuario usuarioMain;
    private Seller vendedorMain;
    private Comprador compradorMain;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonRegistrarse = null;
	private JButton jButtonHacerLogin = null;
	private JButton JButtonAceptarOferta;
	private JButton JButtonVerOfertasAceptadas;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade facade){
		appFacadeInterface=facade;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * This is the default constructor
	 */
	public MainGUI(String mail) {
		super();

		this.usuarioMain = new Usuario("Sin Registrar", "inutil");
		
		this.setSize(610, 450);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setBounds(0, 0, 602, 67);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				paintAgain();
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
	
		panel = new JPanel();
		panel.setBounds(152, 356, 301, 31);
		panel.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton_2);
		panel.add(rdbtnNewRadioButton);
		
		jButtonCreateQuery = new JButton();
		jButtonCreateQuery.setBounds(301, 168, 301, 84);
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateSale"));
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateSaleGUI(vendedorMain.getEmail());
				a.setVisible(true);
			}
		});
		
		jButtonQueryQueries = new JButton();
		jButtonQueryQueries.setBounds(0, 168, 301, 84);
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QuerySales"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new QuerySalesGUI();

				a.setVisible(true);
			}
		});
		
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabelSelectOption);
		
		jButtonRegistrarse = new JButton();
		jButtonRegistrarse.setBounds(0, 74, 301, 84);
		jButtonRegistrarse.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));
		jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RegisterGUI();
				a.setVisible(true);
			}
		});
		
		jButtonHacerLogin = new JButton();
		jButtonHacerLogin.setBounds(301, 74, 301, 84);
		jButtonHacerLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.HacerLogin"));
		jButtonHacerLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new HacerLoginGUI(MainGUI.this);
				a.setVisible(true);
			}
		});
		
		JButtonAceptarOferta = new JButton();
		JButtonAceptarOferta.setBounds(0, 262, 301, 84);
		JButtonAceptarOferta.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.AceptarOferta"));
		JButtonAceptarOferta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new AceptarOfertaGUI(compradorMain);
				a.setVisible(true);
			}
		});
		
		JButtonVerOfertasAceptadas = new JButton();
		JButtonVerOfertasAceptadas.setBounds(301, 262, 294, 84);
		JButtonVerOfertasAceptadas.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerOfertasAceptadas"));
		JButtonVerOfertasAceptadas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new VerOfertasAceptadasGUI(vendedorMain);
				a.setVisible(true);
			}
		});
		
		jContentPane.add(jButtonHacerLogin);
		jContentPane.add(jButtonRegistrarse);
		jContentPane.add(jButtonCreateQuery);
		jContentPane.add(jButtonQueryQueries);
		jContentPane.add(JButtonAceptarOferta);
		jContentPane.add(JButtonVerOfertasAceptadas);
		jContentPane.add(panel);
		
		if(usuarioMain.getTipo() == "inutil") {
			jButtonCreateQuery.setEnabled(false);
			jButtonQueryQueries.setEnabled(false);
			JButtonAceptarOferta.setEnabled(false);
			JButtonVerOfertasAceptadas.setEnabled(false);
			jButtonHacerLogin.setEnabled(true);
			jButtonRegistrarse.setEnabled(true);
		}
		
		
		setContentPane(jContentPane);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") +": "+usuarioMain.getEmail());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}

	private void paintAgain() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QuerySales"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateSale"));
		jButtonHacerLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.HacerLogin"));
		jButtonRegistrarse.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Registrarse"));
		JButtonAceptarOferta.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.AceptarOferta"));
		JButtonVerOfertasAceptadas.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerOfertasAceptadas"));
		if(this.usuarioMain != null) {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ ": "+usuarioMain.getEmail());
		}else if(this.vendedorMain != null) {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ ": "+vendedorMain.getEmail());
		}else if(this.compradorMain != null) {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ ": "+compradorMain.getEmail());
		}
	}
	
	public void setUsuario(Object usuarioImpuesto) {
		Usuario us = new Usuario();
		Seller v = new Seller();
		Comprador c = new Comprador();

	    if(usuarioImpuesto.getClass() == us.getClass()) {
	    	this.usuarioMain = (Usuario) usuarioImpuesto;
	    	this.vendedorMain = null;
	    	this.compradorMain = null;
			jButtonCreateQuery.setEnabled(false);
			jButtonQueryQueries.setEnabled(false);
			jButtonHacerLogin.setEnabled(true);
			jButtonRegistrarse.setEnabled(true);
			JButtonAceptarOferta.setEnabled(false);
			JButtonVerOfertasAceptadas.setEnabled(false);
			
		}else if(usuarioImpuesto.getClass() == v.getClass()){
			this.vendedorMain = (Seller) usuarioImpuesto;
			this.compradorMain = null;
			this.usuarioMain = null;
			jButtonCreateQuery.setEnabled(true);
			jButtonQueryQueries.setEnabled(true);
			jButtonHacerLogin.setEnabled(true);
			jButtonRegistrarse.setEnabled(true);
			JButtonAceptarOferta.setEnabled(false);
			JButtonVerOfertasAceptadas.setEnabled(true);
			
		}else if(usuarioImpuesto.getClass() == c.getClass()){
			this.compradorMain = (Comprador) usuarioImpuesto;
			this.vendedorMain = null;
			this.usuarioMain = null;
			jButtonCreateQuery.setEnabled(false);
			jButtonQueryQueries.setEnabled(true);
			jButtonHacerLogin.setEnabled(true);
			jButtonRegistrarse.setEnabled(true);
			JButtonAceptarOferta.setEnabled(true);
			JButtonVerOfertasAceptadas.setEnabled(false);
		}
	    paintAgain();
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

