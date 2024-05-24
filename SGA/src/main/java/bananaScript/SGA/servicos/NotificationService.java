package bananaScript.SGA.servicos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.entidades.Usuario;
import bananaScript.SGA.repositorios.AtivosRepositorio;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;
import bananaScript.SGA.repositorios.UsuarioRepositorio;
import jakarta.annotation.PostConstruct;
@Service
public class NotificationService {
	
	@Autowired
	private AtivosRepositorio ativosRepo;
	@Autowired
	private NotificacaoRepositorio notiRepo;
	@Autowired
	private EmailService email;
	@Autowired
	private UsuarioRepositorio userRepo;
	
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
	
	@Scheduled(cron = "0 * * * * *")
	public void atualizarDias() {
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			LocalDate dataManutencao = noti.getDataExpiracao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			long diferencaUpdate = ChronoUnit.DAYS.between(LocalDate.now(), dataManutencao);
			noti.setDias(diferencaUpdate);
			notiRepo.save(noti);
		}
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void verificar() {
		List<Notificacao> notis = notiRepo.findAll();
		for(Notificacao noti : notis) {
			Usuario user = userRepo.findById(noti.getId_usuario()).orElse(null);
			if(noti.getDias() <= 0) {
				email.enviarEmail(user.getEmail(), "Data de manutenção expirada", "A data de manutenção do seu ativo expirou!");
			}
			else if(noti.getDias() >=3 || noti.getDias() <= 10) {
				email.enviarEmail(user.getEmail(), "Data de manutenção vai expirar entre 10 e 3 dias!", "Verifique a data de manutenção do seu ativo, ele pode expirar em 10 dias!");
			}
		}
	}
	
	@Scheduled(cron = "0 * * * * *")
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
