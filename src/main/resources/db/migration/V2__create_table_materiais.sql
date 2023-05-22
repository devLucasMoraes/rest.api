CREATE TABLE IF NOT EXISTS materiais (
                                         id BIGSERIAL PRIMARY KEY,
                                         descricao VARCHAR(255) NOT NULL,
                                         valor_unt NUMERIC(10,2) NOT NULL,
                                         categorias_id BIGINT NOT NULL,
                                         CONSTRAINT fk_materiais_categorias1 FOREIGN KEY (categorias_id)
                                             REFERENCES categorias (id) ON DELETE RESTRICT ON UPDATE NO ACTION
);
