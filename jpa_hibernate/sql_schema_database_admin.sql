-- Pessoa definition

CREATE TABLE Pessoa (id bigint not null, cpf varchar(255), dataNascimento date, email varchar(255), idade integer, nome varchar(255), primary key (id));


-- Produto definition

CREATE TABLE Produto (id bigint not null, nome varchar(255), preco double precision, quantidade integer, status boolean, primary key (id));


-- hibernate_sequence definition

CREATE TABLE hibernate_sequence (next_val bigint);