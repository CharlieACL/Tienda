
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    
    @Autowired
    CategoriaService categoriasService;
    
    @Autowired
     private FirebaseStorageServiceImpl firebaseStorageService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        List<Producto> productos = productoService.getProductos(false);
        List<Categoria> categorias = categoriasService.getCategorias(true);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalProductos", productos.size());
        
        return "/producto/listado";    
    }
     
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }
    
    //Inserta y guarda la la url de la imagen del id que se desee
    @PostMapping("/guardar") //Post permite enviar datos de un cuerpo 
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) { //Input de la imagen        
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "producto", 
                            producto.getIdProducto()));
        }
        productoService.save(producto);//Este save es el que modifica
        return "redirect:/producto/listado";//Redirecciona al listado 
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        List<Categoria> categorias = categoriasService.getCategorias(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }
}
