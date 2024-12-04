package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Double numeroDownload,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("authors") List<AutorDTO> autores) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Título: ").append(titulo != null ? titulo : "Não informado").append("\n");
        sb.append("Autor(es): \n");

        if (autores != null && !autores.isEmpty()) {
            for (AutorDTO autor : autores) {
                sb.append("  - ").append(autor.autor() != null ? autor.autor() : "Autor não informado").append("\n");
            }
        } else {
            sb.append("  Nenhum autor informado.\n");
        }

        sb.append("Idioma(s): ");
        if (idioma != null && !idioma.isEmpty()) {
            sb.append(String.join(", ", idioma));
        } else {
            sb.append("Não informado");
        }
        sb.append("\n");

        sb.append("Downloads: ").append(numeroDownload != null ? numeroDownload : "Não informado").append("\n");
        sb.append("----------------------------------------");

        return sb.toString();
    }
}
