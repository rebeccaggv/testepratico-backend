package com.farmtech.testepratico_backend.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable // pq é parte da classe Product
public class Farmer {

    @NotBlank(message = "O documento do fazendeiro é obrigatório")
    private String document;

    @NotBlank(message = "O nome da fazenda é obrigatório")
    private String farmName;

    @NotBlank(message = "A sigla para o estado é obrigatória")
    @Size(min = 2, max = 2, message = "A sigla do estado deve conter exatamente 2 letras (exemplo: SP)")
    private String state;

    // construtor vazio (obrigatório p JPA)
    public Farmer() {}

    // Getters e Setters
    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }

    public String getFarmName() { return farmName; }
    public void setFarmName(String farmName) { this.farmName = farmName; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}