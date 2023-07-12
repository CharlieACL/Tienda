
package com.tienda.service.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Contrado
@Service
public class ProductoServiceImpl implements ProductoService
{
    @Autowired //instancea la dependencia
    ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly=true) //No bloque la pagina solo es para lectura
    public List<Producto> getProductos(boolean activos) 
    {
        List<Producto> productos = productoDao.findAll(); //trae todas las productos
        
        if(activos)
        {
            productos.removeIf(x -> !x.isActivo()); //Renueve elementos de una lista que cumplan con la caracteristica definida
        }
        
        return productos;       
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null); //findById retorna el id creado en la base de datos
    }

    @Override
    @Transactional //Transactional deben llevar los metodos que modifican 
    public void save(Producto producto) {
        productoDao.save(producto); //save modifica si el id ya existe, y si no lo toma como nuevo
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }    
    
    // Lista de productos con precio entre ordendados por descripción ConsultaAmpliada
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
        return productoDao.metodoJPQL(precioInf, precioSup);
    }
    
    @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoNativo(double precioInf, double precioSup) {
        return productoDao.metodoNativo(precioInf, precioSup);
    }
}
