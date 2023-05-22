CREATE TABLE IF NOT EXISTS transportadoras (
                                                id BIGSERIAL PRIMARY KEY,
                                                cnpj VARCHAR(45) NOT NULL UNIQUE,
                                                razao_social VARCHAR(100) NOT NULL,
                                                nome_fantasia VARCHAR(100),
                                                fone VARCHAR(45)
);


