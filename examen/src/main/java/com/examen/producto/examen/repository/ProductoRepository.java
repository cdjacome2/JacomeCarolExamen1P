package com.examen.producto.examen.repository;

import com.examen.producto.examen.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}