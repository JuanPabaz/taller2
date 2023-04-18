package com.ideasexpress1.springdatajpa1.Models.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.ideasexpress1.springdatajpa1.Models.Entity.Usuario;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.UserRepository;

public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UserRepository aux;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = aux.findByEmail(email);

        if(usuario==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new DetalleUsuario(usuario);

    }

    
    
    
}
