
package com.example.estancia.entidades;

import com.example.estancia.enums.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
     
   private String alias;
   private String email;
   private String clave;
   
   @Temporal(TemporalType.DATE) 
   private Date fechaAlta;
   @Temporal(TemporalType.DATE) 
    private Date fechaBaja;
   
   @Enumerated(EnumType.STRING)
   private Rol rol;
    
}
