# 🚚 GynLog Transportadora

Sistema desktop desenvolvido em Java para gestão de operações, controle de veículos e acompanhamento financeiro de uma transportadora.

Este projeto foi desenvolvido como parte do **Projeto Integrador** do curso de **Engenharia de Software** do **3º período** da **Faculdade de Tecnologia SENAI FATESG**. A ideia foi criar uma solução prática, visual e funcional para ajudar no acompanhamento de despesas e movimentações de uma transportadora.

Também organizamos o desenvolvimento em um painel de tarefas no GitHub Projects, com divisão de atividades e acompanhamento do time:  
[GitHub Projects - GynLog](https://github.com/users/arthurllucass/projects/2/views/5)

O sistema oferece uma interface amigável com recursos para cadastrar veículos, controlar tipos de despesas, registrar movimentações e gerar relatórios em PDF para facilitar a tomada de decisões.

---

## ✨ Funcionalidades principais

- **Cadastro de veículos**
  - Inclusão, edição e remoção de veículos
  - Controle de placa, marca, modelo, ano e status

- **Gerenciamento de tipos de despesas**
  - Cadastro de categorias para organização financeira

- **Registro de movimentações**
  - Cadastro de entradas/saídas com:
    - veículo associado
    - tipo de despesa
    - descrição
    - data
    - valor

- **Relatórios em PDF**
  - Geração de relatórios por período
  - Filtros por placa e tipo de despesa
  - Relatório geral com todos os registros
  - Listagem de veículos inativos

- **Interface gráfica desktop**
  - Navegação entre telas principais via menu
  - Experiência visual com ícones e layout organizado

---

## 🛠️ Tecnologias e stack

| Tecnologia | Uso no projeto |
|---|---|
| Java 21 | Linguagem principal da aplicação |
| Maven | Gerenciamento de dependências e build |
| Swing | Interface gráfica desktop |
| OpenPDF | Geração de arquivos PDF |
| Arquivos `.txt` | Persistência local dos dados |

---

## 🧱 Arquitetura do projeto

A estrutura foi organizada em camadas para manter a aplicação mais clara e fácil de manter:

- **model**: entidades do sistema (`Veiculo`, `Movimentacao`, `TipoDespesa`)
- **view**: telas da interface gráfica
- **controller**: lógica de controle entre interface e regras de negócio
- **service**: validações e regras principais
- **repository**: leitura e escrita dos dados persistidos
- **report**: geração de relatórios
- **config**: configuração de dependências e inicialização da aplicação

---

## 📁 Estrutura principal

```text
src/
├── main/
│   ├── java/
│   │   └── com/gynlog/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── report/
│   │       ├── repository/
│   │       ├── service/
│   │       └── view/
│   └── resources/
│       ├── icons/
│       └── images/
└── database/
```

---

## ▶️ Como executar

### Pré-requisitos
- Java 21 instalado
- Maven instalado

### Passos
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd GynLog-Transportadora
   ```
3. Compile e gere o pacote:
   ```bash
   mvn clean package
   ```
4. Execute a aplicação:
   ```bash
   java -jar target/gynlog-transportadora-1.0-SNAPSHOT.jar
   ```

> Também é possível rodar a aplicação diretamente pela classe principal `com.gynlog.Main` em sua IDE.

---

## 📊 Exemplo de uso

O sistema foi pensado para auxiliar no dia a dia da transportadora com:

- acompanhamento de despesas por veículo
- organização de movimentações financeiras
- geração rápida de documentos para análise
- manutenção de histórico local dos registros

---

## ✅ Benefícios do sistema

- Interface simples e objetiva
- Controle centralizado das operações
- Relatórios úteis para gestão
- Persistência local sem necessidade de banco de dados externo
- Fácil manutenção e expansão futura

---

## 👥 Sobre o projeto

Este projeto foi desenvolvido com o objetivo de fornecer uma solução prática para o gerenciamento operacional e financeiro de uma transportadora, unindo facilidade de uso, organização dos dados e recursos de geração de relatórios.

### Contexto acadêmico
- **Disciplina:** Projeto Integrador
- **Curso:** Engenharia de Software
- **Período:** 3º período
- **Instituição:** Faculdade de Tecnologia SENAI FATESG

### Organização do time
O desenvolvimento foi acompanhado por meio do GitHub Projects, onde as tarefas foram organizadas e distribuídas para manter o fluxo do projeto mais estruturado e colaborativo.

- Painel de organização: [GitHub Projects - GynLog](https://github.com/users/arthurllucass/projects/2/views/5)
