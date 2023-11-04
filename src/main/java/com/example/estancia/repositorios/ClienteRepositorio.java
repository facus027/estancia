/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.repositorios;

import com.example.estancia.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE %:nombre%")
    public Cliente searchName (@Param("nombre") String nombre);
    
    @Query("SELECT c FROM Cliente c WHERE c.email LIKE %:email%")
    public Cliente searchEmail (@Param("email") String email);
    
}
