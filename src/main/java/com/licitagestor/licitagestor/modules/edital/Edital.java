package com.licitagestor.licitagestor.modules.edital;

import com.licitagestor.licitagestor.modules.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "editais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pregao", nullable = false, length = 50)
    private String numeroPregao;

    @Column(nullable = false, length = 200)
    private String orgao;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String objeto;

    @Column(name = "data_pregao", nullable = false)
    private LocalDate dataPregao;

    @Column(nullable = false, length = 30)
    private String status = "PENDENTE";

    @Column(name = "pdf_url", length = 500)
    private String pdfUrl;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Relacionamento: Vários editais podem ser captados por 1 usuário (seu pai)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captado_por_id")
    private Usuario captadoPor;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    // Preenche as datas automaticamente antes de salvar a primeira vez
    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    // Atualiza a data automaticamente toda vez que o edital for modificado
    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}