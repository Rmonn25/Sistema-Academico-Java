# Sistema Acadêmico Java

Sistema acadêmico desenvolvido em Java utilizando Swing para interface gráfica e MySQL para armazenamento de dados.

---

# 📚 Sobre o Projeto

O projeto foi desenvolvido com o objetivo de simular um sistema acadêmico para gerenciamento de alunos, notas, faltas e boletins escolares.

O sistema permite:

* Cadastro de alunos
* Consulta de alunos
* Alteração de dados cadastrais
* Exclusão de alunos
* Cadastro de notas e faltas
* Alteração de notas e faltas
* Exclusão de disciplinas/notas
* Geração de boletim acadêmico

---

# 🛠 Tecnologias Utilizadas

* Java
* Java Swing
* Eclipse IDE
* WindowBuilder
* MySQL
* JDBC

---

# 📂 Estrutura do Projeto

```text
Sistema-Academico-Java
│
├── src/
│   ├── sistemaacademico/
│   │   ├── DAO/
│   │   ├── Telas/
│   │   ├── Conexao.java
│   │   ├── Aluno.java
│   │   └── gui.java
│
├── banco/
│   └── sistemaacademico.sql
│
└── README.md
```

---

# ⚙️ Funcionalidades

## 👨‍🎓 Alunos

* Cadastro de alunos
* Consulta por RGM ou CPF
* Alteração de dados
* Exclusão de aluno

## 📘 Notas e Faltas

* Cadastro de notas
* Cadastro de faltas
* Alteração de notas
* Exclusão de disciplinas
* Consulta por aluno

## 📄 Boletim

* Consulta automática via RGM
* Exibição de:

  * Nome
  * Curso
  * Período
  * Disciplinas
  * Notas
  * Faltas
  * Semestre

---

# 🗄 Banco de Dados

## Criar banco:

```sql
CREATE DATABASE sistemaacademico;
```

## Selecionar banco:

```sql
USE sistemaacademico;
```

---

# ▶️ Como Executar

## 1. Clone o repositório

```bash
git clone https://github.com/Rmonn25/Sistema-Academico-Java
```

---

## 2. Abra no Eclipse

* File → Open Projects from File System
* Selecione a pasta do projeto

---

## 3. Configure o banco

Abra a classe:

```text
Conexao.java
```

Configure:

```java
String url = "jdbc:mysql://localhost:3306/sistemaacademico";
String usuario = "root";
String senha = "SUA_SENHA";
```

---

## 4. Execute o script SQL

Crie as tabelas no MySQL utilizando o arquivo:

```text
sistemaacademico.sql
```

---

## 5. Execute o projeto

Execute:

```text
gui.java
```

---

# 🖼️ Imagens do Projeto

## Cadastro de Alunos

![Tela Principal](https://raw.githubusercontent.com/Rmonn25/Sistema-Academico-Java/main/imagens/Tela%20Principal.png)

---

## Consulta de Alunos

![Consultar Aluno](https://raw.githubusercontent.com/Rmonn25/Sistema-Academico-Java/main/imagens/Consultar%20Aluno.png)

---

## Cadastro de Notas

![Cadastro de Notas](https://raw.githubusercontent.com/Rmonn25/Sistema-Academico-Java/main/imagens/Cadastro%20de%20notas.png)

---

## Boletim Acadêmico

![Boletim de Notas](https://raw.githubusercontent.com/Rmonn25/Sistema-Academico-Java/main/imagens/Boletim%20de%20Notas.png)

---

# 📌 Observações

* O projeto utiliza arquitetura separada entre:

  * Interface gráfica
  * DAO
  * Conexão com banco
  * Modelos

* O banco utiliza:

  * Chaves primárias
  * Chaves estrangeiras
  * ON DELETE CASCADE
  * Restrições UNIQUE

---

# 📚 Finalidade Acadêmica

Este projeto foi desenvolvido exclusivamente para fins acadêmicos, com o objetivo de aplicar conceitos de:

- Programação Orientada a Objetos
- Desenvolvimento Desktop em Java
- Integração com Banco de Dados
- JDBC
- CRUD
- Arquitetura DAO
- Modelagem de Sistemas Acadêmicos

---
