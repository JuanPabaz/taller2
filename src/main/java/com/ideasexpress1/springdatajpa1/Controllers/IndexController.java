package com.ideasexpress1.springdatajpa1.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ideasexpress1.springdatajpa1.Models.Entity.Usuario;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.UserRepository;

@Controller
public class IndexController {
    
    @Autowired
    private UserRepository aux;

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("titulo", "Iniciar sesion");

        return "login";

    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("titulo", "Bienvenido");

        return "index";
    }

    @GetMapping("/base")
    public String base(){
        

        return "plantilla/base";
    }

    @GetMapping("/formUsuario")
    public String usuarioregistro(Model model){
        model.addAttribute("titulo", "Formulario de registro de usuarios");
        model.addAttribute("usuario", new Usuario());

        return "formUsuario";
    }

    @PostMapping("ProcesoRegistro")
    public String ProcesoRegistro(Usuario usuario, Model model){

        model.addAttribute("titulo", "Registro exitoso");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        aux.save(usuario);

        return "RegistroExitoso";
    }

    @GetMapping("listUsuario")
    public String ListarUsuarios(Model model){

        model.addAttribute("titulo", "Lista de usuarios");
        List<Usuario> listaUsuarios = aux.findAll();
        model.addAttribute("listusuarios", listaUsuarios);
        return "Usuarios";
    }

}
