package br.com.alura.forum.controllers.dto.responses;

import br.com.alura.forum.controllers.dto.requests.LoginRequestDTO;

public class LoginResponseDTO {
    private String token;
    private String tipo;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
