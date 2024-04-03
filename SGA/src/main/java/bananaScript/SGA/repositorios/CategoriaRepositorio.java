package bananaScript.SGA.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import bananaScript.SGA.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

}
