package com.examen.producto.examen.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Integer idProducto;

    @Column(name = "NombreProducto", length = 255, nullable = false)
    private String nombreProducto;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "PrecioVenta", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "CostoCompra", precision = 10, scale = 2)
    private BigDecimal costoCompra;

    @Column(name = "StockActual", nullable = false)
    private Integer stockActual;

    @Column(name = "EstadoProducto", length = 20, nullable = false)
    private String estadoProducto;

    @ManyToOne()
    @JoinColumn(name = "IdCategoria", nullable = false) 
    private CategoriaProducto categoriaProducto;

    public Producto() {}

    public Producto(Integer idProducto, String nombreProducto, String descripcion, BigDecimal precioVenta, BigDecimal costoCompra, Integer stockActual, String estadoProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.costoCompra = costoCompra;
        this.stockActual = stockActual;
        this.estadoProducto = estadoProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(BigDecimal costoCompra) {
        this.costoCompra = costoCompra;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", costoCompra=" + costoCompra +
                ", stockActual=" + stockActual +
                ", estadoProducto='" + estadoProducto + '\'' +
                '}';
    }
}
