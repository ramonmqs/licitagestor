package com.licitagestor.licitagestor.modules.prazo;

import com.licitagestor.licitagestor.modules.edital.Edital;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prazos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento: Vários prazos/diligências podem pertencer a 1 único Edital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edital_id", nullable = false)
    private Edital edital;

    @Column(nullable = false, length = 300)
    private String descricao;

    @Column(name = "prazo_ate", nullable = false)
    private LocalDateTime prazoAte;

    @Column(nullable = false)
    private Boolean concluido = false;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}