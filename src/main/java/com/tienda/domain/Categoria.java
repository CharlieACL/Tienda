
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

//Las anotaciones deben estar pegadas o unidas a las public class
@Data //Etiqueta de la dependencia de lombok crea los set y los get de los atributos
@Entity //Hay que especificar que es una entidad 
@Table(name="categoria") //nombre de la tabla creada en sql
public class Categoria implements Serializable 
{
    private static final long serialVersionUID = 1L; //Debe estar en todas las entidades que agreguemos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_categoria")
    private Long idCategoria; //Lo transforma en id_categoria en la base de datos
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    @OneToMany
    @JoinColumn(name="id_categoria")
    private List<Producto> productos;
    
    public Categoria() {
    }

    public Categoria(String descripcion, String rutaImagen, boolean activo) 
    {
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
        
    }   
}
