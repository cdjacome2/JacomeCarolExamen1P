package com.examen.producto.examen.service;

import com.examen.producto.examen.exception.CreateEntityException;
import com.examen.producto.examen.exception.ResourceNotFoundException;
import com.examen.producto.examen.exception.UpdateEntityException;
import com.examen.producto.examen.model.Producto;
import com.examen.producto.examen.repository.ProductoRepository;
import com.examen.producto.examen.repository.CategoriaProductoRepository;
import com.examen.producto.examen.model.CategoriaProducto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaProductoRepository categoriaProductoRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaProductoRepository categoriaProductoRepository) {
        this.productoRepository = productoRepository;
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    @Transactional(readOnly = true)
    public Producto findById(Integer idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "idProducto", idProducto));
    }

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional
    public Producto create(Producto producto, Integer idCategoria) {
        try {
            CategoriaProducto categoria = categoriaProductoRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "idCategoria", idCategoria));
            producto.setCategoriaProducto(categoria);
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new CreateEntityException("Producto", e.getMessage());
        }
    }

    @Transactional
    public Producto updateEstadoProducto(Integer idProducto, String nuevoEstado) {
        try {
            Producto producto = findById(idProducto);
            producto.setEstadoProducto(nuevoEstado);
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new UpdateEntityException("Producto", e.getMessage());
        }
    }

    @Transactional
    public Producto aumentarStock(Integer idProducto, Integer cantidad, Double precioCompra) {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a aumentar debe ser mayor a cero");
        }
        if (precioCompra == null || precioCompra <= 0) {
            throw new IllegalArgumentException("El precio de compra debe ser mayor a cero");
        }

        try {
            Producto producto = findById(idProducto);
            producto.setStockActual(producto.getStockActual() + cantidad);
            double nuevoPrecioVenta = precioCompra * 1.25;
            producto.setPrecioVenta(java.math.BigDecimal.valueOf(nuevoPrecioVenta));
            producto.setEstadoProducto("Activo");

            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new UpdateEntityException("Producto", e.getMessage());
        }
    }

    @Transactional
    public Producto disminuirStock(Integer idProducto, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a disminuir debe ser mayor a cero");
        }

        try {
            Producto producto = findById(idProducto);
            int stockActual = producto.getStockActual();
            if (stockActual < cantidad) {
                throw new IllegalArgumentException("No hay stock suficiente para disminuir");
            }
            producto.setStockActual(stockActual - cantidad);
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new UpdateEntityException("Producto", e.getMessage());
        }
    }
}
