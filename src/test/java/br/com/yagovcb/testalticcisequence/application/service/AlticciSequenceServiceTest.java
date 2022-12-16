package br.com.yagovcb.testalticcisequence.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.yagovcb.testalticcisequence.application.dto.AlticciResponseDTO;
import br.com.yagovcb.testalticcisequence.application.dto.CacheResponseDTO;
import br.com.yagovcb.testalticcisequence.config.cache.CacheMemorizationManager;

import java.math.BigInteger;
import java.util.Objects;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AlticciSequenceService.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("AlticciSequenceService - Classe de teste unitario")
class AlticciSequenceServiceTest {
    @Autowired
    private AlticciSequenceService alticciSequenceService;

    @MockBean
    private CacheMemorizationManager cacheMemorizationManager;


    /**
     * Method under test: {@link AlticciSequenceService#calculateAlticciSequenceIndex(Long)}
     */
    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta recuperar o indice da sequencia conforme valor passado")
    void testCalculateAlticciSequenceIndex() {
        ResponseEntity<AlticciResponseDTO> actualCalculateAlticciSequenceIndexResult = alticciSequenceService
                .calculateAlticciSequenceIndex(3L);
        assertTrue(actualCalculateAlticciSequenceIndexResult.hasBody());
        assertEquals(1, actualCalculateAlticciSequenceIndexResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualCalculateAlticciSequenceIndexResult.getStatusCode());
        assertEquals("1", Objects.requireNonNull(actualCalculateAlticciSequenceIndexResult.getBody()).getResult().toString());
    }

    /**
     * Method under test: {@link AlticciSequenceService#calculateAlticciSequenceIndexMemoization(Long)}
     */
    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta recuperar o indice da sequencia conforme valor passado, considerando memorização")
    void testCalculateAlticciSequenceIndexMemoization() {
        BigInteger valueOfResult = BigInteger.valueOf(42L);
        when(cacheMemorizationManager.get(any())).thenReturn(valueOfResult);
        ResponseEntity<AlticciResponseDTO> actualCalculateAlticciSequenceIndexMemoizationResult = alticciSequenceService.calculateAlticciSequenceIndexMemoization(1L);
        assertTrue(actualCalculateAlticciSequenceIndexMemoizationResult.hasBody());
        assertEquals(1, actualCalculateAlticciSequenceIndexMemoizationResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualCalculateAlticciSequenceIndexMemoizationResult.getStatusCode());
        BigInteger result = Objects.requireNonNull(actualCalculateAlticciSequenceIndexMemoizationResult.getBody()).getResult();
        assertSame(valueOfResult, result);
        assertEquals("42", result.toString());
        verify(cacheMemorizationManager).get(any());
    }

    /**
     * Method under test: {@link AlticciSequenceService#checkSequenceCache()}
     */
    @Test
    @Order(3)
    @DisplayName("Teste de serviço que tenta recuperar o sequencia que esta em cache")
    void testCheckSequenceCache() {
        when(cacheMemorizationManager.checkCacheStr()).thenReturn("Check Cache Str");
        ResponseEntity<CacheResponseDTO> actualCheckSequenceCacheResult = alticciSequenceService.checkSequenceCache();
        assertTrue(actualCheckSequenceCacheResult.hasBody());
        assertEquals(1, actualCheckSequenceCacheResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualCheckSequenceCacheResult.getStatusCode());
        assertEquals("Check Cache Str", Objects.requireNonNull(actualCheckSequenceCacheResult.getBody()).getCache());
        verify(cacheMemorizationManager).checkCacheStr();
    }

    /**
     * Method under test: {@link AlticciSequenceService#deleteSequenceCache()}
     */
    @Test
    @Order(4)
    @DisplayName("Teste de serviço que tenta deletar o sequencia que esta em cache ")
    void testDeleteSequenceCache() {
        when(cacheMemorizationManager.clearCache()).thenReturn("Clear Cache");
        ResponseEntity<CacheResponseDTO> actualDeleteSequenceCacheResult = alticciSequenceService.deleteSequenceCache();
        assertTrue(actualDeleteSequenceCacheResult.hasBody());
        assertEquals(1, actualDeleteSequenceCacheResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualDeleteSequenceCacheResult.getStatusCode());
        assertEquals("Clear Cache", Objects.requireNonNull(actualDeleteSequenceCacheResult.getBody()).getCache());
        verify(cacheMemorizationManager).clearCache();
    }

    /**
     * Method under test: {@link AlticciSequenceService#deleteSequenceCache()}
     */
    @Test
    @Order(5)
    @DisplayName("Teste de serviço que tenta simular o erro NumberFormatException")
    void testDeleteSequenceCache_NumberFormatException() {
        when(cacheMemorizationManager.clearCache()).thenThrow(new NumberFormatException());
        assertThrows(NumberFormatException.class, () -> alticciSequenceService.deleteSequenceCache());
        verify(cacheMemorizationManager).clearCache();
    }
}

