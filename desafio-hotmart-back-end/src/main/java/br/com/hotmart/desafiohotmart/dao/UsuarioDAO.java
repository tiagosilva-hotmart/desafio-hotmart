package br.com.hotmart.desafiohotmart.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.desafiohotmart.entity.Usuario;

/**
 * Interface responsável por realizar
 * as operações na base de dados
 * relativas a entidade usuário.
 * 
 * @author Tiago
 *
 */
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {

	/**
	 * Método responsável por obter um usuário
	 * de acordo com o seu login.
	 * 
	 * @param login
	 * @return
	 */
	Usuario findByLogin(String login);
}