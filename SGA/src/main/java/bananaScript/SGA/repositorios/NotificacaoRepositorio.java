package bananaScript.SGA.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bananaScript.SGA.entidades.Notificacao;

public interface NotificacaoRepositorio extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByAtivoNumero(String ativoNumero);
}

