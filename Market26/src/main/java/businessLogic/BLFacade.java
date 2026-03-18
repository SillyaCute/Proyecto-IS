package businessLogic;

import java.io.File;
import java.util.Date;
import java.util.List;

import domain.Contraoferta;
import domain.Sale;
import domain.Seller;
import domain.Usuario;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.awt.image.BufferedImage;
import java.awt.Image;

import gui.*;
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates/adds a product to a seller
	 * 
	 * @param title of the product
	 * @param description of the product
	 * @param status 
	 * @param selling price
	 * @param category of a product
	 * @param publicationDate
	 * @return Sale
	 */
   @WebMethod
	public Sale createSale(String title, String description, int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException;
	
	
	/**
	 * This method retrieves the products that contain desc
	 * 
	 * @param desc the text to search
	 * @return collection of sales that contain desc 
	 */
	@WebMethod public List<Sale> getSales(String desc);
	
	/**
	 * Este metodo crea un usuario
	 * 
	 * @param Nombre del usuario
	 * @param Correo del usuario
	 * @param Contraseña del usuario
	 * @param Tipo de usuario
	 */
	@WebMethod public void crearUsuario(String nombre, String correo, String contraseña, String tipo);
	
	/**
	 * Este metodo guarda la contraoferta realizada por el comprador en la base de datos, y borra de la misma la compra a la
	 * cual se le realiza la contraoferta.
	 * 
	 * @param La contraoferta realizada por el comprador
	 */	
	@WebMethod public void crearContraoferta(Contraoferta contra);
	
	/**
	 * Este metodo devuelve una lista de contraofertas de un vendedor en especifico
	 * 
	 * @return Lista de contraofertas
	 */	
	@WebMethod public List<Contraoferta> getContraofertas(Seller mauricio);
	
	/**
	 * Este metodo busca en la base de datos el usuario que coincida con el correo indicado
	 *  
	 * @param Correo del vendedor
	 * @param Contraseña del vendedor
	 */
	@WebMethod public Usuario getUsuario(String correo);
	
	/**
	 * Este metodo busca en la base de datos el correo prporcionado para saber si existe ya un usuario con ese correo
	 *  
	 * @param Correo del vendedor
	 * @return Si existe o no el usuario
	 */
	@WebMethod public boolean usuarioExistente(String correo);
	
	/**
	 * Este metodo busca en la base de datos el usuario que corresponda con el correo adjunto, y revisa si la contraseña coincide
	 *  
	 * @param Correo del vendedor
	 * @param Contraseña del vendedor
	 * @return Si la contraseña es correcta
	 */
	@WebMethod public boolean contraseñaCorrecta(String correo, String contraseña);
	
	/**
	 * 	 * This method retrieves the products that contain a desc text in a title and the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @param pubDate the date  of the publication date
	 * @return collection of sales that contain desc and published before pubDate
	 */
	@WebMethod public List<Sale> getPublishedSales(String desc, Date pubDate);

	
	/**
	 * This method calls the data access to initialize the database with some sellers and products.
	 * It is only invoked  when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
		
	@WebMethod public Image downloadImage(String imageName);
	

	
}
