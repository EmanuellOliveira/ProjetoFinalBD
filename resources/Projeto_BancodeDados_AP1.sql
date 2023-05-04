CREATE DATABASE projetosBD;

CREATE TABLE cliente(
	ID_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    empresa VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    telefone_celular VARCHAR(20) NOT NULL
    );
    
CREATE TABLE equipe(
	ID_equipe INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    descricao TEXT NOT NULL
    );
    
INSERT INTO equipe (nome, descricao)
VALUES
	("Alfa", "Equipe responsável pelo Levantamento e Análise de Requisitos."),
    ("Bravo", "Equipe responsável pelo Design do projeto."),
    ("Charlie", "Equipe responsável pela implementação."),
    ("Delta", "Equipe responsável pelos testes do projeto."),
    ("Echo", "Equipe responsável pela implantação do projeto."),
    ("Fox", "Equipe responsável pela Manutenção do projeto.");

CREATE TABLE etapa(
	ID_etapa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL, 
    descricao TEXT NOT NULL,
    ID_equipe INT NOT NULL UNIQUE,
    FOREIGN KEY (ID_equipe) REFERENCES equipe(ID_equipe)
    );
    
INSERT INTO etapa (nome, descricao, ID_equipe)
VALUES
	("Etapa de Requisitos", "Nessa etapa, os requisitos do software são coletados a partir de reuniões com o cliente ou usuários finais do sistema. Os requisitos são documentados e utilizados para orientar o desenvolvimento do software.", 1),
    ("Etapa de Design do projeto", "O design do software é criado com base nos requisitos e análise, incluindo a arquitetura, interfaces e fluxo de dados.", 2),
    ("Etapa de Implementação", "Nessa etapa, o software é programado e testado para garantir que esteja funcionando corretamente.", 3),
    ("Etapa de Testes", "O software é testado para garantir que atenda aos requisitos e funcione de forma esperada. Isso pode incluir testes de unidade, testes de integração, testes de aceitação do usuário, entre outros.", 4),
    ("Etapa de Implantação", "O software é preparado para ser implantado no ambiente de produção, incluindo a instalação, configuração e treinamento do usuário final.", 5),
    ("Etapa de Manutenção", "Depois que o software é implantado, ele requer manutenção contínua para corrigir bugs, adicionar novos recursos e atualizar a funcionalidade.", 6);

CREATE TABLE projetos(
	ID_projeto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_final DATE NOT NULL,
    orcamento decimal(10,2) NOT NULL,
    status VARCHAR(30),
    ID_cliente INT NOT NULL,
    FOREIGN KEY (ID_cliente) REFERENCES cliente(ID_cliente)
    );

    
CREATE TABLE etapas_projeto(
	ID_etapasprojeto INT PRIMARY KEY AUTO_INCREMENT,
	ID_projeto INT NOT NULL,
    ID_etapa INT NOT NULL,
    status_etapa VARCHAR(50) NOT NULL,
    data_inicio DATE NOT NULL,
    data_final DATE NOT NULL,
    FOREIGN KEY (ID_projeto) REFERENCES projetos(ID_projeto),
    FOREIGN KEY (ID_etapa) REFERENCES etapa(ID_etapa)
    );
    

CREATE TABLE membros_de_equipe(
	ID_membros INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone_celular VARCHAR(20) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    ID_equipe INT NOT NULL,
    FOREIGN KEY (ID_equipe) REFERENCES equipe(ID_equipe)
    );

INSERT INTO membros_de_equipe (nome, email, telefone_celular, cargo, ID_equipe)
VALUES
	("João Silva", "joao.silva@gmail.com", "(11)99999-1111", "Analista de Requisitos", 1),
    ("Maria Santos", "maria.santos@gmail.com", "(11)99999-2222", "Especialista em Levantamento de Requisitos", 1),
    ("Pedro Oliveira", "pedro.oliveira@gmail.com", "(11)99999-3333", "Coordenador de Requisitos", 1),
    ("Carla Costa", "carla.costa@gmail.com", "(11)99999-4444", "Analista de Negócios", 1),
    ("Rafael Souza", "rafael.souza@gmail.com", "(11)99999-5555", "Especialista em Requisitos", 1),
    ("Lucas Lima", "lucas.lima@gmail.com", "(11)77777-1111", "Designer de Interfaces", 2),
    ("Mariana Castro", "mariana.castro@gmail.com", "(11)99999-8888", "Desenvolvedor Back-End", 2),
    ("Guilherme Santos", "guilherme.santos@gmail.com", "(11)99999-9999", "Desenvolvedor Front-End", 2),
    ("Mariana Alves", "mariana.alves@gmail.com", "(11)77777-2222", "Especialista em UX/UI", 2),
    ("José Santos", "jose.santos@gmail.com", "(11)77777-3333", "Coordenador de Design", 2),
    ("Gabriel Fernandes", "gabriel.fernandes@gmail.com", "(11)77777-4444", "Analista de Desenvolvimento", 3),
    ("Luana Silva", "luana.silva@gmail.com", "(11)77777-5555", "Especialista em Desenvolvimento", 3),
    ("Aline Costa", "aline.costa@gmail.com", "(11)77777-6666", "Coordenadora de Implementação", 3),
    ("Victor Lima", "victor.lima@gmail.com", "(11)88888-1111", "Gerente de Projeto", 3),
    ("Carlos Oliveira", "carlos.oliveira@gmail.com", "(11)77777-1111", "Analista de Sistemas", 4),
    ("Felipe Sousa", "felipe.sousa@gmail.com", "(11)77777-3333", "Desenvolvedor .NET", 4),
    ("Renata Santos", "renata.santos@gmail.com", "(11)77777-4444", "Analista de Testes Automatizados", 4),
    ("Luciana Oliveira", "luciana.oliveira@gmail.com", "(11)77777-5555", "Gerente de TI", 4),
    ("Marcelo Almeida", "marcelo.almeida@gmail.com", "(11)88888-1111", "Especialista em Implantação", 5),
    ("Renata Oliveira", "renata.oliveira@gmail.com", "(11)88888-2222", "Coordenadora de Implantação", 5),
    ("Andréa Silva", "andrea.silva@gmail.com", "(11)77777-5555", "Especialista em Manutenção", 6),
    ("Henrique Oliveira", "henrique.oliveira@gmail.com", "(11)77777-6666", "Analista de Sistemas", 6),
    ("Fernanda Castro", "fernanda.castro@gmail.com", "(11)77777-7777", "Coordenadora de Manutenção", 6),
    ("Gustavo Costa", "gustavo.costa@gmail.com", "(11)77777-8888", "Analista de Suporte Técnico", 6);