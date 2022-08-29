package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import br.com.alura.forum.controllers.dto.requests.TopicoUpdateRequestDTO;
import br.com.alura.forum.controllers.dto.responses.TopicoDetailResponseDTO;
import br.com.alura.forum.controllers.dto.requests.TopicoRequestDTO;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import br.com.alura.forum.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	private TopicoService topicoService;

	@GetMapping
	public Page<TopicoResponseDTO> topicList(@RequestParam(required = false) String cursoNome,
											 @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination) {
		return topicoService.topicList(cursoNome, pagination);
	}

	@PostMapping
	public ResponseEntity<TopicoResponseDTO> createTopic(@Valid @RequestBody TopicoRequestDTO topicoRequest,
											   UriComponentsBuilder uriBuilder) {

		TopicoResponseDTO topico = topicoService.createTopic(topicoRequest);

		URI uri = uriBuilder.path("/topicos/{id}")
					.buildAndExpand(topico.getId())
					.toUri();

		return ResponseEntity
				.created(uri)
				.body((topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetailResponseDTO> topicDetail(@PathVariable("id") Long id) {
		TopicoDetailResponseDTO topico = topicoService.topicDetail(id);

		if (Objects.nonNull(topico)) {
			return ResponseEntity.ok().body(topico);
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<TopicoResponseDTO> topicUpdate(@PathVariable("id") Long id,
														 @Valid
														 @RequestBody
														 TopicoUpdateRequestDTO topicoUpdateRequest) {
		TopicoResponseDTO topico = topicoService.topicUpdate(id, topicoUpdateRequest);

		if (Objects.isNull(topico)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(topico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> topicDelete(@PathVariable("id") Long id) {

		if (Objects.nonNull(id)) {
			topicoService.topicDelete(id);
			return ResponseEntity.ok().build();
		}

		return  ResponseEntity.notFound().build();
	}
}
