/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Cliente;
import com.example.estancia.entidades.Usuario;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.ClienteRepositorio;
import com.example.estancia.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienRepo;
    @Autowired
    private UsuarioRepositorio usuRepo;
    
    @Transactional
    public void create(String nombre,String calle,Integer numero, String codPostal,
                          String ciudad,String pais,String idUsu) throws MiException{
        
        validar(nombre, calle, numero, codPostal, ciudad, pais, idUsu);
        
        Optional<Usuario>respUsu=usuRepo.findById(idUsu);
        
        Usuario usuario=new Usuario();
        
        if(respUsu.isPresent()){
            
            usuario=respUsu.get();
        }
        
        Cliente clente=new Cliente();
        
        clente.setNombre(nombre);
        clente.setCalle(calle);
        clente.setNumero(numero);
        clente.setCodPostal(codPostal);
        clente.setPais(pais);
        clente.setEmail(usuario.getEmail());
        clente.setUsuario(usuario);
        
        clienRepo.save(clente);
    
    }
    
    public void update(String nombre,String calle,Integer numero, String codPostal,
                          String ciudad,String pais,String id,String email) throws MiException{
        
        validar(nombre, calle, numero, codPostal, ciudad, pais, id);
        
        Optional<Cliente>respCli=clienRepo.findById(id);
        
        if(respCli.isPresent()){
            
            Cliente clente=respCli.get();
            
        clente.setNombre(nombre);
        clente.setCalle(calle);
        clente.setNumero(numero);
        clente.setCodPostal(codPostal);
        clente.setPais(pais);
        clente.setEmail(email);
        clente.setUsuario(clente.getUsuario());
        
        clienRepo.save(clente);
            
        }
        
    }
    
    public Cliente getOne(String id){
        return clienRepo.getOne(id);
    }
    
    @Transactional
     public void suprById(String id){
         clienRepo.deleteById(id);
     }
     
      public Cliente getName(String nombre){
        return clienRepo.searchName(nombre);
    }
    
     public Cliente getEmail(String email){
        return clienRepo.searchEmail(email);
    }
     
     public List<Cliente> getList(){
         List<Cliente> clienList=new ArrayList();
         clienList=clienRepo.findAll();
         return clienList;         
     }
    
    
    public void validar(String nombre,String calle,Integer numero, String codPostal,
                          String ciudad,String pais,String idUsu) throws MiException{
        
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("Debe ingrear un nombre");
        }
        if(calle.isEmpty()||calle==null){
            throw new MiException("Debe ingrear una calle");
        }
        if(numero==null){
            throw new MiException("Debe ingrear una numeracio");
        }
        if(codPostal.isEmpty()||codPostal==null){
            throw new MiException("Debe ingrear un codigo postal");
        }
        if(ciudad.isEmpty()||ciudad==null){
            throw new MiException("Debe ingrear una ciudad");
        }
        if(pais.isEmpty()||pais==null){
            throw new MiException("Debe ingrear un pais");
        }
        if(idUsu.isEmpty()||idUsu==null){
            throw new MiException("Debe estar registrado como usuario");
        }
    }
    
    
}
