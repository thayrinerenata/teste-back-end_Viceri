package com.viceri.backgerenciadortarefas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.app.models.Postagem;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin("*")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Tarefa>> findAllTarefa() {
		return ResponseEntity.ok(tarefaRepository.findAll());
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Tarefa> findByIdTarefa(@PathVariable (value="id") Long id){
		return tarefaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/Titulo/{Titulo}")
	public ResponseEntity<List<Tarefa>> findAllByTitulo(@PathVariable (value="titulo") String titulo){
		return ResponseEntity.ok(tarefaRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Tarefa> post (@RequestBody Tarefa tarefa){
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Tarefa> put (@RequestBody Tarefa tarefa){
		return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
	}
	
	@DeleteMapping("/apagar/{id}")
	public void delete(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
	}
	
}
