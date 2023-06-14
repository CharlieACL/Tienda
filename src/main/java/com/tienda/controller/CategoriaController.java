
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ch4rl
 */
@Controller
@RequestMapping("/categoria")
@Slf4j //anotacion opcional
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService; 
    
    @GetMapping("/listado")
    public String page(Model model) {
        log.info("Consumo de recurso /categoria/listado");
        List<Categoria> categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        
        return "/categoria/listado";
    }
    
}
