CREATE DATABASE IF NOT EXISTS SGAbd;
USE SGAbd;

CREATE TABLE IF NOT EXISTS usuario(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    idade VARCHAR(50),
    senha VARCHAR(255),
    rg VARCHAR(50),
    cpf VARCHAR(50),
    genero VARCHAR(50),
    telefone VARCHAR(50),
    email VARCHAR(50),
    imagem VARCHAR(50),
    role ENUM('ADMIN','USER'),
    UNIQUE KEY u_cpf(cpf)
);

INSERT INTO usuario(id, nome, senha, cpf, genero, telefone, email, role)
VALUES    
	(98, 'admteste','adm', '0000000000', 'Indefinido', '(12)00000-0000', 'adm@gmail.com', 'ADMIN' ),
	(99, 'Desteste','des', '9999999999', 'Indefinido', '(12)99999-9999', 'des@gmail.com', 'USER' );

CREATE TABLE IF NOT EXISTS local(
	id INT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(50),
    cidade VARCHAR(50),
    rua VARCHAR(50),
    bairro VARCHAR(50),
    complemento VARCHAR(50),
    numero VARCHAR(50),
    cep VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS categoria(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    descricao VARCHAR(255),
    complemento VARCHAR(255)
);

INSERT INTO categoria(id, nome, descricao, complemento)
VALUES
	(10, 'CategoriaTeste1', 'teste de categoria', 'nenhum'),
    (11, 'CategoriaTeste2', 'segundo teste de categoria', 'nenhum');

CREATE TABLE IF NOT EXISTS modelo(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_categoria INT,
    nome_categoria VARCHAR(50),
    nome VARCHAR(50),
    descricao VARCHAR(255),
    modelo VARCHAR(50),
    fabricante VARCHAR(50),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

INSERT INTO modelo(id, id_categoria, nome_categoria, nome, descricao, modelo)
VALUES
	(2, 10, 'CategoriaTeste1', 'Modelo 1', 'Primeiro modelo', 'A3SA1231-12');

CREATE TABLE IF NOT EXISTS ativos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_modelo INT,
    id_categoria INT,
    id_responsavel INT ,
    nome_modelo VARCHAR(50),
    nome_categoria VARCHAR(50),
    nome VARCHAR(50),
    descricao VARCHAR(255) DEFAULT 'Não informado',
    complemento_ativo VARCHAR(255),
    responsavel VARCHAR(50),
	numero_serie VARCHAR(255),
    valor VARCHAR(50),
    num_ativo VARCHAR(50),
    data_manutencao DATE,
    data_transacao DATE,
    rua VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    pais VARCHAR(50),
    complemento VARCHAR(50),
    numero VARCHAR(50),
    cep VARCHAR(50),
    estado VARCHAR(50),
    UNIQUE KEY u_numativo(num_ativo),
    FOREIGN KEY (id_modelo) REFERENCES modelo(id),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_responsavel) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS notificacao(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    numero_ativo VARCHAR(50),
    usuario VARCHAR(50),
	data_expiracao DATE,
    dias INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS manutencao(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_ativo INT,
    estado_ativo VARCHAR(50),
    data_ultima_manutencao DATE,
    data_proxima_manutencao DATE,
    FOREIGN KEY (id_ativo) REFERENCES ativos(id)
);

INSERT INTO ativos (id_modelo, id_categoria, id_responsavel, nome_modelo, nome_categoria, nome, responsavel, numero_serie, valor, num_ativo, data_manutencao, data_transacao, rua, bairro, cidade, pais, complemento, numero, cep, estado)
VALUES (2, 10, 98, 'Modelo 1', 'CategoriaTeste1', 'Ativo 1', 'admteste', 'SN123456', '1000', 'A124', '2024-01-01', '2024-01-01', 'Rua X', 'Bairro Y', 'Cidade Z', 'País W', 'Complemento', '123', '00000-000', 'Estado');

SELECT * FROM usuario;
SELECT * FROM ativos;
SELECT * FROM notificacao;
SELECT * FROM categoria;
SELECT * FROM modelo;
