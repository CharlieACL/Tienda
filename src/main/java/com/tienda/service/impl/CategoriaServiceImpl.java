
package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Contrado
@Service
public class CategoriaServiceImpl implements CategoriaService
{
    @Autowired //instancea la dependencia
    CategoriaDao categoriaDao;
    
    @Override
    @Transactional(readOnly=true) //No bloque la pagina solo es para lectura
    public List<Categoria> getCategorias(boolean activos) 
    {
        List<Categoria> categorias = categoriaDao.findAll(); //trae todas las categorias
        
        if(activos)
        {
            categorias.removeIf(x -> !x.isActivo());
        }
        
        return categorias;       
    }

   @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null); //findById retorna el id creado en la base de datos
    }

    @Override
    @Transactional //Transactional deben llevar los metodos que modifican 
    public void save(Categoria categoria) {
        categoriaDao.save(categoria); //save modifica si el id ya existe, y si no lo toma como nuevo
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }    
}
