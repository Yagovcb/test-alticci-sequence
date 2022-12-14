package br.com.yagovcb.testalticcisequence.application.controller;

import br.com.yagovcb.testalticcisequence.application.dto.AlticciResponseDTO;
import br.com.yagovcb.testalticcisequence.application.dto.CacheResponseDTO;
import br.com.yagovcb.testalticcisequence.application.service.AlticciSequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "Recupera todos os Projetos cadastrados no sistema", response = AlticciResponseDTO.class, httpMethod = "GET")
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValue(@PathVariable("n") Long number) {
        return alticciSequenceService.calculateAlticciSequenceIndex(number);
    }

    @Cacheable("seq-memoization")
    @GetMapping(value = "/memoizationCache/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos os Projetos cadastrados no sistema", response = AlticciResponseDTO.class, httpMethod = "GET")
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValueMemoized(@PathVariable("n") Long number) {
        return alticciSequenceService.calculateAlticciSequenceIndexMemoization(number);
    }

    @GetMapping(value = "/checkMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos os Projetos cadastrados no sistema", response = CacheResponseDTO.class, httpMethod = "GET")
    public ResponseEntity<CacheResponseDTO> checkMemoCache() {
        return alticciSequenceService.checkSequenceCache();
    }

    @CrossOrigin
    @PutMapping(value = "/clearMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos os Projetos cadastrados no sistema", response = CacheResponseDTO.class, httpMethod = "PUT")
    public ResponseEntity<CacheResponseDTO> clearCache() {
        return alticciSequenceService.deleteSequenceCache();
    }
}
