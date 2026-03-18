package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@DiscriminatorValue(value="Comprador")
public class Comprador extends Usuario {

	private static final long serialVersionUID = 1L;

	public Comprador() {
        super();
    }

    public Comprador(String name, String email) {
        super(name, email);
    }
    public Comprador(String name, String email, String contraseña) {
        super(name, email, contraseña);
    }
    
    @Override
    public String toString() {
    	return "correo: "+ this.email + " de " + this.name;
    }
}
