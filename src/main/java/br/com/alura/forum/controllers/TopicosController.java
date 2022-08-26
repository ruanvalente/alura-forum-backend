package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import br.com.alura.forum.controllers.dto.TopicoDetailResponseDTO;
import br.com.alura.forum.controllers.dto.TopicoRequestDTO;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.forum.controllers.dto.TopicoResponseDTO;
import br.com.alura.forum.models.Topico;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoReposiory;

	@GetMapping
	public List<TopicoResponseDTO> topicoList(@RequestParam(required = false) String cursoNome) {
		if (Objects.isNull(cursoNome)) {
			List<Topico> topicoList = topicoRepository.findAll();
			return TopicoResponseDTO.converter(topicoList);
		}
		List<Topico> topico = topicoRepository.findByCursoNome(cursoNome);
		return TopicoResponseDTO.converter(topico);
	}

	@PostMapping
	public ResponseEntity<TopicoResponseDTO> cadastrarTopico(@Valid @RequestBody TopicoRequestDTO topicoRequest, UriComponentsBuilder uriBuilder) {
		Topico topico = topicoRequest.convert(cursoReposiory);
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}")
					.buildAndExpand(topico.getId())
					.toUri();

		return ResponseEntity
				.created(uri)
				.body(new TopicoResponseDTO(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetailResponseDTO> topicoDetail(@PathVariable("id") Long id) {
		Topico topico = topicoRepository.findById(id).orElse(null);

		if (Objects.nonNull(topico)) {
			return ResponseEntity.ok().body( new TopicoDetailResponseDTO(topico));
		}

		return ResponseEntity.notFound().build();
	}

}
