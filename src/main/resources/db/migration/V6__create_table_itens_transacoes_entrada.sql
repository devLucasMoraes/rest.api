CREATE TABLE IF NOT EXISTS itens_transacoes_entrada
(
    id                    BIGSERIAL PRIMARY KEY,
    materiais_id          BIGINT         NOT NULL,
    transacoes_entrada_id BIGINT         NOT NULL,
    und_com               VARCHAR(45)    NOT NULL,
    quant_com             NUMERIC(10, 2) NOT NULL,
    valor_unt_com         NUMERIC(10, 2) NOT NULL,
    valor_ipi             NUMERIC(10, 2) NOT NULL,
    obs                   VARCHAR(255),
    CONSTRAINT fk_transacoes_entrada_has_materiais_transacoes_entrada
        FOREIGN KEY (transacoes_entrada_id)
            REFERENCES transacoes_entrada (id)
            ON DELETE RESTRICT
            ON UPDATE NO ACTION,
    CONSTRAINT fk_transacoes_entrada_has_materiais_materiais1
        FOREIGN KEY (materiais_id)
            REFERENCES materiais (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);