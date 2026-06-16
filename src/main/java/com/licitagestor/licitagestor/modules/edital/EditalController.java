package com.licitagestor.licitagestor.modules.edital;

import com.licitagestor.licitagestor.modules.edital.dto.EditalCaptacaoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editais") // O endereço base na internet para acessar essa rotina
public class EditalController {

    private final EditalService editalService;

    public EditalController(EditalService editalService) {
        this.editalService = editalService;
    }

    @PostMapping
    public ResponseEntity<Edital> cadastrarEdital(@RequestBody @Valid EditalCaptacaoDTO dto) {
        Edital novoEdital = editalService.cadastrar(dto);

        // Retorna status 201 (Created) e o objeto salvo com o ID gerado pelo banco
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEdital);
    }
}