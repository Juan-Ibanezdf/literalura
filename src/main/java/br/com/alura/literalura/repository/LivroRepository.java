package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    /**
     * Busca livros pelo título, ignorando diferenças de maiúsculas e minúsculas.
     * Se o título for parcial, retorna correspondências parciais.
     */
    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.autor WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> findByTitulo(@Param("titulo") String titulo);


    /**
     * Busca todos os autores que estavam vivos em um determinado ano.
     * Um autor é considerado vivo se nasceu antes ou no ano especificado e morreu depois ou não morreu.
     */
    @Query("""
            SELECT a 
            FROM Autor a 
            WHERE a.anoNascimento <= :ano 
            AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
           """)
    List<Autor> findAutoresVivos(@Param("ano") Integer ano);

    /**
     * Busca autores nascidos em um ano específico e que estavam vivos nesse ano.
     */
    @Query("""
            SELECT a 
            FROM Autor a 
            WHERE a.anoNascimento = :ano 
            AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
           """)
    List<Autor> findAutoresVivosRefinado(@Param("ano") Integer ano);

    /**
     * Busca autores que morreram em um ano específico.
     */
    @Query("""
            SELECT a 
            FROM Autor a 
            WHERE a.anoNascimento <= :ano 
            AND a.anoFalecimento = :ano
           """)
    List<Autor> findAutoresPorAnoDeMorte(@Param("ano") Integer ano);

    /**
     * Busca livros em um idioma correspondente ao parâmetro (exato).
     */
    @Query("""
    SELECT l 
    FROM Livro l 
    LEFT JOIN FETCH l.autor 
    WHERE LOWER(l.idioma) = LOWER(:idioma)
""")
    List<Livro> findByIdiomaWithAutor(@Param("idioma") String idioma);



    /**
     * Busca todos os autores disponíveis no banco (sem duplicatas).
     */
    @Query("SELECT DISTINCT a FROM Autor a")
    List<Autor> findAllAutores();

    /**
     * Busca todos os livros com os autores carregados usando JOIN FETCH.
     */
    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.autor")
    List<Livro> findAllWithAutores();
}
