package bananaScript.SGA.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;

@RestController
@RequestMapping("/notifica")
public class NotificacaoControle {
	
	@Autowired
	private NotificacaoRepositorio repositorio;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/cadastrar")
	public void cadastrarNotificacao(@RequestBody Notificacao notificacao) {
		repositorio.save(notificacao);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listar")
	public List<Notificacao> obterNotificacao(){
		return repositorio.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/atualizar/{id}")
	public void atualizarNotificacao (@PathVariable Long id, @RequestBody Notificacao novaNoti) {
		Notificacao notificacao = repositorio.findById(id).orElse(null);
		if (notificacao != null) {
			notificacao.setData_cadastro(novaNoti.getData_cadastro());
			notificacao.setData_vencimento(novaNoti.getData_vencimento());
			notificacao.setUsuario(novaNoti.getUsuario());
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/deletar/{id}")
	public void deletarNotificacao(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	
}
