package br.com.alura.forum.services;

import br.com.alura.forum.controllers.dto.requests.LoginRequestDTO;
import br.com.alura.forum.controllers.dto.responses.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public LoginResponseDTO loginAuth(LoginRequestDTO loginRequest) {
        if (Objects.nonNull(loginRequest)) {
            UsernamePasswordAuthenticationToken login = loginRequest.convert();
            Authentication authentication = authenticationManager.authenticate(login);

            String token = tokenService.generateToken(authentication);
            LoginResponseDTO loginResponse = new LoginResponseDTO(token, "Bearer");

            return loginResponse;
        }

        return null;
    }
}
