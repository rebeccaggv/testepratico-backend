package com.farmtech.testepratico_backend.repository;

import com.farmtech.testepratico_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // o Spring vai criar o SQL p verificar os nomes duplicados
    boolean existsByName(String name);

}