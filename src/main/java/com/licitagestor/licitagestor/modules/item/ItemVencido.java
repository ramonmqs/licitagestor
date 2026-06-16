package com.licitagestor.licitagestor.modules.item;

import com.licitagestor.licitagestor.modules.edital.Edital;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "itens_vencidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVencido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento: Vários itens pertencem a 1 Edital ganho
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edital_id", nullable = false)
    private Edital edital;

    @Column(name = "descricao_item", columnDefinition = "TEXT", nullable = false)
    private String descricaoItem;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

    // O banco calcula isso sozinho (quantidade * valor_unitario), então o JPA só lê
    @Column(name = "valor_total", precision = 12, scale = 2, insertable = false, updatable = false)
    private BigDecimal valorTotal;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}