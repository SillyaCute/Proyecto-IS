package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class QuerySalesGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products")); 

	private JButton jButtonSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts= new JTable();

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 

	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate"),

	};
	private JTextField jTextFieldSearch;
	//Diogo Agregado nuevos fields
	private final JTextField textFieldPrecioMax = new JTextField();
	private final JTextField textFieldFecha = new JTextField();
	private final JLabel label_1 = new JLabel("Precio Maximo");
	private final JLabel label_2 = new JLabel("Fecha");
	

	public QuerySalesGUI() {
		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.FindProducts"));
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
		tableModelProducts.setColumnCount(4); // another column added to allocate ride objects

		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);


		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable

		this.getContentPane().add(scrollPanelProducts, null);
		
		jTextFieldSearch = new JTextField();
		jTextFieldSearch.setBounds(52, 56, 158, 26);
		getContentPane().add(jTextFieldSearch);
		jTextFieldSearch.setColumns(10);
		
		 jButtonSearch.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
					tableModelProducts.setDataVector(null, columnNamesProducts);
					tableModelProducts.setColumnCount(4);

					BLFacade facade = MainGUI.getBusinessLogic();
					Date today = UtilDate.trim(new Date());

					//Diogo - parte para hacer el filtro de date
					//Los filtros son 1-la del nombre la default 2- De fechas despues de X  3- De precios menores que X 
					//Comprobar que no es null y puedo hacer el filtro
					Boolean fecha = false;
					Boolean precio = false;
					float precioMax = 0;
					Date date = null;
					//Parte del filtro de fecha
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
					String dateInString = textFieldFecha.getText();
					if(!dateInString.equals("")) {
						try {
							date = formatter.parse(dateInString);
							fecha = true;
						}catch(Exception pp) {
							fecha = false;
						}
					}
					//Parte del filtro de precio 
					if(!textFieldPrecioMax.getText().equals("")) {
						try {
							precioMax = Float.parseFloat(textFieldPrecioMax.getText());
							precio = true;
						}catch(Exception pp) {
							precioMax = 0; 
							precio = false;
						}
					}
					
				    //No he tocado esto
					List<domain.Sale> sales=facade.getPublishedSales(jTextFieldSearch.getText(),today);

					if (sales.isEmpty() ) jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.NoProducts"));
					else jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products"));
					for (domain.Sale sale:sales){
						//Diogo Parte del filtro
						if(!sale.getSeller().getName().equals("Basura")) {
							//Si se ha dado ambas la fecha y el precio
							if( fecha && precio) {
								if(sale.getPrice()<precioMax &&sale.getPublicationDate().after(date)) {
									Vector<Object> row = new Vector<Object>();
									row.add(sale.getTitle());
									row.add(sale.getPrice());
									row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
									row.add(sale); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
									tableModelProducts.addRow(row);	
								}
						    //Filtro condicion para agregar dependiendo de fecha
							}else if(fecha) {
								if(sale.getPublicationDate().after(date)) {
									Vector<Object> row = new Vector<Object>();
									row.add(sale.getTitle());
									row.add(sale.getPrice());
									row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
									row.add(sale); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
									tableModelProducts.addRow(row);	
								}
							//Solo precio 
							}else if(precio){
								if(sale.getPrice()<precioMax) {
									Vector<Object> row = new Vector<Object>();
									row.add(sale.getTitle());
									row.add(sale.getPrice());
									row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
									row.add(sale); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
									tableModelProducts.addRow(row);	
								}
							//Default
							}else {
								Vector<Object> row = new Vector<Object>();
								row.add(sale.getTitle());
								row.add(sale.getPrice());
								row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
								row.add(sale); // product object added in order to obtain it with tableModelProducts.getValueAt(i,2)
								tableModelProducts.addRow(row);	
							}
						}
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
		 		
				tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
				tableProducts.getColumnModel().getColumn(1).setPreferredWidth(10);
				tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);

				tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3)); // not shown in JTable
		 		
		 	}
		 });
		jButtonSearch.setBounds(549, 54, 117, 29);
		getContentPane().add(jButtonSearch);
		textFieldPrecioMax.setColumns(10);
		textFieldPrecioMax.setBounds(220, 56, 130, 26);
		
		getContentPane().add(textFieldPrecioMax);
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(362, 56, 130, 26);
		
		getContentPane().add(textFieldFecha);
		
		JLabel label = new JLabel("Nombre");
		label.setBounds(52, 27, 60, 17);
		getContentPane().add(label);
		label_1.setBounds(220, 27, 60, 17);
		
		getContentPane().add(label_1);
		label_2.setBounds(368, 27, 60, 17);
		
		getContentPane().add(label_2);
		
	    
		tableProducts.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mousePressed(MouseEvent mouseEvent) {
		            
		            if(mouseEvent.getClickCount() == 2)
		            {
				        JTable table =(JTable) mouseEvent.getSource();
		            	Point point = mouseEvent.getPoint();
				        int row = table.rowAtPoint(point);
		            	Sale s=(Sale) tableModelProducts.getValueAt(row, 3);
			            new ShowSaleGUI(s);
		            }
		        }
		 });
	}
