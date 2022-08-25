package br.com.alura.forum.repositories;

import br.com.alura.forum.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String cursoNome);
}
