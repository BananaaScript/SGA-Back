package bananaScript.SGA.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bananaScript.SGA.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
	Usuario findByNome(String nome);

	Optional<Usuario> findById(Long id);

	void deleteById(Long id);
}
