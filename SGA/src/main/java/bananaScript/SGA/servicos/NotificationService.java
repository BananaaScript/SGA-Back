package bananaScript.SGA.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.repositorios.AtivosRepositorio;

@Service
public class NotificationService {
	
	@Autowired
	private AtivosRepositorio ativosRepo;
	
	public List<Ativos> getAtivosExpirados(){
		List<Ativos> ativosExpirados = new ArrayList<>();
		List<Ativos> todosAtivos = ativosRepo.findAll();
		Date dataAtual = new Date();
		
		for(Ativos ativo : todosAtivos) {
			if(ativo.getDataManutencao().before(dataAtual)) {
				ativosExpirados.add(ativo);
			}
		}
		return ativosExpirados;
	}
}
