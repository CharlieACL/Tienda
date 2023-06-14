
package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;


public interface CategoriaService 
{
    // Método para consultar las categoria. El parametro define si se muestran solo las activas o todas
    public List<Categoria> getCategorias(boolean activos);
    
}
