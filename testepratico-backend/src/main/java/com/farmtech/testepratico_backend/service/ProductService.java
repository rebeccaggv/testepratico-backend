package com.farmtech.testepratico_backend.service;

import com.farmtech.testepratico_backend.model.Product;
import com.farmtech.testepratico_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // pq é a classe que contém a lógica de negócio
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // listar todos
    public List<Product> listAll() {
        return repository.findAll();
    }

    // salvar
    public Product save(Product product) {
        // verifica se o nome já existe, antes de salvar
        if (repository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Já existe um produto com este nome: " + product.getName());
        }
        return repository.save(product);
    }

    // buscar por ID
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    // deletar
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    // atualizar
    public Product update(Long id, Product productAtualizado) {
        if (!repository.existsById(id)) {
            return null; // se não achar o produto, retorna null
        }
        // garante que o ID do objeto seja o mesmo da URL
        productAtualizado.setId(id);
        return repository.save(productAtualizado);
    }
}