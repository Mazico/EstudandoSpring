package br.com.apex.api.colaboradores.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.apex.api.colaboradores.model.ColaboradorModel;
import br.com.apex.api.colaboradores.repository.ColaboradorRepository;
import br.com.apex.api.colaboradores.service.CadastroColaboradorService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private CadastroColaboradorService cadastroColaborador;
	
	@GetMapping
	public List<ColaboradorModel> listar(){
		return colaboradorRepository.findAll();
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity <ColaboradorModel> buscar(@PathVariable Integer codigo){
		Optional<ColaboradorModel> colaborador = colaboradorRepository.findById(codigo);
		
		if(colaborador.isPresent()) {
			return ResponseEntity.ok(colaborador.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ColaboradorModel adicionar(@Valid @RequestBody ColaboradorModel colaboradorModel) {
		return cadastroColaborador.salvar(colaboradorModel);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ColaboradorModel> atualizar(@Valid @PathVariable Integer codigo, 
			@RequestBody ColaboradorModel colaborador){
		
		if(!colaboradorRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
					
		}
		
		colaborador.setCodigo(codigo);
		colaborador = cadastroColaborador.salvar(colaborador);
		
		return ResponseEntity.ok(colaborador);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remover(@PathVariable Integer codigo){
		if(!colaboradorRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroColaborador.excluir(codigo);
		
		return ResponseEntity.noContent().build();
		
	}
		
}