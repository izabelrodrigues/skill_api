package br.com.izabelrodrigues.skill.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.izabelrodrigues.skill.api.converter.SkillEntityConverter;
import br.com.izabelrodrigues.skill.api.dto.SkillDto;
import br.com.izabelrodrigues.skill.api.model.Skill;
import br.com.izabelrodrigues.skill.api.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService service;

	/**
	 * Chamada exemplo: localhost:8080/skills?page=0&size=10&sort=nome,desc
	 *
	 * @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size =
	 *                       10): Se não passar a ordenação, vai ordenar pelo id
	 *                       desc e cada página vai conter 10 registros
	 * 
	 *                       Para funcionar, a classe principal tem que estar
	 *                       anotada com @EnableSpringDataWebSupport
	 *
	 * @param nome
	 * @param paginacao
	 * @return
	 */
	@GetMapping
	@Cacheable(value = "listar_skill_cache")
	public Page<SkillDto> listar(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		return service.listar(paginacao, nome);
	}

	/**
	 * Busca uma skill pelo id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SkillDto> buscarPorId(@PathVariable Long id) {
		Optional<Skill> skillOptional = getById(id);
		if (skillOptional.isPresent()) {
			return ResponseEntity.ok(SkillEntityConverter.convertToDto(skillOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 *
	 * @param skillDto
	 * @param uriBuilder
	 * @return
	 */
	@PostMapping
	@Transactional
	@CacheEvict(value = "listar_skill_cache", allEntries = true)
	public ResponseEntity<SkillDto> cadastrar(@RequestBody @Valid SkillDto skillDto, UriComponentsBuilder uriBuilder) {

		Skill skill = SkillEntityConverter.convertFromDto(skillDto);
		service.salvar(skill);

		URI uri = uriBuilder.path("/skills/{id}").buildAndExpand(skill.getId()).toUri();
		return ResponseEntity.created(uri).body(SkillEntityConverter.convertToDto(skill));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listar_skill_cache", allEntries = true)
	public ResponseEntity<SkillDto> atualizar(@PathVariable Long id, @RequestBody @Valid SkillDto skillDto) {
		Optional<Skill> skillOptional = getById(id);
		if (skillOptional.isPresent()) {
			skillDto.setId(id);
			Skill skill = SkillEntityConverter.convertFromDto(skillDto);
			service.atualizar(skill);
			return ResponseEntity.ok(skillDto);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listar_skill_cache", allEntries = true)
	public ResponseEntity<Skill> remover(@PathVariable Long id) {
		Optional<Skill> skillOptional = getById(id);
		if (skillOptional.isPresent()) {
			service.remover(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	private Optional<Skill> getById(Long id) {
		return service.buscarPorId(id);
	}

}
