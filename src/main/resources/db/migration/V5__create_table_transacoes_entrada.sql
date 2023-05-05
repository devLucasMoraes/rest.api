CREATE TABLE IF NOT EXISTS transacoes_entrada (
                                                    id BIGSERIAL PRIMARY KEY,
                                                    nfe VARCHAR(255),
                                                    data_emissao TIMESTAMP,
                                                    data_recebimento TIMESTAMP NOT NULL,
                                                    valor_total NUMERIC(10,2),
                                                    valor_frete NUMERIC(10,2),
                                                    valor_ipi_total NUMERIC(10,2),
                                                    obs VARCHAR(255),
                                                    transportadoras_id BIGINT NOT NULL,
                                                    fornecedoras_id BIGINT NOT NULL,
                                                    CONSTRAINT fk_transacoes_entrada_transportadoras1
                                                        FOREIGN KEY (transportadoras_id)
                                                            REFERENCES transportadoras (id)
                                                            ON DELETE RESTRICT
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT fk_transacoes_entrada_fornecedoras1
                                                        FOREIGN KEY (fornecedoras_id)
                                                            REFERENCES fornecedoras (id)
                                                            ON DELETE RESTRICT
                                                            ON UPDATE NO ACTION
);



