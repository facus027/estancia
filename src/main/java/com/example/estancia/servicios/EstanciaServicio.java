/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Cliente;
import com.example.estancia.entidades.Estancia;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.ClienteRepositorio;
import com.example.estancia.repositorios.EstanciaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstanciaServicio {
    
    @Autowired
    private EstanciaRepositorio estaRepo;
    @Autowired
    private ClienteRepositorio clienRepo;
    
    
    @Transactional
    public void create(String idClien,String huesped,Date fechaDesde,Date fechaHasta) throws MiException{
        
        validar(idClien, huesped, fechaDesde, fechaHasta);
        
        Optional<Cliente>respClien=clienRepo.findById(idClien);
        
        Cliente cliente=new Cliente();
        
        if(respClien.isPresent()){
            
            Estancia estancia=new Estancia();
            
            estancia.setHuesped(huesped);
            estancia.setFechaDesde(fechaDesde);
            estancia.setFechaHasta(fechaHasta);
            estancia.setCliente(cliente);
            
            estaRepo.save(estancia);
            
        }
        
    }
    
    @Transactional
    public void update(String id,String huesped,Date fechaDesde,Date fechaHasta) throws MiException{
        
        validar(id, huesped, fechaDesde, fechaHasta);
        
        Optional<Estancia>respEsta=estaRepo.findById(id);
        
        if(respEsta.isPresent()){
            
            Estancia estancia=respEsta.get();
            
            estancia.setHuesped(huesped);
            estancia.setFechaDesde(fechaDesde);
            estancia.setFechaHasta(fechaHasta);
            estancia.setCliente(estancia.getCliente());
            
            estaRepo.save(estancia);
            
        }
    }
    
    public Estancia getOne(String id){
        return estaRepo.getOne(id);
    }
    
    public Estancia getName(String huesped){
        return estaRepo.searchHuesped(huesped);
    }
    
    @Transactional
     public void suprById(String id){
         estaRepo.deleteById(id);
     }
     
     public List<Estancia> getList(){
         List<Estancia> estaList=new ArrayList();
         estaList=estaRepo.findAll();
         return estaList;
     }
     
       public List<Estancia> getDateEnd(Date fechaHasta){
         List<Estancia> estaList=new ArrayList();
         estaList=estaRepo.reservaEnd(fechaHasta);
         return estaList;
     }
    
    
    public void validar(String idClien,String huesped,Date fechaDesde,Date fechaHasta) throws MiException{
        
        if(idClien.isEmpty()||idClien==null){
            throw new MiException("Debe ser un cliente registrado");
        }
        if(huesped.isEmpty()||huesped==null){
            throw new MiException("Debe ser un cliente registrado");
        }
        if(fechaDesde==null){
            throw new MiException("Debe ingresar una fecha de inicio");
        }
        if(fechaHasta==null){
            throw new MiException("Debe ingresar una fecha de salida");
        }
    }
    
    
}
