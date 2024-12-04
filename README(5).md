
# Literalura
![Badge](https://github.com/Juan-Ibanezdf/literalura/blob/main/imagens/badge%20literalura.png)

Resolução do Desafio Java/Spring Boot para o challenge literalura. Esta aplicação utiliza a API  Gutendex para buscar livros, salvá-los no banco de dados, e consultar os dados armazenados localmente.

---

## 📚 **Funcionalidades**

1. **Buscar livros por título pela API e salvar**:
   - Consulta a API Gutendex para buscar livros pelo título.
   - Exibe os resultados encontrados e salva os livros novos no banco de dados local.

2. **Listar livros registrados**:
   - Exibe todos os livros já salvos no banco de dados local.

3. **Listar autores registrados**:
   - Exibe todos os autores registrados no banco de dados.

4. **Listar autores vivos em um determinado ano**:
   - Mostra autores vivos no ano especificado, baseando-se nas datas de nascimento e falecimento.

5. **Listar autores nascidos em determinado ano**:
   - Lista autores nascidos no ano especificado.

6. **Listar autores por ano de sua morte**:
   - Lista autores que faleceram no ano especificado.

7. **Listar livros em um determinado idioma**:
   - Exibe livros registrados no banco de dados em um idioma específico.

8. **Encerrar a aplicação**:
   - Encerra o programa.

OBS: Da Opção 2 em diante, é necessário já ter algum livro salvo, de preferencia 2 livros.

---
## 🔧 **Configuração do Projeto**

### Pré-requisitos

- **Java 17** ou superior
- **Maven**
- **PostgreSQL**

### Instalação

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/literalura.git
   cd literalura
   ```

2. **Configure o banco de dados no arquivo `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. **Crie o banco de dados chamado `literalura`**:
   ```sql
   CREATE DATABASE literalura;
   ```

4. **Execute o projeto**:
   ```bash
   mvn spring-boot:run
   ```

---

## 🧪 **Sugestão para Testes**

- Adicione registros iniciais ao banco de dados para testar as funcionalidades locais:
  - Livro 1:
    - Título: **"O Cortiço"**
    - Autor: **"Aluísio Azevedo"**
    - Idioma: **"pt"**
    - Downloads: **1000**
  - Livro 2:
    - Título: **"The Great Gatsby"**
    - Autor: **"F. Scott Fitzgerald"**
    - Idioma: **"en"**
    - Downloads: **5000**

  **Comandos SQL para inserção:**
  ```sql
  INSERT INTO autores (autor, ano_nascimento, ano_falecimento) VALUES ('Aluísio Azevedo', 1857, 1913);
  INSERT INTO autores (autor, ano_nascimento, ano_falecimento) VALUES ('F. Scott Fitzgerald', 1896, 1940);

  INSERT INTO livros (titulo, idioma, numero_downloads, autor_id) VALUES ('O Cortiço', 'pt', 1000, 1);
  INSERT INTO livros (titulo, idioma, numero_downloads, autor_id) VALUES ('The Great Gatsby', 'en', 5000, 2);
  ```

---

## 🚀 **Uso**

1. **Inicie a aplicação** e siga o menu principal exibido.
2. Escolha uma opção para executar a funcionalidade desejada.

### **Exemplos de Uso**

- **Buscar livros por título**:
  - Digite `1` e pressione Enter.
  - Insira o título do livro que deseja buscar.
  - A aplicação consultará a API Gutendex, exibirá os resultados encontrados e salvará livros novos no banco.

- **Listar livros registrados**:
  - Digite `2` e pressione Enter.
  - A aplicação exibirá todos os livros registrados localmente.

- **Listar autores registrados**:
  - Digite `3` e pressione Enter.
  - A aplicação exibirá todos os autores registrados.

- **Listar autores vivos em um ano**:
  - Digite `4`, insira o ano desejado, e pressione Enter.
  - A aplicação exibirá os autores vivos naquele ano.

---

## 🛠️ **Estrutura do Projeto**

- **Pacote principal (`br.com.alura.literalura`)**:
  - `principal`: Contém a classe `Principal`, que gerencia a aplicação.
  - `model`: Define as entidades do banco de dados (`Livro`, `Autor`) e objetos de transferência de dados (`LivroDTO`, `AutorDTO`).
  - `repository`: Contém as interfaces de repositório Spring Data JPA.
  - `service`: Contém classes de serviço para manipulação de dados e integração com a API.

---

## 🤝 **Contribuição**

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature: `git checkout -b minha-feature`.
3. Commit suas mudanças: `git commit -m 'Adicionando minha feature'`.
4. Push para sua branch: `git push origin minha-feature`.
5. Crie um Pull Request.

---

## 📝 **Licença**

Este projeto está licenciado sob a MIT License. Veja o arquivo `LICENSE` para mais detalhes.

---

