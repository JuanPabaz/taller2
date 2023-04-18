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

import com.ideasexpress1.springdatajpa1.Models.DAO.IProductoDao;
import com.ideasexpress1.springdatajpa1.Models.Entity.Producto;

@Controller
@SessionAttributes("producto")
public class ProductoController {

    @Autowired
    private IProductoDao productoDao;

    @GetMapping("/listarp")
    public String listarproductos(Model model){

        model.addAttribute("titulo", "Listado de productos");
        model.addAttribute("productos", productoDao.findAll());

        return "listarp";

    }

    @GetMapping("/formp")
    public String crearproducto(Model model){

        Producto producto = new Producto();

        model.addAttribute("titulo", "Formulario de productos");
        model.addAttribute("boton", "Crear productos");
        model.addAttribute("producto", producto);

        return "formp";
        
    }

    @PostMapping("/formp")
    public String guardarproducto(@Valid Producto producto,BindingResult resultado, SessionStatus status, Model model){

        if(resultado.hasErrors()){

            model.addAttribute("titulo", "Crear productos");
            model.addAttribute("producto", producto);
            return "formp";
        }else{

            productoDao.save(producto);
            status.setComplete();
            return "redirect:listarp";
        }
        

    }
    
    @GetMapping("/formp/{id}")
    public String editarproducto(@PathVariable(value = "id") Long id, Model model){

        Producto producto = new Producto();

        if(id>0){
            producto=productoDao.findOne(id);
        }else{
            return "redirect:/listarp";
        }

        model.addAttribute("titulo", "Editar productos");
        model.addAttribute("boton", "editar producto");
        model.addAttribute("producto", producto);

        return "formp";

    }

    @GetMapping("/eliminarp/{id}")
    public String eliminarproducto(@PathVariable(value = "id") Long id){

        if(id>0){
            productoDao.delete(id);
        }

        return "redirect:/listarp";

    }


}
