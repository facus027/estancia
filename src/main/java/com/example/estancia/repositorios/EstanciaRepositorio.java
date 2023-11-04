/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.repositorios;

import com.example.estancia.entidades.Estancia;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanciaRepositorio extends JpaRepository<Estancia, String> {
    
    @Query("SELECT e FROM Estancias e WHERE e.fechaHasta < :fechaHasta")
    public List<Estancia> reservaEnd (@Param("fechaHasta") Date fechaHasta);
    
    @Query("SELECT e FROM Estancias e WHERE e.huesped LIKE %:huesped%")
    public Estancia searchHuesped (@Param("huesped") String huesped);
    
}
