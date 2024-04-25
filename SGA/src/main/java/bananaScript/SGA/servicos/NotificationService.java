package bananaScript.SGA.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.repositorios.AtivosRepositorio;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;
@Service
public class NotificationService {
	
	@Autowired
	private AtivosRepositorio ativosRepo;
	@Autowired
	private NotificacaoRepositorio notiRepo;
	
	public void salvarNotificacao(Ativos ativo) {
		Notificacao noti = new Notificacao();
		noti.setAtivoNumero(ativo.getNumAtivo());
		noti.setUsuario(ativo.getNome());
		noti.setDataExpiracao(ativo.getDataManutencao());
		String rota = "http://localhost:8080/notifica/cadastrar";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Notificacao> requestEntity = new HttpEntity<>(noti, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(rota, requestEntity, String.class);
	}
	
	public void atualizarNotificacao(Long id) {
		Ativos ativo = ativosRepo.findById(id).orElse(null);
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			if(noti.getAtivoNumero().equals(ativo.getNumAtivo())){
				if(noti.getDataExpiracao() != ativo.getDataManutencao()) {
					noti.setDataExpiracao(ativo.getDataManutencao());
					notiRepo.save(noti);
				}
			}
		}
	}
	
	
	public List<Ativos> getAtivosExpirados(){
		List<Ativos> ativosExpirados = new ArrayList<>();
		List<Ativos> todosAtivos = ativosRepo.findAll();
		Date dataAtual = new Date();
		for(Ativos ativo : todosAtivos) {
			if(ativo.getDataManutencao().before(dataAtual)) {
				ativosExpirados.add(ativo);
			}
		}
		Iterator<Ativos> iterator = ativosExpirados.iterator();
		while(iterator.hasNext()) {
			Ativos ativo = iterator.next();
			if(ativo.getDataManutencao().after(dataAtual)) {
				iterator.remove();
			}
		}
		return ativosExpirados;
	}
}
