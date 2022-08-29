package br.com.alura.forum.controllers;

import br.com.alura.forum.controllers.dto.requests.LoginRequestDTO;
import br.com.alura.forum.controllers.dto.responses.LoginResponseDTO;
import br.com.alura.forum.services.LoginService;
import br.com.alura.forum.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        UsernamePasswordAuthenticationToken login = loginRequest.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(login);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new LoginResponseDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
