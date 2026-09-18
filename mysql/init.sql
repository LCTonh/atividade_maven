CREATE DATABASE IF NOT EXISTS mvc_java
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE mvc_java;

-- =========================================
-- TABELA DE USUÁRIOS
-- =========================================

CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_usuario_email
        UNIQUE (email),

    CONSTRAINT fk_usuario_perfil
        FOREIGN KEY (perfil_id)
        REFERENCES perfis(id)
);

-- =========================================
-- DADOS PARA TESTE
-- =========================================

INSERT INTO usuarios (
    nome,
    email,
    senha,
    perfil_id
)
VALUES
    ('Admin', 'admin', '123456', 1),
    ('João Professor', 'joao', '123456', 2),
    ('Maria Aluna', 'maria', '123456', 3);


-- =========================================
-- CONSULTA DE EXEMPLO
-- =========================================

SELECT
    u.id,
    u.nome,
    u.login,
    p.nome AS perfil
FROM usuarios u
INNER JOIN perfis p
    ON p.id = u.perfil_id
ORDER BY u.nome;