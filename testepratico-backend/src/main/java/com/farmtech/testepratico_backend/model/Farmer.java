package com.farmtech.testepratico_backend.model;

import jakarta.persistence.Embeddable;

@Embeddable // pq é parte da classe Product
public class Farmer {

    private String document;
    private String farmName;
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