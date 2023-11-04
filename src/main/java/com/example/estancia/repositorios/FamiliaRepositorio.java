/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.repositorios;

import com.example.estancia.entidades.Familia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, String>{
    
    @Query("SELECT f from Familia f WHERE f.nombre LIKE %:nombre%")
    public Familia searchName (@Param("nombre") String nombre);
    
    @Query("SELECT f from Familia f WHERE f.email LIKE %:email%")
    public Familia searchEmail (@Param ("email") String email);
    
     @Query("SELECT f FROM Familia f WHERE p.numHijos BETWEEN :minimo AND :maximo")
    public List<Familia> filterNumHijos (Integer minimo,Integer maximo);
    
     @Query("SELECT f FROM Familia f WHERE p.edadMax <= :edad AND p.edadMin >= :edad")
     public List<Familia> rangeEdad (Integer edad);
    
    
}
