/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.controladores;

import com.example.estancia.entidades.Estancia;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.servicios.EstanciaServicio;
import java.util.Date;
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
@RequestMapping("/estancia")
public class EstanciaControl {
    
    @Autowired
    private EstanciaServicio es;
    
    @GetMapping("/registrar")
    public String registrar(){
        return "estancia_form.html";
    }
    
    @PostMapping("/registro/{id}")
    public String registro(@RequestParam String id,@RequestParam String huesped,
                           @RequestParam Date fechaDesde,@RequestParam Date fechaHasta,
                           ModelMap modelo){
        
        try {
            es.create(id, huesped, fechaDesde, fechaHasta);
            modelo.put("exito","La estancia fue creada con exito");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "estancia,form.html";
        }
    }
    
    @GetMapping("modificar/{id}")
    public String modificar(@RequestParam String id,ModelMap modelo){
        
        modelo.put("estancia", es.getOne(id));
        return "estancia_modi";
        
    }
    
    @PostMapping("/modifico/{id}")
    public String modifico(@RequestParam String id,String huesped,Date fechaDesde,
                            Date fechaHasta,ModelMap modelo){
        
        try {
            es.update(id, huesped, fechaDesde, fechaHasta);
            modelo.put("exito","La estancia fur realizada con exito");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "estancia_modi.html";
        }
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Estancia>estancias=es.getList();
        modelo.addAttribute("estancias",estancias );
        return "estancia_list.html";
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String supri(@RequestParam String id,ModelMap modelo) {
        
        es.suprById(id);
        modelo.put("exito", "La estancia fue eliminada con exito!");
        return "redirect:../lista";
    }
}
