package com.ideasexpress1.springdatajpa1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ideasexpress1.springdatajpa1.Models.Repositiorio.VentaRepository;

@Controller
public class VentaController {
    
    @Autowired
    VentaRepository ventaRepository;

    @GetMapping(value = "/Venta")
    public String listarVentas(Model model){

        model.addAttribute("ventas", ventaRepository.findAll());

        return "Venta";
    }

    @GetMapping("/Factura")
    public String factura(Model model){
        model.addAttribute("titulo", "Factura de venta");
        model.addAttribute("ventas", ventaRepository.findAll());

        return "Factura";
    }
}
