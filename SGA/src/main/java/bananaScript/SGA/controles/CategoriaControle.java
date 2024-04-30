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

import bananaScript.SGA.entidades.Categoria;
import bananaScript.SGA.repositorios.CategoriaRepositorio;

@RestController
@RequestMapping("/categoria")
public class CategoriaControle {
	
	@Autowired
	private CategoriaRepositorio repo;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@PostMapping("/cadastrar")
	public void cadastrarCategoria(@RequestBody Categoria categoria) {
		repo.save(categoria);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@GetMapping("/listar")
	public List<Categoria> listarCategoria(){
		return repo.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@PutMapping("/atualizar/{id}")
	public void atualizarCategoria(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
		Categoria cate = repo.findById(id).orElse(null);
		if (cate != null) {
			cate.setNome(novaCategoria.getNome());
			cate.setDescricao(novaCategoria.getDescricao());
			cate.setComplemento(novaCategoria.getComplemento());
			repo.save(cate);
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@DeleteMapping("/deletar/{id}")
	public void deletarCategoria(@PathVariable Long id) {
		repo.deleteById(id);
	}
}
