package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Resposta;

import java.time.LocalDateTime;

public class RespostaResponseDTO {
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaResponseDTO(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
