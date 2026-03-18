package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;
import domain.Seller;

public class VerOfertasAceptadasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products")); 

	private JButton jButtonSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts= new JTable();

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 

	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.Titulo"), 
			ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.PrecioOriginal"),
			ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.Contraoferta"),
			ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.Comprador"),

	};
	private JTextField jTextFieldSearch;
	

	public VerOfertasAceptadasGUI(Seller magdaleno) {
		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.BuscarProductos"));
		jLabelProducts.setBounds(52, 108, 427, 16);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(220, 379, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				thisFrame.setVisible(false);

			}
		});		
		
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(52, 137, 459, 150));

		scrollPanelProducts.setViewportView(tableProducts);
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);

		tableProducts.setModel(tableModelProducts);

		tableModelProducts.setDataVector(null, columnNamesProducts);
		tableModelProducts.setColumnCount(4);

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableProducts.getColumnModel().getColumn(3).setPreferredWidth(120);

		this.getContentPane().add(scrollPanelProducts, null);
		
		jTextFieldSearch = new JTextField();
		jTextFieldSearch.setBounds(52, 56, 357, 26);
		getContentPane().add(jTextFieldSearch);
		jTextFieldSearch.setColumns(10);
		
		 jButtonSearch.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
					tableModelProducts.setDataVector(null, columnNamesProducts);
					tableModelProducts.setColumnCount(4);

					BLFacade facade = MainGUI.getBusinessLogic();
					List<domain.Contraoferta> contraofertas=facade.getContraofertas(magdaleno);

					if (contraofertas.isEmpty() ) jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.SinContraofertas"));
					else jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("VerOfertasAceptadasGUI.Contraofertas"));
					for (domain.Contraoferta contra:contraofertas){
						Vector<Object> row = new Vector<Object>();
						row.add(contra.getCompra().getTitle());
						row.add(contra.getCompra().getPrice());
						row.add(contra.getPrecioOfrecido());
						row.add(contra.getComprador().getName());
						tableModelProducts.addRow(row);		
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
		 		
		 	}
		 });
		 
		jButtonSearch.setBounds(427, 56, 117, 29);
		getContentPane().add(jButtonSearch);
	}
}
