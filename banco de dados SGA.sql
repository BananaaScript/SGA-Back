create database SGAbd;
use SGAbd;

create table administrador(
	id int primary key,
    nome varchar(50),
    cpf varchar(50),
    genero varchar(50),
    telefone varchar(50),
    email varchar(50),
    unique key u_cpf(cpf)
);
    
create table funcionario(
	id int primary key,
    nome varchar(50),
    cpf varchar(50),
    genero varchar(50),
    telefone varchar(50),
    email varchar(50),
    unique key u_cpf(cpf)
);
    
create table local(
	id int primary key,
    estado varchar(50),
    cidade varchar(50),
    rua varchar(50),
    bairro varchar(50),
    complemento varchar(50),
    numero varchar(50),
    cep varchar(50)
);

create table ativos(
	id int primary key auto_increment,
    id_local int,
    nome varchar(50),
    numero_ativo varchar(50),
    rua varchar(50),
    bairro varchar(50),
    complemento varchar(50),
    numero varchar(50),
    cep varchar(50),
    unique key u_numativo(numero_ativo),
    foreign key (id_local) references local(id)
);

create table manutencao(
	id int primary key,
    id_ativo int,
    estado_ativo varchar(50),
    data_ultima_manutencao date,
    data_proxima_manutencao date,
    foreign key (id_ativo) references ativos(id)
);

create table categoria(
	id int primary key auto_increment,
    nome varchar(50),
    descricao varchar(255)
);

create table modelo(
	id int primary key auto_increment,
    id_categoria int,
    nome varchar(50),
    descricao varchar(255),
    modelo varchar(50),
    foreign key (id_categoria) references categoria(id)
);

select * from ativos;
select * from categoria;
select * from modelo;