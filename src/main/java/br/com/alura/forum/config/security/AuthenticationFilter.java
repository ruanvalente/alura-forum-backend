package br.com.alura.forum.config.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repositories.UsuarioRepository;
import br.com.alura.forum.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AuthenticationFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = receiveToken(request);
        boolean isTokenValid = tokenService.hasTokenValid(token);

        if (isTokenValid) {
            userAuthenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private String receiveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private void userAuthenticate(String token) {
        Long userId = tokenService.getUserId(token);
        Usuario user = usuarioRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
