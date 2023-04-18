package com.ideasexpress1.springdatajpa1.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ideasexpress1.springdatajpa1.Models.Auxiliar.ProductoVenta;
import com.ideasexpress1.springdatajpa1.Models.Entity.Producto;
import com.ideasexpress1.springdatajpa1.Models.Entity.ProductoVendido;
import com.ideasexpress1.springdatajpa1.Models.Entity.Venta;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.ProductoRepository;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.ProductoVendidoRepository;
import com.ideasexpress1.springdatajpa1.Models.Repositiorio.VentaRepository;

@Controller
public class VenderController {
    
    @Autowired
    private ProductoRepository aux;
    @Autowired
    private VentaRepository aux2;
    @Autowired
    private ProductoVendidoRepository aux3;

    private ArrayList<ProductoVenta> obtenerCanasta(HttpServletRequest request){
        
        ArrayList<ProductoVenta> canasta = (ArrayList<ProductoVenta>) request.getSession().getAttribute("canasta");

        if(canasta==null){
            canasta= new ArrayList<>();
        }

        return canasta;
    }

    private void guardarCanasta(ArrayList<ProductoVenta> canasta, HttpServletRequest request){
        request.getSession().setAttribute("canasta", canasta);
    }

    @GetMapping(value = "/Vender")
    public String Vender(Model model, HttpServletRequest request){

        model.addAttribute("titulo", "Proceso de venta");
        model.addAttribute("producto", new Producto());

        float total = 0;

        ArrayList<ProductoVenta> canasta = this.obtenerCanasta(request);

        for(ProductoVenta p: canasta){
            total += p.getTotal();
        }

        model.addAttribute("total", total);
        return "Vender";
    }

    @PostMapping(value = "/Agregar")
    public String AgregarCanasta(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttributes){
        ArrayList<ProductoVenta> canasta = this.obtenerCanasta(request);
        Producto producto_codigo = aux.findBycodigo(producto.getCodigo());

        if(producto_codigo==null){
            redirectAttributes.addFlashAttribute("mensaje","El producto con el codigo" + producto.getCodigo() + "no existe").addFlashAttribute("clase", "warning");
            return "redirect:/Vender";
        }

        if(producto_codigo.sinUnidades()){
            redirectAttributes.addFlashAttribute("mensaje", "El producto esta agotado").addFlashAttribute("clase", "warning");

            return "redirect:/Vender";
        }

        boolean encontrado=false;

        for(ProductoVenta productoParaVenderAhora: canasta){
            
            if(productoParaVenderAhora.getCodigo().equals(producto_codigo.getCodigo())){
                productoParaVenderAhora.aumentarCantidad();
                encontrado=true;
                break;
            }

        }

        if(!encontrado){
            canasta.add(new ProductoVenta(producto_codigo.getId_producto(), producto_codigo.getNombre(), producto_codigo.getDescripcion(), producto_codigo.getUnidades(),producto_codigo.getPrecio(), producto_codigo.getCodigo(), 1f));

        }

        this.guardarCanasta(canasta, request);

        return "redirect:/Vender";
    }

    @PostMapping(value = "/Quitar/{indice}")
    public String eliminarDeCasta(@PathVariable int indice, HttpServletRequest request){

        ArrayList<ProductoVenta> canasta = this.obtenerCanasta(request);

        if(canasta != null && canasta.size() >0 && canasta.get(indice) != null){
            canasta.remove(indice);
            this.guardarCanasta(canasta, request);
        }

        return "redirect:/Vender";
    }

    private void vaciarCanasta(HttpServletRequest request){
        this.guardarCanasta(new ArrayList<>(), request);

    }

    @GetMapping(value = "/Vaciar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttributes){
        
        this.vaciarCanasta(request);
        redirectAttributes.addFlashAttribute("mensaje", "Venta cancelada").addFlashAttribute("clase", "info");
        return "redirect:/Vender";
    }

    @PostMapping(value = "/Finalizar")
    public String finalizarVenta(HttpServletRequest request, RedirectAttributes redirectAttributes){

        ArrayList<ProductoVenta> canasta = this.obtenerCanasta(request);

        if(canasta == null || canasta.size()<=0){
            return "redirect:/Vender";
        }

        Venta venta = aux2.save(new Venta());

        for(ProductoVenta productoParaVender:canasta){
            
            Producto producto = aux.findById(productoParaVender.getId_producto()).orElse(null);

            if(producto == null) continue;

            producto.restarUnidades(productoParaVender.getCantidad());

            aux.save(producto);

            ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getNombre(), productoParaVender.getDescripcion(), productoParaVender.getCantidad(), productoParaVender.getPrecio(),productoParaVender.getCodigo(), venta);

            System.out.println(productoVendido);

            aux3.save(productoVendido);
        }

        this.vaciarCanasta(request);

        redirectAttributes.addFlashAttribute("mensaje", "Venta realizada existosamente").addFlashAttribute("clase", "success");

        return "redirect:/Vender";
    }
}
