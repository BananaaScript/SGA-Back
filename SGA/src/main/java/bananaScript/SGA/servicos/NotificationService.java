package bananaScript.SGA.servicos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.repositorios.AtivosRepositorio;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;
import jakarta.annotation.PostConstruct;
@Service
public class NotificationService {
	
	@Autowired
	private AtivosRepositorio ativosRepo;
	@Autowired
	private NotificacaoRepositorio notiRepo;
	
	@PostConstruct
	public void init() {
		atualizarDias();
	}
	
	public void salvarNotificacao(Ativos ativo) {
		Notificacao noti = new Notificacao();
		noti.setId_usuario(ativo.getId_responsavel());
		noti.setAtivoNumero(ativo.getNumAtivo());
		noti.setUsuario(ativo.getResponsavel());
		noti.setDataExpiracao(ativo.getDataManutencao());
		
		LocalDate dataManutencao = ativo.getDataManutencao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long diferenca = ChronoUnit.DAYS.between(LocalDate.now(), dataManutencao);
		noti.setDias(diferenca);
		
		notiRepo.save(noti);
	}
	
	
	public void atualizarNotificacao(Long id) {
		Ativos ativo = ativosRepo.findById(id).orElse(null);
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			if(noti.getAtivoNumero().equals(ativo.getNumAtivo())){
				if(noti.getDataExpiracao() != ativo.getDataManutencao()) {
					noti.setDataExpiracao(ativo.getDataManutencao());
					
					LocalDate dataManutencao = ativo.getDataManutencao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					long diferencaUpdate = ChronoUnit.DAYS.between(LocalDate.now(), dataManutencao);
					noti.setDias(diferencaUpdate);
					
					notiRepo.save(noti);
				}
			}
		}
	}
	
	public void deletarNotificacao(Long id) {
		Ativos ativo = ativosRepo.findById(id).orElse(null);
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			if(noti.getAtivoNumero().equals(ativo.getNumAtivo())) {
				notiRepo.deleteById(noti.getId());
			}
		}
	}
	
	public void atualizarDias() {
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			LocalDate dataManutencao = noti.getDataExpiracao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			long diferencaUpdate = ChronoUnit.DAYS.between(LocalDate.now(), dataManutencao);
			noti.setDias(diferencaUpdate);
			notiRepo.save(noti);
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
