package com.farmtech.testepratico_backend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // para ser uma TABELA no BD, chamada products
@Table(name = "products")
public class Product {

    @Id // ID único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // p gerar o ID automático
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    @Column(unique = true) // valor único p não ter produtos com o mesmo nome
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do produto é obrigatório") // salva no banco como texto, obrigatório
    private ProductStatus status;

    @Embedded // aqui entra a classe do Fazendeiro!
    @NotNull(message = "Os dados do fazendeiro são obrigatórios") //obrigatório
    @Valid //p funcionar as mensagens da classe Farmer
    private Farmer farmer;

    @CreationTimestamp // p preencher a data de criação sozinho
    private LocalDateTime createdAt;

    @UpdateTimestamp // p atualizar a data de edição sozinho
    private LocalDateTime updatedAt;

    // construtor vazio
    public Product() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }

    public Farmer getFarmer() { return farmer; }
    public void setFarmer(Farmer farmer) { this.farmer = farmer; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}