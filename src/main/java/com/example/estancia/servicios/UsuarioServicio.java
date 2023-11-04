/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.estancia.servicios;

import com.example.estancia.entidades.Usuario;
import com.example.estancia.enums.Rol;
import com.example.estancia.excepciones.MiException;
import com.example.estancia.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepo;
    
   @Transactional
    public void createUser(String alias, String email, String clave, String clave2) throws MiException {

        validar(alias, email, clave, clave2);
        
        Date date=new Date();

        Usuario usuario = new Usuario();

        usuario.setAlias(alias);
        usuario.setEmail(email);
        usuario.setFechaAlta(date);
        usuario.setClave(new BCryptPasswordEncoder().encode(clave));
        usuario.setFechaBaja(null);
        usuario.setRol(Rol.USER);
     
        usuarioRepo.save(usuario);
    }
    
    public Optional<Usuario> getOne(String id){
        return usuarioRepo.findById(id);
    }
    
    public List<Usuario> getList(){
        List<Usuario> usuarioList=new ArrayList();
        usuarioList=usuarioRepo.findAll();
        return usuarioList;
    }
    
    public Usuario getEmail(String email){
         return usuarioRepo.buscarPorEmail(email);
    }
    
    //Falta update, updateClave y dar de baja Date;
    
     @Transactional
    public void cambiarRol(String id){
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
    	
    	if(respuesta.isPresent()) {
    		
    		Usuario usuario = respuesta.get();
    		
    		if(usuario.getRol().equals(Rol.USER)) {
    			
    		usuario.setRol(Rol.ADMIN);
    		
    		}else if(usuario.getRol().equals(Rol.ADMIN)) {
    			usuario.setRol(Rol.USER);
    		}
    	}
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepo.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getClave(), permisos);
        } else {
            return null;
        }
    
}
    @Transactional
     public void suprById(String id){
         usuarioRepo.deleteById(id);
     }
    
    
    private void validar(String alias, String email, String clave, String clave2) throws MiException {

        if (alias.isEmpty() || alias == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacio");
        }
        if (clave.isEmpty() || clave == null || clave.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }

        if (!clave.equals(clave2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }

    }
    
    
}