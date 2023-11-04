/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Casa;
import com.example.estancia.entidades.Comentario;
import com.example.estancia.entidades.Estancia;
import com.example.estancia.entidades.Familia;
import com.example.estancia.enums.TipoVi;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.CasaRepositorio;
import com.example.estancia.repositorios.FamiliaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaServicio {
    
    @Autowired
    private CasaRepositorio casaRepo;
    @Autowired
    private FamiliaRepositorio famiRepo;
    @Autowired
    private EstanciaServicio es;
    
    
    @Transactional
    public void create(String calle,Integer numero,String codPostal,String ciudad,String pais,
                        Date fechaDesde,Date fechaHasta,Integer minDias,Integer maxDias,Double precio,
                        TipoVi tipoVivienda, String idFami) throws MiException{
        
        validar(calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, idFami);
        
        Optional<Familia>respFami=famiRepo.findById(idFami);
        
        Familia family=new Familia();
        
        if(respFami.isPresent()){
            family=respFami.get();
        }
        
        Casa casa=new Casa();
        
        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodPostal(codPostal);
        casa.setCiudad(ciudad);
        casa.setPais(pais);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setMinDias(minDias);
        casa.setMaxDias(maxDias);
        casa.setPrecio(precio);
        casa.setTipoVivienda(tipoVivienda);
        casa.setFamilia(family);
        
        casa.setComentario(null);
        casa.setEstancia(null);
        
        casaRepo.save(casa);
        
    }
    
    @Transactional
    public void update(String calle,Integer numero,String codPostal,String ciudad,String pais,
                        Date fechaDesde,Date fechaHasta,Integer minDias,Integer maxDias,Double precio,
                        TipoVi tipoVivienda, String id) throws MiException{
        
        validar(calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, id);
        
        Optional<Casa>respCasa=casaRepo.findById(id);
        
        if(respCasa.isPresent()){
            
            Casa casa=respCasa.get();
            
        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodPostal(codPostal);
        casa.setCiudad(ciudad);
        casa.setPais(pais);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setMinDias(minDias);
        casa.setMaxDias(maxDias);
        casa.setPrecio(precio);
        casa.setTipoVivienda(tipoVivienda);
        casa.setFamilia(casa.getFamilia());
        
        casa.setComentario(null);
        casa.setEstancia(null);
        
        casaRepo.save(casa);
            
        }
    }
  
//    @Transactional
//    public void updateEstancia(String idEst, String id){
//        
//        Optional<Casa>respCasa=casaRepo.findById(id);
//
//        
//        if(respCasa.isPresent()){
//            
//            Casa casa=respCasa.get();
//            
//   
//        casa.setEstancia(estancia);
//        
//        casaRepo.save(casa);
//            
//        }
//        
//    }
    
     @Transactional
    public void updateComentario(Comentario comentario, String id){
        
        Optional<Casa>respCasa=casaRepo.findById(id);
        
        if(respCasa.isPresent()){
            
            Casa casa=respCasa.get();
            
            casa.setComentario(comentario);
        
        casaRepo.save(casa);
            
        }
        
    }
    
    public Casa getOne(String id){
        return casaRepo.getOne(id);
    }
    
    @Transactional
     public void suprById(String id){
         casaRepo.deleteById(id);
     }
     
     public List<Casa> getListCity(String ciudad){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.searchCity(ciudad);
         return listCasa;
         
     }
     
       public List<Casa> getList(){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.findAll();
         return listCasa;
         
     }
     
       public List<Casa> getListCountry(String pais){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.searchCountry(pais);
         return listCasa;
         
     }
       
        public List<Casa> getListTipoVi(TipoVi tipoVivienda){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.searchTipoVivienda(tipoVivienda);
         return listCasa;
         
     }
        
         public List<Casa> getListPrece(Double minimo,Double maximo){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.filterPrecio (minimo,maximo);
         return listCasa;
         
     }  
         
         public List<Casa> getListDate(Date date ){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.searchDate (date);
         return listCasa;
         
     }   
         
           public List<Casa> getAmountDay(Integer dias){
         
         List<Casa> listCasa=new ArrayList();
         listCasa=casaRepo.searchAmountDay (dias);
         return listCasa;
         
     }    
       
       
    
    public void validar(String calle,Integer numero,String codPostal,String ciudad,String pais,
                        Date fechaDesde,Date fechaHasta,Integer minDias,Integer maxDias,Double precio,
                        TipoVi tipoVivienda, String idFami) throws MiException{
        
        if(calle.isEmpty()||calle==null){
            throw new MiException("Debe ingresar una calle");
        }
        if(numero==null){
            throw new MiException("Debe ingresar una numeracion");
        }
        if(codPostal.isEmpty()||codPostal==null){
            throw new MiException("Debe ingresar un codigo postal");
        }
        if(ciudad.isEmpty()||ciudad==null){
            throw new MiException("Debe ingresar una ciudad");
        }
        if(pais.isEmpty()||pais==null){
            throw new MiException("Debe ingresar un pais");
        }
        if(fechaDesde==null){
            throw new MiException("Debe ingresar una fecha disponible");
        }
        if(fechaHasta==null){
            throw new MiException("Debe ingresar una fecha disponible");
        }
        if(minDias==null){
            throw new MiException("Debe ingresar un minimo de dia");
        }
        if(maxDias==null){
            throw new MiException("Debe ingresar un maximo de dia");
        }
        if(precio==null){
            throw new MiException("Debe ingresar un precio");
        }
        if(tipoVivienda==null){
            throw new MiException("Debe ingresar un tipo de vivienda");
        }
         if(idFami.isEmpty()||idFami==null){
            throw new MiException("Debe tener una familia registrada");
        }
        
    }
    
}
