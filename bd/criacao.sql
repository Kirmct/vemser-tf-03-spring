ALTER SESSION SET CURRENT_SCHEMA=EQUIPE_5;

-- Usuario --
CREATE TABLE USUARIO (
	id_usuario number(38, 0) NOT NULL,
	nome varchar2(255) NOT NULL,
	dataNascimento DATE NOT NULL,
	cpf CHAR(11) NOT NULL,
	email varchar2(255) NOT NULL,
	senha varchar2(20) NOT NULL,
	PRIMARY KEY (id_usuario)
);

CREATE SEQUENCE SEQ_USUARIO
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
-- Receita --
CREATE TABLE RECEITA (
	id_receita number(38, 0) NOT NULL,
	banco varchar2(50) NOT NULL,
	empresa varchar2(50) NOT NULL,
	valor number(38, 0) NOT NULL,
	descricao varchar2(255) NOT NULL,
	id_usuario number(38, 0) NOT NULL,
	PRIMARY KEY (id_receita),
	CONSTRAINT FK_USUARIO_RECEITA FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE SEQUENCE SEQ_RECEITA
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Investimento --
CREATE TABLE INVESTIMENTO (
	id_investimento number(38, 0) NOT NULL,
	corretora varchar2(100) NOT NULL,
	tipo varchar2(50) NOT NULL,
	valor number(38, 0) NOT NULL,
	data_inicial DATE NOT NULL,
	descricao varchar2(255),
	id_usuario number(38, 0) NOT NULL,
	PRIMARY KEY (id_investimento),
	CONSTRAINT FK_USUARIO_INVESTIMENTO FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE SEQUENCE SEQ_INVESTIMENTO
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

-- Despesa --
CREATE TABLE DESPESA (
	id_despesa number(38, 0) NOT NULL,
	tipo varchar2(50) NOT NULL,
	valor number(38, 0) NOT NULL,
	descricao varchar2(255),
	data_pagamento DATE NOT NULL,
	id_usuario number(38, 0) NOT NULL,
	PRIMARY KEY (id_despesa),
	CONSTRAINT FK_USUARIO_DESPESA FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE SEQUENCE SEQ_DESPESA
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

