package br.com.yagovcb.testalticcisequence.application.controller;

import br.com.yagovcb.testalticcisequence.application.dto.AlticciResponseDTO;
import br.com.yagovcb.testalticcisequence.application.dto.CacheResponseDTO;
import br.com.yagovcb.testalticcisequence.application.service.AlticciSequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alticci")
@RequiredArgsConstructor
@Api(
        value = "/api/alticci",
        produces = MediaType.APPLICATION_JSON_VALUE,
        tags = "API de geração da sequencia Alticci",
        description = "Reúne endpoints destinados a com a geração da sequencia Alticci sendo com memorização ou não"
)
public class AlticciSequenceController {

    private final AlticciSequenceService alticciSequenceService;

    @Cacheable("no-seq-memoization")
    @GetMapping(value = "/springCache/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna o numero da sequencia Alticci", response = AlticciResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o numero da sequencia em caso de sucesso", response = AlticciResponseDTO.class),
            @ApiResponse(code = 400, message = "Retorna erro caso o numero não igual ao esperado", response = NumberFormatException.class)
    })
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValue(@PathVariable("n") Long number) {
        log.info("AlticciSequenceController :: Iniciando a busca pelo indice correto da sequencia...");
        return alticciSequenceService.calculateAlticciSequenceIndex(number);
    }

    @Cacheable("seq-memoization")
    @GetMapping(value = "/memoizationCache/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna o numero da sequencia Alticci com memorização", response = AlticciResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o numero da sequencia em cache, em caso de sucesso", response = AlticciResponseDTO.class),
            @ApiResponse(code = 400, message = "Retorna erro caso o numero não igual ao esperado", response = NumberFormatException.class)

    })
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValueMemoized(@PathVariable("n") Long number) {
        log.info("AlticciSequenceController :: Iniciando a busca pelo indice correto da sequencia, considerando memorização...");
        return alticciSequenceService.calculateAlticciSequenceIndexMemoization(number);
    }

    @GetMapping(value = "/checkMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera a lista de indices em cache", response = CacheResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de indices de cache", response = CacheResponseDTO.class)
    })
    public ResponseEntity<CacheResponseDTO> checkMemoCache() {
        log.info("AlticciSequenceController :: Iniciando a busca pela lista de indices que foram salvos em cache até o momento...");
        return alticciSequenceService.checkSequenceCache();
    }

    @PutMapping(value = "/clearMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Limpa a lista de cache", response = CacheResponseDTO.class, httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensage se a operação for bem sucedida", response = CacheResponseDTO.class)
    })
    public ResponseEntity<CacheResponseDTO> clearCache() {
        log.info("AlticciSequenceController :: Limpando a lista de indices que foram salvos em cache até o momento...");
        return alticciSequenceService.deleteSequenceCache();
    }
}