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

INSERT INTO usuario(id, nome, senha, cpf, genero, telefone, email, role)
VALUES
	("1", "Administrador01","ADMKEY#01", "1111111110", "Indefinido", "(12)00000-0001)", "usuario.adm@gmail.com", "ADMIN" ),
    ("2", "Administrador02","ADMKEY#02", "1111111111", "Indefinido", "(12)00000-0002)", "usuario.adm@gmail.com", "ADMIN" ),
    ("3", "Destinatario01","DESTKEY#01", "1111111112", "Indefinido", "(12)00000-0003)", "usuario.dest@gmail.com", "USER" ),
    ("4", "Destinatario02","DESTKEY#02", "1111111113", "Indefinido", "(12)00000-0004)", "usuario.dest@gmail.com", "USER" ),
    
	("98", "admteste","adm", "0000000000", "Indefinido", "(12)00000-0000)", "adm@gmail.com", "ADMIN" ),
	("99", "Desteste","des", "9999999999", "Indefinido", "(12)99999-9999)", "des@gmail.com", "USER" );
 
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

create table if not exists categoria(
	id int primary key auto_increment,
    nome varchar(50),
    descricao varchar(255),
    complemento varchar(255)
);

create table  if not exists modelo(
	id int primary key auto_increment,
    id_categoria int,
    nome_categoria varchar(50),
    nome varchar(50),
    descricao varchar(255),
    modelo varchar(50),
    fabricante varchar(50),
    imagem varchar(255),
    foreign key (id_categoria) references categoria(id)
);

create table if not exists ativos(
	id int primary key auto_increment,
    id_modelo int,
    id_categoria int,
    id_local int,
    nome_modelo varchar(50),
    nome_categoria varchar(50),
    nome varchar(50),
    descricao varchar(255),
    complementoAtivo varchar(255),
    responsavel varchar(50),
    estado varchar(50),
    emissorNF varchar(255),
    documentoFiscal varchar(255),
	numeroSerie varchar(255),
    valor varchar(50),
    garantia varchar(50),
    num_ativo varchar(50),
    data_manutencao date,
    rua varchar(50),
    bairro varchar(50),
    complemento varchar(50),
    numero varchar(50),
    cep varchar(50),
    unique key u_numativo(num_ativo),
    foreign key (id_modelo) references modelo(id),
    foreign key (id_categoria) references categoria(id),
    foreign key (id_local) references local(id)
);

create table if not exists notificacao(
	id int primary key auto_increment,
    usuario_id int,
    numero_ativo varchar(50),
    usuario varchar(50),
	data_expiracao date,
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


select * from usuario;
select * from ativos;
select * from notificacao;
select * from categoria;
select * from modelo;