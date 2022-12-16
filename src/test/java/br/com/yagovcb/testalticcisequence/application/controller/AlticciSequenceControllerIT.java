package br.com.yagovcb.testalticcisequence.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.yagovcb.testalticcisequence.TestAlticciSequenceApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Objects;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(properties = {"security.basic.enabled=false"}, classes = TestAlticciSequenceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("AlticciSequenceControllerIT - Teste de integração da API de Alticci sequence")
class AlticciSequenceControllerIT {

    private static final String URI_BASE = "/api/alticci";

    @Autowired
    private MockMvc restAlticciSequenceMockMvc;


    /**
     * Method under test: {@link AlticciSequenceController#returnAlticciSequenceValue(Long)}
     */
    @Test
    @Order(1)
    @DisplayName("Teste de API tenta retornar o indice correto da sequencia Alticci")
    void getReturnAlticciSequenceValueTest() throws Exception {
        restAlticciSequenceMockMvc.perform(get(URI_BASE + "/springCache/{n}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
    }


    /**
     * Method under test: {@link AlticciSequenceController#returnAlticciSequenceValueMemoized(Long)}
     */
    @Test
    @Order(2)
    @DisplayName("Teste de API tenta retornar o indice correto da sequencia Alticci com memorização")
    void getReturnAlticciSequenceValueMemoizedTest() throws Exception {
        restAlticciSequenceMockMvc.perform(get(URI_BASE + "/memoizationCache/{n}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0));
    }


    /**
     * Method under test: {@link AlticciSequenceController#checkMemoCache()}
     */
    @Test
    @Order(3)
    @DisplayName("Teste de API tenta retornar o conteudo salvo em cache")
    void getCheckMemoCacheTest() throws Exception {
        restAlticciSequenceMockMvc.perform(get(URI_BASE + "/checkMemoCache"))
                .andExpect(status().isOk())
                .andExpect(result -> Objects.requireNonNull(result.getResponse().getContentAsString()))
        ;
    }


    /**
     * Method under test: {@link AlticciSequenceController#clearCache()}
     */
    @Test
    @Order(4)
    @DisplayName("Teste de API tenta deletar o conteudo salvo em cache")
    void putClearCacheTest() throws Exception {
        restAlticciSequenceMockMvc.perform(put(URI_BASE + "/clearMemoCache"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strCache").value("The cache has been clear"))
                .andExpect(result -> Objects.requireNonNull(result.getResponse().getContentAsString()))
        ;
    }
}

