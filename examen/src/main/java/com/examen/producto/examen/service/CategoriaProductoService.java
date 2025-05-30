package com.examen.producto.examen.service;

import com.examen.producto.examen.exception.CreateEntityException;
import com.examen.producto.examen.exception.DeleteEntityException;
import com.examen.producto.examen.exception.ResourceNotFoundException;
import com.examen.producto.examen.exception.UpdateEntityException;
import com.examen.producto.examen.model.CategoriaProducto;
import com.examen.producto.examen.repository.CategoriaProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProductoService(CategoriaProductoRepository categoriaProductoRepository) {
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaProducto> findAll() {
        return categoriaProductoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoriaProducto findById(Integer idCategoria) {
        return categoriaProductoRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "idCategoria", idCategoria));
    }

    @Transactional
    public CategoriaProducto create(CategoriaProducto categoriaProducto) {
        try {
            return categoriaProductoRepository.save(categoriaProducto);
        } catch (Exception e) {
            throw new CreateEntityException("CategoriaProducto", e.getMessage());
        }
    }

    @Transactional
    public CategoriaProducto update(Integer idCategoria, CategoriaProducto categoriaProductoDetalles) {
        try {
            CategoriaProducto categoriaProducto = findById(idCategoria);
            categoriaProducto.setNombreCategoria(categoriaProductoDetalles.getNombreCategoria());
            categoriaProducto.setDescripcion(categoriaProductoDetalles.getDescripcion());
            return categoriaProductoRepository.save(categoriaProducto);
        } catch (Exception e) {
            throw new UpdateEntityException("CategoriaProducto", e.getMessage());
        }
    }

    @Transactional
    public void delete(Integer idCategoria) {
        try {
            CategoriaProducto categoriaProducto = findById(idCategoria);
            categoriaProductoRepository.delete(categoriaProducto);
        } catch (Exception e) {
            throw new DeleteEntityException("CategoriaProducto", e.getMessage());
        }
    }
}
