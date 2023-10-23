# Projeto de Gerenciamento de Alunos

Este projeto consiste em um sistema de gerenciamento de alunos desenvolvido em Java, com integração a um banco de dados (SGBD) de sua escolha (ex: MySQL, PostgreSQL).

## Configuração do Banco de Dados

1. Crie uma base de dados com o nome `sistema_alunos`.
2. Execute os seguintes scripts SQL para criar as tabelas necessárias:

```sql
-- Tabela aluno
CREATE TABLE aluno (
  idaluno INT PRIMARY KEY,
  nome VARCHAR(100),
  cpf VARCHAR(11),
  fk_idendereco INT,
  FOREIGN KEY (fk_idendereco) REFERENCES endereco(idendereco)
);

-- Tabela endereco
CREATE TABLE endereco (
  idendereco INT PRIMARY KEY,
  cidade VARCHAR(50),
  rua VARCHAR(100),
  numero VARCHAR(10)
);
```

## Estrutura do Projeto

O projeto Java possui os seguintes pacotes:

- `model`: Contém as classes que representam os objetos do sistema (`Aluno` e `Endereco`).
- `persistence`: Inclui a classe de conexão com o banco de dados (`DatabaseConnection`).
- `controller`: Engloba a classe `AlunoManager` que gerencia as operações de inserção, edição, remoção e listagem de alunos.

## Executando o Projeto

1. Clone o repositório para o seu ambiente local.
2. Configure a conexão com o banco de dados em `DatabaseConnection.java`, inserindo as credenciais de acesso.

```java
// Exemplo de configuração para MySQL
String url = "jdbc:mysql://localhost:3306/sistema_aluno";
String user = "usuario";
String pass = "senha";
```

3. Execute a classe `Main.java` para iniciar o sistema.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. **Inserir Aluno**: Adiciona novos alunos ao banco de dados.
2. **Remover Aluno**: Remove um aluno com base no seu ID.
3. **Editar Aluno**: Permite a edição de informações de um aluno através do seu CPF.
4. **Listar Alunos**: Exibe todos os alunos cadastrados no banco de dados.

---

Este projeto foi desenvolvido como parte de um exercício prático do curso de TADS da Univel.
