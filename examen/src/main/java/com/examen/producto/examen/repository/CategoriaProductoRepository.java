package com.examen.producto.examen.repository;

import com.examen.producto.examen.model.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {

    // Método para buscar por nombre de categoría
    Optional<CategoriaProducto> findByNombreCategoria(String nombreCategoria);
}
