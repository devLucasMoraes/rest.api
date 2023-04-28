CREATE TABLE IF NOT EXISTS categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    und_padrao VARCHAR(45) NOT NULL,
    estoque_minimo NUMERIC(10,2) NOT NULL
);
