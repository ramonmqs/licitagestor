package com.licitagestor.licitagestor.modules.edital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// O Record é um "envelope" leve. As anotações garantem que seu pai não envie campos em branco.
public record EditalCaptacaoDTO(

        @NotBlank(message = "O número do pregão é obrigatório")
        String numeroPregao,

        @NotBlank(message = "O órgão é obrigatório")
        String orgao,

        @NotBlank(message = "O objeto da licitação é obrigatório")
        String objeto,

        @NotNull(message = "A data do pregão é obrigatória")
        LocalDate dataPregao
) {
}