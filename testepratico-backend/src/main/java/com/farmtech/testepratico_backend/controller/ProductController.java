package com.farmtech.testepratico_backend.controller;

import com.farmtech.testepratico_backend.model.Product;
import com.farmtech.testepratico_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController // é o controller pq esta classe recebe requisições da web
@RequestMapping("/products") // endereço base
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // CRIAR
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        // vai reetornar status 201 (Created) em vez de 200
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    // LISTAR TODOS
    @GetMapping
    public List<Product> listAll() {
        return service.listAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok) // se achar, retorna 200 e o produto
                .orElse(ResponseEntity.notFound().build()); // se não, retorna o erro 404
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}