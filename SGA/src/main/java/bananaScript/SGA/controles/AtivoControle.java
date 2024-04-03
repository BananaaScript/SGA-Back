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

import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.repositorios.AtivosRepositorio;

@RestController
@RequestMapping("/ativo")
public class AtivoControle {
	
	@Autowired
	private AtivosRepositorio repositorio;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/cadastrar")
    public void cadastrarAtivos(@RequestBody Ativos ativo) {
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
			ativo.setNumero_ativo(novoAtivo.getNumero_ativo());
			ativo.setRua(novoAtivo.getRua());
			ativo.setBairro(novoAtivo.getBairro());
			ativo.setComplemento(novoAtivo.getComplemento());
			ativo.setNumero(novoAtivo.getNumero());
			ativo.setCep(novoAtivo.getCep());
			repositorio.save(ativo);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/deletar/{id}")
	public void deletarAtivo (@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	
}
