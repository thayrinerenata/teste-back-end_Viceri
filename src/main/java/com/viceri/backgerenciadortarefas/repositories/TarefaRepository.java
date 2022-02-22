package com.viceri.backgerenciadortarefas.repositories;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	public List<Tarefa> findAllByTituloContainingIgnoreCase (String titulo);

}
