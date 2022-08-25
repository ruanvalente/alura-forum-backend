package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TopicoRequestDTO {
    @NotNull(message = "O campo titulo não pode ser nullo, por favor preencha o campo corretamente.")
    @NotBlank(message = "Por favor preencha o campo corretamente.")
    private  String titulo;

    @NotNull(message = "O campo mensagem não pode ser nullo, por favor preencha o campo corretamente.")
    @NotBlank(message = "Por favor preencha o campo corretamente.")
    private String mensagem;

    @NotNull(message = "O campo nomeCurso não pode ser nullo, por favor preencha o campo corretamente.")
    @NotBlank(message = "Por favor preencha o campo corretamente.")
    private  String nomeCurso;

    public TopicoRequestDTO() {
    }

    public TopicoRequestDTO(String titulo, String mensagem, String nomeCurso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeCurso = nomeCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicoRequestDTO that = (TopicoRequestDTO) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(mensagem, that.mensagem) && Objects.equals(nomeCurso, that.nomeCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, mensagem, nomeCurso);
    }

    @Override
    public String toString() {
        return "TopicoRequestDTO{" +
                "titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", nomeCurso='" + nomeCurso + '\'' +
                '}';
    }

    public Topico convert(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
