package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import br.com.alura.forum.controllers.dto.requests.TopicoUpdateRequestDTO;
import br.com.alura.forum.controllers.dto.responses.TopicoDetailResponseDTO;
import br.com.alura.forum.controllers.dto.requests.TopicoRequestDTO;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.alura.forum.controllers.dto.responses.TopicoResponseDTO;
import br.com.alura.forum.models.Topico;
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
	public List<TopicoResponseDTO> topicList(@RequestParam(required = false) String cursoNome) {
		if (Objects.isNull(cursoNome)) {
			List<Topico> topicoList = topicoRepository.findAll();
			return TopicoResponseDTO.converter(topicoList);
		}
		List<Topico> topico = topicoRepository.findByCursoNome(cursoNome);
		return TopicoResponseDTO.converter(topico);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoResponseDTO> createTopic(@Valid @RequestBody TopicoRequestDTO topicoRequest,
											   UriComponentsBuilder uriBuilder) {
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
	@Transactional
	public ResponseEntity<TopicoDetailResponseDTO> topicDetail(@PathVariable("id") Long id) {
		Topico topico = topicoRepository.findById(id).orElse(null);

		if (Objects.nonNull(topico)) {
			return ResponseEntity.ok().body( new TopicoDetailResponseDTO(topico));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoResponseDTO> topicUpdate(@PathVariable("id") Long id,
														 @Valid
														 @RequestBody
														 TopicoUpdateRequestDTO topicoUpdateRequest) {
		Topico topico = topicoRepository.findById(id).orElse(null);

		if (Objects.isNull(topico)) {
			return ResponseEntity.notFound().build();
		}

		topico.setTitulo(topicoUpdateRequest.getTitulo());
		topico.setMensagem(topicoUpdateRequest.getMensagem());

		return ResponseEntity.ok().body(new TopicoResponseDTO(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> topicDelete(@PathVariable("id") Long id) {

		if (Objects.nonNull(id)) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return  ResponseEntity.notFound().build();
	}
}
