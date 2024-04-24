create database if not exists SGAbd;
use SGAbd;

create table if not exists usuario(
	id int primary key auto_increment,
    nome varchar(50),
    senha varchar(255),
    cpf varchar(50),
    genero varchar(50),
    telefone varchar(50),
    email varchar(50),
    role enum("ADMIN","USER"),
    unique key u_cpf(cpf)
);
INSERT INTO usuario (nome, senha, cpf, genero, telefone, email, role) 
VALUES ('Destinatario1', 'DestKey#01', '1234567890', 'Homem', '12992221111', 'dest01.funcionario@gmail.com.br', 'USER');

INSERT INTO usuario (nome, senha, cpf, genero, telefone, email, role) 
VALUES ('Destinatario2', 'DestKey#01', '1234567891', 'Mulher', '12992221112', 'dest02.funcionario@gmail.com.br', 'USER');

INSERT INTO usuario (nome, senha, cpf, genero, telefone, email, role) 
VALUES ('Administrador1', 'AdmKey#01', '1234567892', 'Homem', '12992221113', 'adm01.administrador@gmail.com.br', 'ADMIN');

INSERT INTO usuario (nome, senha, cpf, genero, telefone, email, role) 
VALUES ('Administrador2', 'AdmKey#01', '1234567893', 'Mulher', '1299221114', 'adm01.administrador@gmail.com.br', 'ADMIN');
 
create table if not exists local(
	id int primary key,
    estado varchar(50),
    cidade varchar(50),
    rua varchar(50),
    bairro varchar(50),
    complemento varchar(50),
    numero varchar(50),
    cep varchar(50)
);

create table if not exists notificacao(
	id int primary key,
    usuario_id int,
    usuario_nome varchar(50),
    data_vencimento Date,
    data_cadastro Date,
    foreign key (usuario_id) references usuario(id)
);

create table if not exists categoria(
	id int primary key auto_increment,
    nome varchar(50),
    descricao varchar(255)
);

create table  if not exists modelo(
	id int primary key auto_increment,
    id_categoria int,
    nome varchar(50),
    descricao varchar(255),
    modelo varchar(50),
    foreign key (id_categoria) references categoria(id)
);

create table if not exists ativos(
	id int primary key auto_increment,
    id_modelo int,
    id_categoria int,
    id_local int,
    nome varchar(50),
    numero_ativo varchar(50),
    rua varchar(50),
    bairro varchar(50),
    complemento varchar(50),
    numero varchar(50),
    cep varchar(50),
    unique key u_numativo(numero_ativo),
    foreign key (id_modelo) references modelo(id),
    foreign key (id_categoria) references categoria(id),
    foreign key (id_local) references local(id)
);

create table if not exists manutencao(
	id int primary key,
    id_ativo int,
    estado_ativo varchar(50),
    data_ultima_manutencao date,
    data_proxima_manutencao date,
    foreign key (id_ativo) references ativos(id)
);


select * from usuario;
select * from ativos;
select * from categoria;
select * from modelo;