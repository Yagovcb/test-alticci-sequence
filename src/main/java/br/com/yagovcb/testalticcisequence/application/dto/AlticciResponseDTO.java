package br.com.yagovcb.testalticcisequence.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlticciResponseDTO {

    private String strProcessingTime;
    private BigInteger result;
}
