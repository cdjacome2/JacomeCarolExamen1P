package com.examen.producto.examen.controller;

import com.examen.producto.examen.model.Producto;
import com.examen.producto.examen.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto,
                                               @RequestParam Integer idCategoria) {
    Producto nuevoProducto = productoService.create(producto, idCategoria);
    return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Producto> updateEstadoProducto(@PathVariable Integer id, @RequestBody String nuevoEstado) {
        Producto productoActualizado = productoService.updateEstadoProducto(id, nuevoEstado);
        return ResponseEntity.ok(productoActualizado);
    }

    @PutMapping("/{id}/aumentar-stock")
    public ResponseEntity<Producto> aumentarStock(@PathVariable Integer id,
                                                  @RequestParam Integer cantidad,
                                                  @RequestParam Double precioCompra) {
        Producto productoActualizado = productoService.aumentarStock(id, cantidad, precioCompra);
        return ResponseEntity.ok(productoActualizado);
    }

    @PutMapping("/{id}/disminuir-stock")
    public ResponseEntity<Producto> disminuirStock(@PathVariable Integer id,
                                                  @RequestParam Integer cantidad) {
        Producto productoActualizado = productoService.disminuirStock(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }
}