package bananaScript.SGA.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import bananaScript.SGA.entidades.Ativos;

public interface AtivosRepositorio extends JpaRepository<Ativos, Long> {
}
