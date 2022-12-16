package br.com.yagovcb.testalticcisequence.application.service;

import br.com.yagovcb.testalticcisequence.application.dto.AlticciResponseDTO;
import br.com.yagovcb.testalticcisequence.application.dto.CacheResponseDTO;
import br.com.yagovcb.testalticcisequence.config.cache.CacheMemorizationManager;
import br.com.yagovcb.testalticcisequence.utils.AlticciSequenceUtils;
import br.com.yagovcb.testalticcisequence.utils.TimerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlticciSequenceService {

    private final CacheMemorizationManager cacheMemorizationManager;

    private Long countCacheCalls = 0L;
    private Long countMemorizedCacheCalls = 0L;

    public ResponseEntity<AlticciResponseDTO> calculateAlticciSequenceIndex(Long number) {
        log.info("AlticciSequenceService :: Starting the process of calculating the Alticci sequence");
        validateNumber(number);

        BigInteger sprResult = calculateSequence(number);

        AlticciResponseDTO responseDTO = new AlticciResponseDTO(TimerUtils.timeBreakFormat(), sprResult);
        TimerUtils.timeBreakPrint("DONE - CALLED ["+countCacheCalls+"] TIMES ");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDTO);
    }

    public ResponseEntity<AlticciResponseDTO> calculateAlticciSequenceIndexMemoization(Long number) {
        log.info("AlticciSequenceService ::Starting the Alticci sequence calculation process with memorization");
        validateNumber(number);

        BigInteger memoResult = calculateWithMemoization(number);

        AlticciResponseDTO responseDTO = new AlticciResponseDTO(TimerUtils.timeBreakFormat(), memoResult);
        TimerUtils.timeBreakPrint("> DONE - ["+countMemorizedCacheCalls+"] TIMES ");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDTO);
    }

    public ResponseEntity<CacheResponseDTO> checkSequenceCache() {
        log.info("AlticciSequenceService :: Starting verification of the cached sequence");
        CacheResponseDTO responseDTO = new CacheResponseDTO(cacheMemorizationManager.checkCacheStr());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDTO);
    }

    public ResponseEntity<CacheResponseDTO> deleteSequenceCache() {
        log.info("AlticciSequenceService ::Starting the Alticci sequence calculation process with memorization");
        CacheResponseDTO responseDTO = new CacheResponseDTO(cacheMemorizationManager.clearCache());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseDTO);
    }

    private void validateNumber(Long number) {
        log.info("AlticciSequenceService :: Starting number validation");
        if (AlticciSequenceUtils.isNumberLowerThanZero(number)) {
            throw new NumberFormatException("Invalid number:" + number);
        }
        log.info("AlticciSequenceService :: Number Validated with success");
    }

    private BigInteger calculateSequence(Long number) {
        countCacheCalls++;
        if (AlticciSequenceUtils.isNumberEqualThanZero(number)) {
            return BigInteger.valueOf(0);
        }
        if (AlticciSequenceUtils.isNumberLowerThanTwo(number)) {
            return BigInteger.valueOf(1);
        }
        return calculateSequence(number - 3).add(calculateSequence(number - 2));
    }

    private BigInteger calculateWithMemoization(Long number) {
        countMemorizedCacheCalls ++;
        BigInteger seqNumber = cacheMemorizationManager.get(number);
        if(seqNumber == null) {
            if (number < 3 || number < 2){
                seqNumber = BigInteger.valueOf(0);
            } else {
                seqNumber = calculateWithMemoization(number - 3).add(calculateWithMemoization(number - 2));
            }
            cacheMemorizationManager.save(number, seqNumber);
        }
        return seqNumber;
    }

}
