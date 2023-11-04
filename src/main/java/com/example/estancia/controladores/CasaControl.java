/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.controladores;

import com.example.estancia.entidades.Casa;
import com.example.estancia.enums.TipoVi;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.servicios.CasaServicio;
import com.example.estancia.servicios.ComentarioServicio;
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
@RequestMapping("/casa")
public class CasaControl {
    
    @Autowired
    private CasaServicio casaServ;
    @Autowired
    private ComentarioServicio comenServ;
    
    
    
    
    @GetMapping("/registrAR/{id}")
    public String registro(){
        
        return "casa_form.hml";
    }
    
    @PostMapping("/regisro/{id}")
    public String registro(@RequestParam String id,@RequestParam String calle,@RequestParam Integer numero,
                           @RequestParam String codPostal,@RequestParam String ciudad,@RequestParam String pais,
                           @RequestParam Date fechaDesde,@RequestParam Date fechaHasta,@RequestParam Integer minDias,
                           @RequestParam Integer maxDias,@RequestParam Double precio,@RequestParam TipoVi tipoVivienda,
                           ModelMap modelo){
        
        try {
            casaServ.create(calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, id);
            modelo.put("exito","La casa fue registrada con exito");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "casa_form.html";
        }
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@RequestParam String id,ModelMap modelo){
        
        modelo.put("casa",casaServ.getOne(id));
        return "casa_modi.html";
    }
    
    @PostMapping("/modifico/{id}")
    public String modifico(@RequestParam String id,String calle,Integer numero,String codPostal,String ciudad,String pais,
                        Date fechaDesde,Date fechaHasta,Integer minDias,Integer maxDias,Double precio,
                        TipoVi tipoVivienda,ModelMap modelo){
        
        try {
            casaServ.update(calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, id);
            modelo.put("exito","La casa fue modificada con exito");
            return "index.hml";
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "casa_modi.html";
        }
        
    }
    
    
            
    
    
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Casa> casas=casaServ.getList();
        modelo.addAttribute("casas", casas);
        return "casa_list.html";
        
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String supri(@RequestParam String id, ModelMap modelo){
        
        casaServ.suprById(id);
        modelo.put("exito","La casa fue eliminada con exito!");
        return "redirect:../lista";
        
    }
      
}
