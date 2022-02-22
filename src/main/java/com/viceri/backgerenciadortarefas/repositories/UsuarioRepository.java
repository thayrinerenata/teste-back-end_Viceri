package com.viceri.backgerenciadortarefas.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional <Usuario> findByEmail (String email);

}
