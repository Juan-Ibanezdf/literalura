package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LivroRepository livroRepository;

    private final Scanner leitura = new Scanner(System.in);

    public void executar() {
        boolean running = true;
        while (running) {
            exibirMenu();
            int opcao = leitura.nextInt();
            leitura.nextLine(); // Consumir o restante da linha

            switch (opcao) {
                case 1 -> buscarLivrosPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarAutoresVivosRefinado();
                case 6 -> listarAutoresPorAnoDeMorte();
                case 7 -> listarLivrosPorIdioma();
                case 0 -> {
                    System.out.println("Encerrando a LiterAlura!");
                    running = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("""
            ===========================================================
                                LITERALURA
                   Uma aplicação para você que gosta de livros !
                   Escolha um número no menu abaixo:
            -----------------------------------------------------------
                                 Menu
                       1- Buscar livros pelo título
                       2- Listar livros registrados
                       3- Listar autores registrados
                       4- Listar autores vivos em um determinado ano
                       5- Listar autores nascidos em determinado ano
                       6- Listar autores por ano de sua morte
                       7- Listar livros em um determinado idioma
                       0- Sair
            """);
    }

    private void buscarLivrosPeloTitulo() {
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine();
        List<Livro> livros = livroRepository.findByTitulo(titulo);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarLivrosRegistrados() {
        try {
            List<Livro> livros = livroRepository.findAllWithAutores();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro registrado.");
            } else {
                livros.forEach(livro -> {
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + (livro.getAutor() != null ? livro.getAutor().getAutor() : "Não registrado"));
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("Downloads: " + livro.getNumeroDownloads());
                    System.out.println("----------------------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar livros registrados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = livroRepository.findAllAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(autor -> {
                System.out.println("Autor: " + autor.getAutor());
                System.out.println("Ano de Nascimento: " +
                        (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "Não informado"));
                System.out.println("Ano de Falecimento: " +
                        (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "Não informado"));
                System.out.println("----------------------------------------");
            });
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = livroRepository.findAutoresVivos(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosRefinado() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = livroRepository.findAutoresVivosRefinado(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresPorAnoDeMorte() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = livroRepository.findAutoresPorAnoDeMorte(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
        Digite o idioma pretendido:
        1 - Português (pt)
        2 - Inglês (en)
        3 - Espanhol (es)
        4 - Francês (fr)
        5 - Alemão (de)
    """);

        int opcao = leitura.nextInt();
        leitura.nextLine();

        String idioma;
        switch (opcao) {
            case 1 -> idioma = "pt";
            case 2 -> idioma = "en";
            case 3 -> idioma = "es";
            case 4 -> idioma = "fr";
            case 5 -> idioma = "de";
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        try {
            List<Livro> livros = livroRepository.findByIdiomaWithAutor(idioma);
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado no idioma especificado.");
            } else {
                livros.forEach(livro -> {
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + (livro.getAutor() != null ? livro.getAutor().getAutor() : "Não registrado"));
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("Downloads: " + livro.getNumeroDownloads());
                    System.out.println("----------------------------------------");
                });
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar livros por idioma: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
