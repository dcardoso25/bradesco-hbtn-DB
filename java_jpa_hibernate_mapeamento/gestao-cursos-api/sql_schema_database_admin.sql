
CREATE TABLE Aluno (id bigint not null, email varchar(255), matricula varchar(255), nascimento datetime, nomeCompleto varchar(255), primary key (id));

CREATE TABLE Curso (id bigint not null, nome varchar(255), sigla varchar(255), materialCurso_id bigint, professor_id bigint not null, primary key (id));

CREATE TABLE Curso_Aluno (curso_id bigint not null, aluno_id bigint not null, primary key (curso_id, aluno_id));

CREATE TABLE Endereco (id bigint not null, bairro varchar(255), cep integer, cidade varchar(255), endereco varchar(255), estado varchar(255), logradouro varchar(255), numero varchar(255), aluno_id bigint not null, primary key (id));

CREATE TABLE MaterialCurso (id bigint not null, url varchar(255), primary key (id));

CREATE TABLE Professor (id bigint not null, email varchar(255), matricula varchar(255), nomeCompleto varchar(255), primary key (id));

CREATE TABLE Telefone (id bigint not null, DDD varchar(255), numero varchar(255), aluno_id bigint not null, primary key (id));


CREATE TABLE hibernate_sequence (next_val bigint);