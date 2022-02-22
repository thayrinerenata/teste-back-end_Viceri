package com.viceri.backgerenciadortarefas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> userLogin) {
		return usuarioService.logar(userLogin).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@GetMapping("/buscar/tudo")
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		List<Usuario> list = usuarioRepository.findAll();
		if(list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	@GetMapping("/buscar/{idUsuario}")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(value = "idUsuario") Long idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@GetMapping("/buscar/email/{emailUsuario}")
	public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable(value = "emailUsuario") String emailUsuario) {
		Optional<Usuario> usuario = usuarioReposotory.findByEmail(emailUsuario);
		if(usuario.isPresent()) {
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
		}
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.findById(usuario.getIdUsuario())
				.map(resp -> ResponseEntity.status(200).body(usuarioRepository.save(usuario)))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@DeleteMapping
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		if(usuario.isPresent()) {
			usuarioRepository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
		
	}
	
}
