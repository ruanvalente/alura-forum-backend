package br.com.alura.forum.services;

import br.com.alura.forum.controllers.dto.requests.TopicoRequestDTO;
import br.com.alura.forum.controllers.dto.requests.TopicoUpdateRequestDTO;
import br.com.alura.forum.controllers.dto.responses.TopicoDetailResponseDTO;
import br.com.alura.forum.controllers.dto.responses.TopicoResponseDTO;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Page<TopicoResponseDTO> topicList(String cursoNome, Pageable pagination) {

        if (Objects.isNull(cursoNome)) {
            Page<Topico> topicoList = topicoRepository.findAll(pagination);
            return TopicoResponseDTO.converter(topicoList);
        }

        Page<Topico> topico = topicoRepository.findByCursoNome(cursoNome, pagination);
        return TopicoResponseDTO.converter(topico);
    }

    @Transactional
    public TopicoResponseDTO createTopic(TopicoRequestDTO topicoRequest) {
        Topico topico = topicoRequest.convert(cursoRepository);
        topicoRepository.save(topico);
        return new TopicoResponseDTO(topico);
    }

    @Transactional
    public TopicoDetailResponseDTO topicDetail(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            return new TopicoDetailResponseDTO(topico.get());
        }
        return null;
    }

    @Transactional
    public TopicoResponseDTO topicUpdate(Long id, TopicoUpdateRequestDTO topicoUpdateRequest) {
        Topico topico = topicoRepository.findById(id).orElse(null);

        if (Objects.nonNull(topico)) {
            topico.setTitulo(topicoUpdateRequest.getTitulo());
            topico.setMensagem(topicoUpdateRequest.getMensagem());

            return new TopicoResponseDTO(topico);
        }
        return null;
    }

    @Transactional
    public void topicDelete(Long id) {

        if (Objects.nonNull(id)) {
            topicoRepository.deleteById(id);
        }
    }
}
