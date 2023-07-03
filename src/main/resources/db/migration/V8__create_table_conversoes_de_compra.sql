CREATE TABLE IF NOT EXISTS conversoes_de_compra
(
    id                                 BIGSERIAL PRIMARY KEY,
    und_compra                         VARCHAR(45),
    und_padrao                         VARCHAR(45),
    fator_de_conversao                 NUMERIC(10, 2),
    vinculos_materiais_fornecedoras_id BIGINT NOT NULL,
    CONSTRAINT fk_conversoes_de_compra_vinculos_materiais_fornecedoras1
        FOREIGN KEY (vinculos_materiais_fornecedoras_id)
            REFERENCES vinculos_materiais_fornecedoras (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);