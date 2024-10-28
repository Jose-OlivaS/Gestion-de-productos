package com.GestionProductos.springboot.controller;

import com.GestionProductos.springboot.exception.exceptionRecurse;
import com.GestionProductos.springboot.model.Producto;
import com.GestionProductos.springboot.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        Producto product = productoRepository.findById(id)
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable Long id, @RequestBody Producto productDetails) {
        Producto product = productoRepository.findById(id)
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        Producto updatedProduct = productoRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Producto product = productoRepository.findById(id)
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productoRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}