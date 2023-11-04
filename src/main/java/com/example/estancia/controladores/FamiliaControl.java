/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.controladores;

import com.example.estancia.entidades.Familia;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.servicios.FamiliaServicio;
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
@RequestMapping("/familia")
public class FamiliaControl {
    
    @Autowired
    private FamiliaServicio familyServ;
    
    @GetMapping("/registrar")
    public String registro(){
        return "family_form.html";
    }
    
    @PostMapping("/registro/{id}")
    public String registro(@RequestParam String nombre,@RequestParam Integer edadMin,
                           @RequestParam Integer edadMax,@RequestParam Integer numHijos,
                           @RequestParam String id,ModelMap modelo){
        
        try {
            familyServ.create(nombre, edadMin, edadMax, numHijos, id);
            modelo.put("exito", "familia creada con exito!");
            return "casa_form.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "family_form.html";
        }
    }
    
    @GetMapping("/lista")
    public String lista(ModelMap modelo){
        
        List<Familia> familias=familyServ.getList();
        modelo.addAttribute("familias", familias);
        return "family_list";
        
    }
    
    @GetMapping("modificar/{id}")
    public String modificar(@RequestParam String id,ModelMap modelo){
        
        modelo.put("familia", familyServ.getOne(id));
        return "family_modi";
        
    }
    
    @PostMapping("/modifico/{id}")
    public String modifico(@RequestParam String id,String nombre,Integer edadMin,
                           Integer edadMax,Integer numHijos,String email,ModelMap modelo){
        try {
            familyServ.update(nombre, edadMin, edadMax, numHijos, email, email);
             modelo.put("exito", "La familia fue modificada con exito!");
             return "index.html";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "family_modi";
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String suprimir(@RequestParam String id,ModelMap modelo){
        
        familyServ.suprById(id);
        modelo.put("exito", "La familia fue eliminada con exito!");
        return "redirect:../lista";
    }
    
}
