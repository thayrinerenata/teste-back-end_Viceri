package com.viceri.backgerenciadortarefas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_tarefas")
public class Tarefa {
	
	@Id
	@GenerateValue(strategy=GenerationType.IDENTITY)
	private long idTarefa;
	
	@NotBlank
	@Size(min=3, max= 250)
	private String titulo;
	
	@NotBlank
	@Size(min=3, max= 1300)
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	@JsonIgnoreProperties("tarefa")
	private Usuario usuario;

	public long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
