package br.com.alura.forum.controllers.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoUpdateRequestDTO {
    @NotBlank
    @NotNull
    @Size(min = 6, max = 100)
    private String titulo;
    @NotBlank
    @NotNull
    @Size(min = 20, max = 250)
    private String mensagem;

    public TopicoUpdateRequestDTO() {
    }

    public TopicoUpdateRequestDTO(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
