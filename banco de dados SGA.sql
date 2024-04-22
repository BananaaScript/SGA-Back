create database if not exists SGAbd;
use SGAbd;

create table if not exists usuario(
	id int primary key,
    nome varchar(50),
    cpf varchar(50),
    genero varchar(50),
    telefone varchar(50),
    email varchar(50),
    role varchar(50),
    unique key u_cpf(cpf)
);
    
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

create table if not exists ativos(
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

create table if not exists notificacao(
	id int primary key,
    usuario_id int,
    usuario_nome varchar(50),
    data_vencimento Date,
    data_cadastro Date,
    foreign key (usuario_id) references usuario(id)
);

create table if not exists manutencao(
	id int primary key,
    id_ativo int,
    estado_ativo varchar(50),
    data_ultima_manutencao date,
    data_proxima_manutencao date,
    foreign key (id_ativo) references ativos(id)
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

create table if not exists usuario(
	id int primary key auto_increment,
    nome varchar(50),
    senha varchar(100)
);

select * from ativos;
select * from categoria;
select * from modelo;