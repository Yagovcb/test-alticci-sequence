package br.com.yagovcb.testalticcisequence.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Resposta Cache",
        value = "Resposta Cache",
        description = "Entidade de resposta que demonstra qual o conteúdo salvo em cache."
)
public class CacheResponseDTO {

    @ApiModelProperty(value = "Conteúdo salvo em cache.",
            example = "[ 0 - 0 ]\\n[ 1 - 0 ]\\n[ 2 - 0 ]\\n[ 3 - 0 ]\\n[ 4 - 0 ]\\n[ 5 - 0 ]\\n[ 6 - 0 ]\\n[ 7 - 0 ]\\n[ 8 - 0 ]\\n[ 9 - 0 ]\\n[ 10 - 0 ]\\n[ 11 - 0 ]",
            required = true)
    private String cache;
}
