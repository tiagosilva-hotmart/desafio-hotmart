package br.com.hotmart.desafiohotmart.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.hotmart.desafiohotmart.entity.Usuario;

/**
 * Classe que representa um UsuarioVO.
 * 
 * @author Tiago
 *
 */
public class UsuarioVO implements BaseVO, BaseResponseVO {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -3722017452589728946L;

	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotBlank
	@Size(max = 50)
	private String nick;
		
	@NotBlank
	@Size(max = 150)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String senha;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * Responsável por retornar uma entidade de usuário.
	 * 
	 * @return
	 */
	public Usuario toUsuario(){
		
		Usuario usuario = new Usuario();
		  
	    usuario.setLogin(this.getEmail());
	    usuario.setNickName(this.getNick());
	    usuario.setSenha(this.getSenha());
	    usuario.setNome(this.getNome());
	    
	    return usuario;
		
	}
	
}