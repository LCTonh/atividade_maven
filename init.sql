CREATE DATABASE IF NOT EXISTS avaliacao_cafes;
USE avaliacao_cafes;

CREATE TABLE cafes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco TEXT,
    bairro VARCHAR(100),
    tipo_cafe_principal VARCHAR(50)
);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cafe_id INT,
    autor VARCHAR(100),
    nota_bebida INT,
    nota_ambiente INT,
    nota_atendimento INT,
    comentario TEXT,
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cafe_id) REFERENCES cafes(id)
);