package com.licitagestor.licitagestor.modules.edital;

import com.licitagestor.licitagestor.modules.edital.dto.EditalCaptacaoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditalService {

    private final EditalRepository editalRepository;

    // Injeção de dependência via construtor (Melhor prática do mercado)
    public EditalService(EditalRepository editalRepository) {
        this.editalRepository = editalRepository;
    }

    @Transactional
    public Edital cadastrar(EditalCaptacaoDTO dto) {
        // Converte o "envelope" DTO na Entidade que o banco entende
        Edital edital = new Edital();
        edital.setNumeroPregao(dto.numeroPregao());
        edital.setOrgao(dto.orgao());
        edital.setObjeto(dto.objeto());
        edital.setDataPregao(dto.dataPregao());
        edital.setStatus("PENDENTE"); // Todo edital captado pelo seu pai entra como PENDENTE

        // O Spring Data JPA faz o INSERT no PostgreSQL automaticamente aqui
        return editalRepository.save(edital);
    }
}