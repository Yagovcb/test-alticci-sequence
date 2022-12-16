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
    @ApiOperation(value = "Returns the Alticci sequence number", response = AlticciResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the sequence number on success", response = AlticciResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns error if the number is not equal to the expected one", response = NumberFormatException.class)
    })
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValue(@PathVariable("n") Long number) {
        log.info("AlticciSequenceController :: Starting the search for the correct index of the sequence...");
        return alticciSequenceService.calculateAlticciSequenceIndex(number);
    }

    @Cacheable("seq-memoization")
    @GetMapping(value = "/memoizationCache/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns the Alticci sequence number with memorization", response = AlticciResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the sequence number in the cache, in case of success", response = AlticciResponseDTO.class),
            @ApiResponse(code = 400, message = "Returns error if the number is not equal to the expected one", response = NumberFormatException.class)

    })
    public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValueMemoized(@PathVariable("n") Long number) {
        log.info("AlticciSequenceController :: Starting the search for the correct index of the sequence, considering memorization...");
        return alticciSequenceService.calculateAlticciSequenceIndexMemoization(number);
    }

    @GetMapping(value = "/checkMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve the list of cached indexes", response = CacheResponseDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve the list of indexes that have been cached so far", response = CacheResponseDTO.class)
    })
    public ResponseEntity<CacheResponseDTO> checkMemoCache() {
        log.info("AlticciSequenceController :: Starting the search for the list of indexes that have been cached so far...");
        return alticciSequenceService.checkSequenceCache();
    }

    @PutMapping(value = "/clearMemoCache", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Clear cache list", response = CacheResponseDTO.class, httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a message if the operation is successful", response = CacheResponseDTO.class)
    })
    public ResponseEntity<CacheResponseDTO> clearCache() {
        log.info("AlticciSequenceController :: Clearing the list of indices that have been cached so far...");
        return alticciSequenceService.deleteSequenceCache();
    }
}