package com.farmtech.testepratico_backend.controller;

import com.farmtech.testepratico_backend.model.Farmer;
import com.farmtech.testepratico_backend.model.Product;
import com.farmtech.testepratico_backend.model.ProductStatus;
import com.farmtech.testepratico_backend.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // sobe a aplicação inteira para testar
@AutoConfigureMockMvc // mock que vai fazer as requisições
@Transactional // garante que o teste limpe o banco depois de rodar
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // navegador fake

    @Autowired
    private ProductRepository repository; // p salvar dados no banco antes do teste

    @Autowired
    private ObjectMapper objectMapper; // transforma o objeto de Java em JSON

    @Test
    @DisplayName("Deve retornar Erro 409 (Conflict) quando tentar criar produto com nome duplicado")
    void shouldReturnConflictWhenNameExists() throws Exception {
        // CENÁRIO
        // CRIA fazendeiro
        Farmer farmer = new Farmer();
        farmer.setDocument("123.456.789-00");
        farmer.setFarmName("Fazenda Teste");
        farmer.setState("SP");

        // CRIA e SALVA um produto no banco
        Product product = new Product();
        product.setName("Soja Comum");
        product.setStatus(ProductStatus.ACTIVE);
        product.setFarmer(farmer);

        repository.save(product); // salvou no banco real (H2)

        // AÇÃO
        // fazz um POST enviando EXATAMENTE o mesmo produto
        String jsonRequest = objectMapper.writeValueAsString(product);

        // VERIFICAÇÃO
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Regra de Negócio"))
                .andExpect(jsonPath("$.message").value("Já existe um produto com este nome: Soja Comum"));
    }
}