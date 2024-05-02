package bananaScript.SGA.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;
import bananaScript.SGA.servicos.NotificationService;

@RestController
@RequestMapping("/notifica")
public class NotificacaoControle {
	
	@Autowired
	private NotificacaoRepositorio repositorio;
	
	@Autowired
	private NotificationService notificaService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listar")
	public List<Notificacao> obterNotificacao(){
		return repositorio.findAll();
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/expirados")
	public List<Ativos> verificarDatas(){
		List<Ativos> ativosExpirados = notificaService.getAtivosExpirados();
		return ativosExpirados;
	}
}
