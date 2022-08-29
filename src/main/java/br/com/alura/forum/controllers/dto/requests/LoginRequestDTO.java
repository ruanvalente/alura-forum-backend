package br.com.alura.forum.controllers.dto.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequestDTO {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
