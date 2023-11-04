/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Familia;
import com.example.estancia.entidades.Usuario;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.FamiliaRepositorio;
import com.example.estancia.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamiliaServicio {
    
    @Autowired
    private FamiliaRepositorio famiRepo;
    @Autowired
    private UsuarioRepositorio usuRepo;
    
    @Transactional
    public void create(String nombre, Integer edadMin, Integer edadMax,Integer numHijos,
                               String idUser) throws MiException{
        
        Validar(nombre, edadMin, edadMax, numHijos,idUser);
        
        Optional<Usuario> respUsu = usuRepo.findById(idUser);
        
        Usuario usuario=new Usuario();
            
        if(respUsu.isPresent()){
            usuario=respUsu.get();
        }
        
        Familia family=new Familia();
        
        family.setNombre(nombre);
        family.setEdadMin(edadMin);
        family.setEdadMax(edadMax);
        family.setNumHihos(numHijos);
        family.setEmail(usuario.getEmail());
        family.setUsuario(usuario);
        
        famiRepo.save(family);
        
    }
    
    @Transactional
    public void update(String nombre, Integer edadMin, Integer edadMax,Integer numHijos,
                              String email,String id) throws MiException{
        
        Validar(nombre, edadMin, edadMax, numHijos, id);
        
        if(email.isEmpty()||email==null){
            throw new MiException("Debe ingresar un email");
        }
        
        Optional<Familia>respFami=famiRepo.findById(id);
        
        if(respFami.isPresent()){
            Familia family=respFami.get();
            
        family.setNombre(nombre);
        family.setEdadMin(edadMin);
        family.setEdadMax(edadMax);
        family.setNumHihos(numHijos);
        family.setEmail(email);
        family.setUsuario(family.getUsuario());
        
        famiRepo.save(family);
            
        }
    }
    
    
    public List<Familia> getList(){
        List<Familia> familyList=new ArrayList();
        familyList=famiRepo.findAll();
        return familyList;
    }
    
    public List<Familia> getNumHijos(Integer minimo,Integer maximo){
        List<Familia> familyList=new ArrayList();
        familyList=famiRepo.filterNumHijos(minimo, maximo);
        return familyList;
    }
    
    public List<Familia> getRangeAge(Integer edad){
        List<Familia> familyList=new ArrayList();
        familyList=famiRepo.rangeEdad(edad);
        return familyList;
    }
    
    
    
     public Familia getOne(String id){
        return famiRepo.getOne(id);
    }
    
    public Familia getName(String nombre){
        return famiRepo.searchName(nombre);
    }
    
     public Familia getEmail(String email){
        return famiRepo.searchEmail(email);
    }
    
     @Transactional
     public void suprById(String id){
         famiRepo.deleteById(id);
     }
     
     
    
    public void Validar(String nombre, Integer edadMin, Integer edadMax,Integer numHijos,
                               String idUser) throws MiException{
        
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("Debe ingrear un nombre");
        }
        if(edadMin<0||edadMin==null){
            throw new MiException("Debe ingrear una edad minima");
        }
         if(edadMax<0||edadMax==null){
            throw new MiException("Debe ingrear una edad maxima");
        }
        if(numHijos<0||numHijos==null){
            throw new MiException("Debe ingrear una cantidad de hijos");
        }
       
        if(idUser==null){
            throw new MiException("Debe estar registrado como usuario");
        }
    }
 
}
