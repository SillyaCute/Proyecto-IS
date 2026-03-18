package businessLogic;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
import domain.Contraoferta;
import domain.Sale;
import domain.Seller;
import domain.Usuario;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	 private static final int baseSize = 160;

		private static final String basePath="src/main/resources/images/";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;		
	}
    

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
	public Sale createSale(String title, String description,int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		dbManager.open();
		Sale product=dbManager.createSale(title, description, status, price, pubDate, sellerEmail, file);		
		dbManager.close();
		return product;
   };
   
	/**
	 * {@inheritDoc}
	 */
  @WebMethod
	public void crearUsuario(String nombre, String correo, String contraseña, String tipo) {
		dbManager.open();
		dbManager.crearUsuario(nombre, correo, contraseña, tipo);		
		dbManager.close();
  };
  
	/**
	 * {@inheritDoc}
	 */
@WebMethod
	public Usuario getUsuario(String correo) {
		dbManager.open();
		Usuario usuarioDB = dbManager.getUsuario(correo);		
		dbManager.close();
		return usuarioDB;
	};

	/**
	 * {@inheritDoc}
	 */
@WebMethod
	public boolean usuarioExistente(String correo) {
		dbManager.open();
		Boolean existe = dbManager.usuarioExistente(correo);		
		dbManager.close();
		return existe;
	}

/**
 * {@inheritDoc}
 */
@WebMethod
	public boolean contraseñaCorrecta(String correo, String contraseña) {
	dbManager.open();
	Boolean correcta = dbManager.contraseñaCorrecta(correo, contraseña);		
	dbManager.close();
	return correcta;
}


/**
 * {@inheritDoc}
 */
@WebMethod
	public void crearContraoferta(Contraoferta contra) {
		dbManager.open();
		dbManager.crearContraoferta(contra);		
		dbManager.close();
};

/**
 * {@inheritDoc}
 */
@WebMethod
public List<Contraoferta> getContraofertas(Seller manrique){
	dbManager.open();
	List<Contraoferta> contras = dbManager.getContraofertas(manrique);		
	dbManager.close();
	return contras;
};
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Sale> getSales(String desc){
		dbManager.open();
		List<Sale>  rides=dbManager.getSales(desc);
		dbManager.close();
		return rides;
	}
	
	/**
	    * {@inheritDoc}
	    */
		@WebMethod 
		public List<Sale> getPublishedSales(String desc, Date pubDate) {
			dbManager.open();
			List<Sale>  rides=dbManager.getPublishedSales(desc,pubDate);
			dbManager.close();
			return rides;
		}
	/**
	    * {@inheritDoc}
	    */
	@WebMethod public BufferedImage getFile(String fileName) {
		return dbManager.getFile(fileName);
	}

    
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /**
	 * {@inheritDoc}
	 */
    @WebMethod public Image downloadImage(String imageName) {
        File image = new File(basePath+imageName);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}

