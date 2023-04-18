package com.ideasexpress1.springdatajpa1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideasexpress1.springdatajpa1.Models.DAO.IDaoVenta;
import com.ideasexpress1.springdatajpa1.Models.Entity.Venta;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.VentaRepository;

@Controller
public class VentaController {
    
    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    private IDaoVenta ventaDao;

    @GetMapping(value = "/Venta")
    public String listarVentas(Model model){

        model.addAttribute("titulo", "Listado de Ventas");
        model.addAttribute("ventas", ventaRepository.findAll());

        return "Venta";
    }

    @GetMapping("/Factura/{Id_venta}")
    public String factura(@PathVariable(value = "Id_venta") Long id, Model model){

        Venta venta = new Venta();

        if(id>0){
            venta= ventaDao.searchsale(id);
        }else{
            return "redirect:/Venta";
        }

        model.addAttribute("titulo", "Factura de venta");
        model.addAttribute("ventas", venta);

        return "Factura";
    }
}
