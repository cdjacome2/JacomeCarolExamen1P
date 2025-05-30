package com.examen.producto.examen.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CategoriasProducto")
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    private Integer idCategoria;

    @Column(name = "NombreCategoria", length = 100, nullable = false, unique = true)
    private String nombreCategoria;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "categoriaProducto")
    private List<Producto> productos;

    public CategoriaProducto() {}

    public CategoriaProducto(Integer idCategoria, String nombreCategoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaProducto)) return false;
        CategoriaProducto that = (CategoriaProducto) o;
        return Objects.equals(idCategoria, that.idCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }

    @Override
    public String toString() {
        return "CategoriaProducto{" +
                "idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
