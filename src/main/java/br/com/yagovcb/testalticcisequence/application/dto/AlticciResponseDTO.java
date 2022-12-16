package br.com.yagovcb.testalticcisequence.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Resposta Sequencia Alticci",
        value = "Resposta Sequencia Alticci",
        description = "Entidade de resposta que demonstra qual o indice correto da sequencia Alticci."
)
public class AlticciResponseDTO {

    @ApiModelProperty(value = "Tempo de processamento que levou para obter o indice.", example = "Processing time: 78 s 685 ms 685542000 ns", required = true)
    private String processingTime;

    @ApiModelProperty(value = "Numero correspondente da sequencia Alticci que foi selecionado.", example = "1.. 2... 3", required = true)
    private BigInteger result;
}
