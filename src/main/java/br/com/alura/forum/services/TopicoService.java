package br.com.alura.forum.services;

import br.com.alura.forum.controllers.dto.responses.TopicoResponseDTO;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<TopicoResponseDTO> topicList(String cursoNome) {

        if (Objects.isNull(cursoNome)) {
            List<Topico> topicoList = topicoRepository.findAll();
            return TopicoResponseDTO.converter(topicoList);
        }

        List<Topico> topico = topicoRepository.findByCursoNome(cursoNome);
        return TopicoResponseDTO.converter(topico);
    }
}
