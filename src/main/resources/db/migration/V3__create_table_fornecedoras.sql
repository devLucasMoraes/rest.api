CREATE TABLE IF NOT EXISTS fornecedoras (
                                            id BIGSERIAL PRIMARY KEY,
                                            cnpj VARCHAR(45) NOT NULL UNIQUE,
                                            razao_social VARCHAR(45) NOT NULL,
                                            nome_fantasia VARCHAR(45),
                                            fone VARCHAR(45)
);

