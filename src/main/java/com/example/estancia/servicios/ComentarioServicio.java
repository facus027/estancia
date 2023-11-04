/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Comentario;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.ComentarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicio {
    
    @Autowired
    private ComentarioRepositorio comeRepo;
    
    @Transactional
    public void create(String descripcion) throws MiException{
        
        if(descripcion.isEmpty()||descripcion==null){
            throw new MiException("Debe ingresar un comentario para continuar");
        }
        
        Comentario come=new Comentario();
        
        come.setDescripcion(descripcion);
        comeRepo.save(come);
        
    }
    
    @Transactional
    public void update(String descripcion,String id) throws MiException{
        
        Optional<Comentario> respCome=comeRepo.findById(id);
        
        if(descripcion.isEmpty()||descripcion==null){
            throw new MiException("Debe ingresar un comentario para continuar");
        }
        
        if(respCome.isPresent()){
           
            Comentario come=respCome.get();
        
        come.setDescripcion(descripcion);
        comeRepo.save(come);
            
        }
 
    }
    
      public Comentario getOne(String id){
        return comeRepo.getOne(id);
    }
    
    @Transactional
     public void suprById(String id){
         comeRepo.deleteById(id);
     }
    
    public List<Comentario> getList(){
         List<Comentario> comeList=new ArrayList();
         comeList=comeRepo.findAll();
         return comeList;         
     }
    
}
