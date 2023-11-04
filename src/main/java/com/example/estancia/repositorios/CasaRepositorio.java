/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.repositorios;

import com.example.estancia.entidades.Casa;
import com.example.estancia.enums.TipoVi;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepositorio extends JpaRepository<Casa, String> {
    
    @Query("SELECT c FROM Casa c WHERE c.ciudad LIKE %:ciudad%")
    public List<Casa> searchCity (@Param("ciudad") String ciudad);
    
    @Query("SELECT c FROM Casa c WHERE c.pais LIKE %:pais%")
    public List<Casa> searchCountry (@Param("pais") String pais);
    
    @Query("SELECT c FROM Casa c WHERE c.estancia.huesped LIKE %:huesped%")
    public Casa searchHuesped (@Param("huesped") String huesped);
    
    @Query("SELECT c FROM Casa c WHERE c.tipoVivienda = :tipoVivienda")
    public List<Casa> searchTipoVivienda (@Param("tipoVivienda") TipoVi tipoVivienda);
    
    @Query("SELECT c FROM Casa c WHERE c.precio BETWEEN :minimo AND :maximo")
    public List<Casa> filterPrecio (Double minimo,Double maximo);
    
    @Query("SELECT c FROM Casa c WHERE c.fechaHasta <= :date AND c.fechaDesde >= :date")
    public List<Casa> searchDate (Date date);
    
    @Query("SELECT c FROM Casa c WHERE c.maxDias <= :dias AND c.minDias >= :dias")
    public List<Casa> searchAmountDay (Integer dias);
    
}
