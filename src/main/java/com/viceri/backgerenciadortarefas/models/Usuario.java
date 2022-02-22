package com.viceri.backgerenciadortarefas.models;

@Entity
@Table(name="tb_usuarios")
public class Usuario {
	
	@Id
	@GenerateValue(strategy=GenerationType.IDENTITY)
	private long idUsuario;
	
	@NotBlank(message="Este campo é obrigatório!")
	@Size(min=3, max= 100)
	private String nome;
	
	@Schema(example="email@email.com.br")
	@Email(message="Por favor, informe um e-mail correto!")
	@NotBlank(message="Este campo é obrigatório!")
	@Size(min=3, max=100)
	private String email;
	
	@NotBlank(message="Este campo é obrigatório!")
	@Size(min=3, max= 100)
	private String senha;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Tarefa> tarefa=new ArrayList<>();

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}
	
}
