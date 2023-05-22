CREATE TABLE IF NOT EXISTS vinculos_materiais_fornecedoras
(
    id                    BIGSERIAL PRIMARY KEY,
    materiais_id          BIGINT         NOT NULL,
    fornecedoras_id BIGINT         NOT NULL,
    cod_prod               VARCHAR(100)    NOT NULL,
    CONSTRAINT fk_fornecedoras_has_materiais_fornecedoras1
        FOREIGN KEY (fornecedoras_id)
            REFERENCES fornecedoras (id)
            ON DELETE RESTRICT
            ON UPDATE NO ACTION,
    CONSTRAINT fk_fornecedoras_has_materiais_materiais1
        FOREIGN KEY (materiais_id)
            REFERENCES materiais (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);