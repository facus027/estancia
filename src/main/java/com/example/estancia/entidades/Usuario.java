
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
   @Nullable
    private Date fechaBaja;
   
   @Enumerated(EnumType.STRING)
   private Rol rol;
    
}
