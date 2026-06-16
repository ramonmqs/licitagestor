-- Tabela de usuários (pai = CAPTADOR, você = GESTOR)
CREATE TABLE usuarios (
                          id          BIGSERIAL PRIMARY KEY,
                          nome        VARCHAR(100) NOT NULL,
                          email       VARCHAR(150) NOT NULL UNIQUE,
                          senha_hash  VARCHAR(255) NOT NULL,
                          perfil      VARCHAR(20)  NOT NULL CHECK (perfil IN ('CAPTADOR', 'GESTOR')),
                          ativo       BOOLEAN      NOT NULL DEFAULT TRUE,
                          criado_em   TIMESTAMP    NOT NULL DEFAULT NOW()
);

-- Tabela principal de editais
CREATE TABLE editais (
                         id              BIGSERIAL PRIMARY KEY,
                         numero_pregao   VARCHAR(50)  NOT NULL,
                         orgao           VARCHAR(200) NOT NULL,
                         objeto          TEXT         NOT NULL,
                         data_pregao     DATE         NOT NULL,
                         status          VARCHAR(30)  NOT NULL DEFAULT 'PENDENTE'
                             CHECK (status IN ('PENDENTE','EM_ANDAMENTO','SUSPENSO',
                                               'AGUARDANDO_DOC','VENCIDO','PERDIDO','CANCELADO')),
                         pdf_url         VARCHAR(500),
                         observacoes     TEXT,
                         captado_por_id  BIGINT REFERENCES usuarios(id),
                         criado_em       TIMESTAMP NOT NULL DEFAULT NOW(),
                         atualizado_em   TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Tabela de prazos críticos (suspensões, diligências)
CREATE TABLE prazos (
                        id          BIGSERIAL PRIMARY KEY,
                        edital_id   BIGINT    NOT NULL REFERENCES editais(id) ON DELETE CASCADE,
                        descricao   VARCHAR(300) NOT NULL,
                        prazo_ate   TIMESTAMP NOT NULL,
                        concluido   BOOLEAN   NOT NULL DEFAULT FALSE,
                        criado_em   TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Tabela de itens vencidos (histórico de sucesso)
CREATE TABLE itens_vencidos (
                                id              BIGSERIAL PRIMARY KEY,
                                edital_id       BIGINT         NOT NULL REFERENCES editais(id) ON DELETE CASCADE,
                                descricao_item  TEXT           NOT NULL,
                                quantidade      INTEGER        NOT NULL,
                                valor_unitario  NUMERIC(12,2)  NOT NULL,
                                valor_total     NUMERIC(12,2)  GENERATED ALWAYS AS (quantidade * valor_unitario) STORED,
                                criado_em       TIMESTAMP      NOT NULL DEFAULT NOW()
);

-- Índices para performance
CREATE INDEX idx_editais_status    ON editais(status);
CREATE INDEX idx_prazos_prazo_ate  ON prazos(prazo_ate) WHERE concluido = FALSE;