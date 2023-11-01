
package com.example.estancia.entidades;

import com.example.estancia.enums.TipoVi;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Casa {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    
    @Temporal(TemporalType.DATE) 
   private Date fechaDesde;
   @Temporal(TemporalType.DATE) 
   private Date fechaHasta;
   
   private Integer minDias;
   private Integer maxDias;
   private Double precio;
   
   @Enumerated(EnumType.STRING)
   private TipoVi tipoVivienda;
    
   @OneToOne
   private Familia familia;
   @OneToOne
   private Estancia estancia;
   @OneToOne
   private Comentario comentario;
    
    
}
