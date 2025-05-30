package com.examen.producto.examen.controller;

import com.examen.producto.examen.model.CategoriaProducto;
import com.examen.producto.examen.service.CategoriaProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    public CategoriaProductoController(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProducto>> getAllCategorias() {
        List<CategoriaProducto> categorias = categoriaProductoService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> getCategoriaById(@PathVariable Integer id) {
        CategoriaProducto categoria = categoriaProductoService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaProducto> createCategoria(@RequestBody CategoriaProducto categoriaProducto) {
        CategoriaProducto nuevaCategoria = categoriaProductoService.create(categoriaProducto);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProducto> updateCategoria(@PathVariable Integer id, @RequestBody CategoriaProducto categoriaProducto) {
        CategoriaProducto categoriaActualizada = categoriaProductoService.update(id, categoriaProducto);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriaProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

