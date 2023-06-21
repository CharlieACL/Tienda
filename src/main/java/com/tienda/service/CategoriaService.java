
package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;


public interface CategoriaService 
{
    // Método para consultar las categoria. El parametro define si se muestran solo las activas o todas
    public List<Categoria> getCategorias(boolean activos);
    
    //Método para obtener una categoria mediante su id
    public Categoria getCategoria(Categoria categoria);
    
    //Método que nos servira para insertar cuando el IdCategoria está vacio(valor 0 o null)
    //Tambien nos sirve para modificar cuando el idCategoria no está vacio
    public void save(Categoria categoria);
    
    //Método para eliminar registro por su Id
    public void delete(Categoria categoria);
    
}
