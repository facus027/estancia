
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
   @Nullable
   private Estancia estancia;
   @OneToOne
   @Nullable
   private Comentario comentario;
    
    
}
