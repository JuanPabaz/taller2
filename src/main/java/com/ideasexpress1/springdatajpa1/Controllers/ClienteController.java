package com.ideasexpress1.springdatajpa1.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ideasexpress1.springdatajpa1.Models.DAO.IClienteDao;
import com.ideasexpress1.springdatajpa1.Models.Entity.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    
    @Autowired
    private IClienteDao clienteDao;

    @GetMapping("/listar")
    public String Listar(Model model){

        model.addAttribute("titulo", "Listado de clientes ");
        model.addAttribute("clientes", clienteDao.findAll());

        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model){

        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "Formulario de clientes");
        model.addAttribute("boton", "Crear cliente");
        model.addAttribute("cliente", cliente);

        return "form";
    }

    //@RequestMapping(value="/form", method= RequestMethod.POST)
    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult resultado, SessionStatus status, Model model){

        if(resultado.hasErrors()){
            model.addAttribute("titulo", "Formulario de clientes");
            model.addAttribute("boton", "Crear cliente");
            model.addAttribute("cliente", cliente);
            return "form";
        }
        clienteDao.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model){

        Cliente cliente = new Cliente();

        if(id>0){
            cliente= clienteDao.findOne(id);
        }else{
            return "redirect:/listar";
        }

        model.addAttribute("titulo", "Editar clientes");
        model.addAttribute("boton", "editar clientes");
        model.addAttribute("cliente", cliente);

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){

        if(id>0){
            clienteDao.delete(id);
        }

        return "redirect:/listar";
    }

}
