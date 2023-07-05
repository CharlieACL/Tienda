
package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;


public interface ProductoService 
{
    // Método para consultar las categoria. El parametro define si se muestran solo las activas o todas
    public List<Producto> getProductos(boolean activos);
    
    //Método para obtener una producto mediante su id
    public Producto getProducto(Producto categoria);
    
    //Método que nos servira para insertar cuando el IdProducto está vacio(valor 0 o null)
    //Tambien nos sirve para modificar cuando el idProducto no está vacio
    public void save(Producto categoria);
    
    //Método para eliminar registro por su Id
    public void delete(Producto categoria);
    
}
