-- ============================================================
--  Sistema Acadêmico - Script de criação do banco de dados
-- ============================================================

CREATE DATABASE IF NOT EXISTS sistemaacademico
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sistemaacademico;

-- ------------------------------------------------------------
-- Tabela: aluno
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS alunos (
    rgm        VARCHAR(20)  NOT NULL,
    nome       VARCHAR(100) NOT NULL,
    data_nasc  DATE         NOT NULL,
    cpf        VARCHAR(14)  NOT NULL,
    email      VARCHAR(100),
    endereco   VARCHAR(200),
    municipio  VARCHAR(100),
    uf         VARCHAR(2),
    celular    VARCHAR(20),
    curso      VARCHAR(100),
    campus     VARCHAR(100),
    periodo    VARCHAR(20),
    CONSTRAINT pk_aluno  PRIMARY KEY (rgm),
    CONSTRAINT uq_cpf    UNIQUE      (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------------
-- Tabela: notas_faltas
-- Ao excluir o aluno, suas notas e faltas são excluídas (ON DELETE CASCADE)
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS notas_faltas (
    id         INT          NOT NULL AUTO_INCREMENT,
    rgm        VARCHAR(20)  NOT NULL,
    disciplina VARCHAR(100) NOT NULL,
    semestre   VARCHAR(10)  NOT NULL,
    nota       DECIMAL(5,2) DEFAULT 0.00,
    faltas     INT          DEFAULT 0,

    CONSTRAINT pk_notas PRIMARY KEY (id),

    CONSTRAINT fk_notas_aluno FOREIGN KEY (rgm)
        REFERENCES alunos(rgm)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uk_notas_faltas UNIQUE (rgm, disciplina, semestre)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* INSERTS COM DADOS */
USE sistemaacademico;

INSERT INTO alunos
(rgm, nome, data_nasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo)
VALUES
('1', 'Ana Souza', '2005-03-15', '111.111.111-11', 'ana.souza@email.com', 'Rua A, 100', 'São Paulo', 'SP', '(11) 90000-0001', 'Análise e Desenvolvimento de Sistemas', 'Tatuapé', 'Matutino'),

('2', 'Bruno Lima', '2004-07-22', '222.222.222-22', 'bruno.lima@email.com', 'Rua B, 200', 'São Paulo', 'SP', '(11) 90000-0002', 'Ciencias da Computação', 'Tatuapé', 'Vespertino'),

('3', 'Carla Mendes', '2006-01-10', '333.333.333-33', 'carla.mendes@email.com', 'Rua C, 300', 'São Paulo', 'SP', '(11) 90000-0003', 'Engenharia de Software', 'Tatuapé', 'Noturno'),

('4', 'Diego Santos', '2005-11-05', '444.444.444-44', 'diego.santos@email.com', 'Rua D, 400', 'São Paulo', 'SP', '(11) 90000-0004', 'Gestão de TI', 'Tatuapé', 'Matutino'),

('5', 'Fernanda Alves', '2004-09-18', '555.555.555-55', 'fernanda.alves@email.com', 'Rua E, 500', 'São Paulo', 'SP', '(11) 90000-0005', 'Análise e Desenvolvimento de Sistemas', 'Tatuapé', 'Noturno');

INSERT INTO notas_faltas
(rgm, disciplina, semestre, nota, faltas)
VALUES
('1', 'Estrutura de Dados I', '2026-1', 8.5, 2),
('1', 'Programação Orientada a Objetos', '2026-1', 9.0, 0),
('1', 'Banco de Dados', '2026-1', 7.5, 4),

('2', 'Estrutura de Dados I', '2026-1', 6.50, 6),
('2', 'Programação Orientada a Objetos', '2026-1', 7.0, 3),
('2', 'Banco de Dados', '2026-1', 8.00, 1),

('3', 'Estrutura de Dados I', '2026-1', 9.5, 0),
('3', 'Programação Orientada a Objetos', '2026-1', 8.5, 2),
('3', 'Banco de Dados', '2026-1', 9.0, 1),

('4', 'Estrutura de Dados I', '2026-1', 5.5, 8),
('4', 'Programação Orientada a Objetos', '2026-1', 6.0, 5),
('4', 'Banco de Dados', '2026-1', 7.0, 4),

('5', 'Estrutura de Dados I', '2026-1', 8.0, 2),
('5', 'Programação Orientada a Objetos', '2026-1', 7.5, 3),
('5', 'Banco de Dados', '2026-1', 8.5, 1);

/* CONSULTAS */

SELECT * FROM alunos;

SELECT * FROM notas_faltas;