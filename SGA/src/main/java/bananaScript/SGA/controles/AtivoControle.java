package bananaScript.SGA.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.Usuario;
import bananaScript.SGA.repositorios.AtivosRepositorio;
import bananaScript.SGA.repositorios.UsuarioRepositorio;
import bananaScript.SGA.servicos.NotificationService;

@RestController
@RequestMapping("/ativo")
public class AtivoControle {
	
	@Autowired
	private AtivosRepositorio repositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private NotificationService notifica;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/cadastrar")
    public void cadastrarAtivos(@RequestBody Ativos ativo) {
		notifica.salvarNotificacao(ativo);
        repositorio.save(ativo);
    }
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listar")
	public List<Ativos> obterAtivos(){
		return repositorio.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/atualizar/{id}")
	public void atualizarAtivo (@PathVariable Long id, @RequestBody Ativos novoAtivo) {
		Ativos ativo = repositorio.findById(id).orElse(null);
		if (ativo != null) {}
			ativo.setNome(novoAtivo.getNome());
			ativo.setDescricao(novoAtivo.getDescricao());
			ativo.setComplementoAtivo(novoAtivo.getComplementoAtivo());
			ativo.setResponsavel(novoAtivo.getResponsavel());
			ativo.setEstado(novoAtivo.getEstado());
			ativo.setNumeroSerie(novoAtivo.getNumeroSerie());
			ativo.setValor(novoAtivo.getValor());
			ativo.setDataTransacao(novoAtivo.getDataTransacao());
			ativo.setDataManutencao(novoAtivo.getDataManutencao());
			ativo.setRua(novoAtivo.getRua());
			ativo.setBairro(novoAtivo.getBairro());
			ativo.setComplemento(novoAtivo.getComplemento());
			ativo.setNumero(novoAtivo.getNumero());
			ativo.setCep(novoAtivo.getCep());
			ativo.setId_modelo(novoAtivo.getId_modelo());
			ativo.setNome_modelo(novoAtivo.getNome_modelo());
			ativo.setId_categoria(novoAtivo.getId_categoria());
			ativo.setNome_categoria(novoAtivo.getNome_categoria());
			repositorio.save(ativo);
			notifica.atualizarNotificacao(ativo.getId());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/deletar/{id}")
	public void deletarAtivo (@PathVariable Long id) {
		notifica.deletarNotificacao(id);
		repositorio.deleteById(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativos")
	public Double obterValorTodosAtivos() {
	    List<Ativos> ativos = repositorio.findAll();
	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	            soma += Double.parseDouble(ativo.getValor());
	        }
	    }
	    return soma;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativospormodelo/{id_modelo}")
	public Double obterValorTotalAtivosPorModelo(@PathVariable Long id_modelo) {
	    List<Ativos> ativos = repositorio.findAll();

	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_modelo().equals(id_modelo)) {
	            if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	            	
	                soma += Double.parseDouble(ativo.getValor());
	            }
	        }
	    }
	    return soma;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativosporcategoria/{id_categoria}")
	public Double obterValorTotalAtivosPorCategoria(@PathVariable Long id_categoria) {
	    List<Ativos> ativos = repositorio.findAll();

	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_categoria().equals(id_categoria)) {
	            if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	                soma += Double.parseDouble(ativo.getValor());
	            }
	        }
	    }
	    return soma;
	}


	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/usuario")
	public List<Ativos> obeterAtivos(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String nomeUsuario = authentication.getName();
		Usuario usuario = usuarioRepositorio.findByNome(nomeUsuario);
		
		return repositorio.findAll().stream()
									.filter(ativo -> ativo.getId_responsavel().equals(usuario.getId()))
									.collect(Collectors.toList());
	};
	
	
}
