package br.com.estudo.api.agenda.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estudo.api.agenda.controller.dto.ContatoDto;
import br.com.estudo.api.agenda.controller.form.ContatoForm;
import br.com.estudo.api.agenda.modelo.Contato;
import br.com.estudo.api.agenda.repository.AgendaRepository;
import br.com.estudo.api.agenda.repository.ContatoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "AgendaController", consumes ="application/json", produces = "application/json",  description = "Administra contatos da agenda")
@RestController
@RequestMapping("/contatos")
public class AgendaController {
	
	@Autowired
	ContatoRepository contatoRepository; 
	@Autowired
	AgendaRepository agendaRepository;
	
	public AgendaController(ContatoRepository contatoRepository){
		this.contatoRepository = contatoRepository;
	}
	
	@ApiOperation(value = "Lista os contatos ", response = Iterable.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor") })
	
	@GetMapping
	public List<ContatoDto> listaContatos(String nome) {
		
		List<ContatoDto> contatos;
		if(nome != null) {
			contatos = contatoRepository.findByNome(nome).stream().map(ContatoDto::new).collect(Collectors.toList());
		}else {
			contatos = contatoRepository.findAll().stream().map(ContatoDto::new).collect(Collectors.toList());
		}
				
		return contatos;
	}
	
	
	@ApiOperation(value = "Cadastra contatos ", response = ContatoDto.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "Cadastro efetuado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping
	@Transactional
	public ResponseEntity<ContatoDto>  cadastraContatoAgenda(@RequestBody @Valid ContatoForm form, UriComponentsBuilder builder) {
		
		Contato contato = form.converter(agendaRepository);
		agendaRepository.save(contato.getAgenda());
		contatoRepository.save(contato);
		URI uri =  builder.path("/contatos/{id}").buildAndExpand(contato.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ContatoDto(contato)); 
	}
	
	
	@ApiOperation(value = "Detalha um contato ", response = ContatoDto.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 202, message = "Sucesso|ok"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public ResponseEntity<ContatoDto> detalhar(@PathVariable Long id) {
		
		Optional<Contato> contatoOptional = contatoRepository.findById(id);
		if(contatoOptional.isPresent()) {
			return ResponseEntity.ok(new ContatoDto(contatoOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@ApiOperation(value = "Altera nome, telefone e email do contato ", response = ContatoDto.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 202, message = "Sucesso|ok"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContatoDto>  atualizar(@PathVariable Long id, @RequestBody @Valid ContatoForm form) {
		
		Optional<Contato> contatoOptional = contatoRepository.findById(id);
		if(contatoOptional.isPresent()) {
			Contato contato = form.atualizar(id, contatoRepository, agendaRepository);
			return ResponseEntity.ok(new ContatoDto(contato));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@ApiOperation(value = "Exclui o contato ", response = ContatoDto.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 204, message = "Contato excluido com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
				
		Optional<Contato> contatoOptional = contatoRepository.findById(id);
		if(contatoOptional.isPresent()) {
			contatoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}


}
