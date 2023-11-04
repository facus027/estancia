/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.controladores;

import com.example.estancia.entidades.Cliente;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.servicios.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControl {
    
    @Autowired
    private ClienteServicio cs;
    
    @GetMapping("/registrar")
    public String registrar(){
        return "clien_form";
    }
    
    @PostMapping("/registro/{id}")
    public String registro(@RequestParam String nombre,@RequestParam String calle,
                           @RequestParam Integer numero,@RequestParam String codPostal,
                          @RequestParam String ciudad,@RequestParam String pais,
                          @RequestParam String id,ModelMap modelo){
        
        try {
            cs.create(nombre, calle, numero, codPostal, ciudad, pais, id);
            modelo.put("exito","El cliene fue cargado con exito!");
            return "estancia_form";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "clien_form";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@RequestParam String id,ModelMap modelo){
        
        modelo.put("cliente",cs.getList());
        return "clien_modi.html";
        
    }
    
    @PostMapping("/modifico/{id}")
    public String modifico(@RequestParam String id,String nombre,String calle,Integer numero, String codPostal,
                          String ciudad,String pais,String email,ModelMap modelo){
        
        try {
            cs.update(nombre, calle, numero, codPostal, ciudad, pais, id, email);
            modelo.put("exito","El cliente fue cargado con exito!");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "clien_form.html";
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String supri(@RequestParam String id,ModelMap modelo){
        
        cs.suprById(id);
        modelo.put("exito","El cliente fue eliminado con exito");
        return "index.html";
        
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Cliente> clientes=cs.getList();
        modelo.put("clientes", clientes);
        return "clien_list.html";
    }
    
    
}
