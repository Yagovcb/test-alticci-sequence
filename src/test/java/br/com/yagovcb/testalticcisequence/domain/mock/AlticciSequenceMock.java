package br.com.yagovcb.testalticcisequence.domain.mock;

import br.com.yagovcb.testalticcisequence.application.dto.AlticciResponseDTO;
import br.com.yagovcb.testalticcisequence.application.dto.CacheResponseDTO;
import br.com.yagovcb.testalticcisequence.utils.TimerUtils;

import java.math.BigInteger;

public class AlticciSequenceMock {

    public static AlticciResponseDTO alticciResponseDTO(){
        return AlticciResponseDTO.builder()
                .strProcessingTime(TimerUtils.timeBreakFormat())
                .result(BigInteger.valueOf(3))
                .build();
    }

    public static CacheResponseDTO cacheResponseDTO(){
        return CacheResponseDTO.builder().build();
    }
}
