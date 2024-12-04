package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Autor autor;

    private String idioma;

    @Column(name = "ano_nascimento_autor")
    private Integer anoNascimentoAutor;

    @Column(name = "ano_falecimento_autor")
    private Integer anoFalecimentoAutor;

    @Column(name = "numero_downloads")
    private Integer numeroDownloads; // Alterado para Integer

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getAnoNascimentoAutor() {
        return anoNascimentoAutor;
    }

    public void setAnoNascimentoAutor(Integer anoNascimentoAutor) {
        this.anoNascimentoAutor = anoNascimentoAutor;
    }

    public Integer getAnoFalecimentoAutor() {
        return anoFalecimentoAutor;
    }

    public void setAnoFalecimentoAutor(Integer anoFalecimentoAutor) {
        this.anoFalecimentoAutor = anoFalecimentoAutor;
    }

    public Integer getNumeroDownloads() { // Alterado para Integer
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) { // Alterado para Integer
        this.numeroDownloads = numeroDownloads;
    }

    // Construtores
    public Livro() {}

    public Livro(LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();

        if (livroDTO.autores() != null && !livroDTO.autores().isEmpty()) {
            Autor autor = new Autor(livroDTO.autores().get(0));
            this.autor = autor;
            this.anoNascimentoAutor = livroDTO.autores().get(0).anoNascimento();
            this.anoFalecimentoAutor = livroDTO.autores().get(0).anoFalecimento();
        }

        this.idioma = livroDTO.idioma() != null && !livroDTO.idioma().isEmpty()
                ? livroDTO.idioma().get(0)
                : "Desconhecido";

        this.numeroDownloads = livroDTO.numeroDownload() != null
                ? livroDTO.numeroDownload().intValue() // Convertendo para Integer
                : 0;
    }

    public Livro(String titulo, Autor autor, String idioma, Integer numeroDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "Título: " + (titulo != null ? titulo : "Não informado") + "\n" +
                "Autor: " + (autor != null ? autor : "Não informado") + "\n" +
                "Idioma: " + (idioma != null ? idioma : "Não informado") + "\n" +
                "Downloads: " + (numeroDownloads != null ? numeroDownloads : "Não informado") + "\n" +
                "----------------------------------------";
    }
}
