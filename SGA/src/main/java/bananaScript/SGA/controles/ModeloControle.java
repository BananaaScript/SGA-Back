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

import bananaScript.SGA.entidades.Modelo;
import bananaScript.SGA.repositorios.ModeloRepositorio;


@RestController
@RequestMapping("/modelo")
public class ModeloControle {
	@Autowired
	private ModeloRepositorio repo;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@PostMapping("/cadastrar")
	public void cadastrar(@RequestBody Modelo p) {
		repo.save(p);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@GetMapping("/listar")
	public List<Modelo> listar(){
		return repo.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@PutMapping("/atualizar/{id}")	
	public void atualizar(@PathVariable Long id, @RequestBody Modelo modelo){
		Modelo m = repo.findById(id).orElse(null);
		if(m != null) {
			m.setNome(modelo.getNome());
			m.setDescricao(modelo.getDescricao());
			m.setModelo(modelo.getModelo());
			m.setFabricante(modelo.getFabricante());
			m.setId_categoria(modelo.getId_categoria());
			m.setNome_categoria(modelo.getNome_categoria());
			repo.save(m);
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*") 
	@DeleteMapping("/deletar/{id}")
	public void deleteProduto(@PathVariable Long id) {
		Modelo m = repo.findById(id).orElse(null);
		if(m != null) {
			repo.delete(m);
		}
	}
}