package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoFalecimento) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Autor: ").append(autor != null ? autor : "Não informado");

        if (anoNascimento != null) {
            sb.append(" (Nascido em ").append(anoNascimento);
            if (anoFalecimento != null) {
                sb.append(", Falecido em ").append(anoFalecimento);
            }
            sb.append(")");
        }

        return sb.toString();
    }
}
