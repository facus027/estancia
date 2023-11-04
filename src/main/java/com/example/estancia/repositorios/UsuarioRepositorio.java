/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.repositorios;

import com.example.estancia.entidades.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail (@Param("email") String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.fechaBaja <= :fechaBaja")
    public List<Usuario> searchDateBaja (@Param("fechaBaja") Date fechaBaja);
    
}
