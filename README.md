  # Escaneador OWASP

Um projeto Java para escaneamento de vulnerabilidades.

## Visão Geral

O projeto Escaneador OWASP é uma ferramenta desenvolvida em Java destinada à detecção de vulnerabilidades em aplicações. Ele utiliza técnicas avançadas para identificar possíveis falhas de segurança e oferece uma interface gráfica para facilitar o uso.

## Estrutura do Projeto

A estrutura do projeto inclui os seguintes arquivos e diretórios principais:

- `src/main/java/com/aesirsoftwares/`: Contém os principais pacotes e classes do projeto.
  - `DatabaseManager.java`: Gerenciamento de banco de dados.
  - `JavaSwingApp/`: Contém as classes da interface gráfica.
    - `Criar.java`
    - `Dashboard.java`
    - `Login.java`
    - `DatabaseManager.java`
  - `VulnerabilityScanner.java`: Scanner de vulnerabilidades.
- `pom.xml`: Arquivo de configuração do Maven.

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/NegativoPai/escaneadorowasp.git
