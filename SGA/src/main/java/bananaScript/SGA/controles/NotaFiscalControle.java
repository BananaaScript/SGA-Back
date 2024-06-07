package bananaScript.SGA.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.entidades.NotaFiscal;
import bananaScript.SGA.repositorios.NotaFiscalRepositorio;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalControle {
	@Autowired
	private NotaFiscalRepositorio repo;
	
	@PostMapping("/enviar")
	public ResponseEntity<?> enviar(@RequestBody NotaFiscal nota){
		repo.save(nota);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping("/listar")
	public ResponseEntity<List<NotaFiscal>> listar(){
		List<NotaFiscal> notasFiscais = repo.findAll();
		if(notasFiscais.isEmpty()) {
			return new ResponseEntity<List<NotaFiscal>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<NotaFiscal>>(notasFiscais, HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody NotaFiscal newData){
		NotaFiscal notaFiscal = repo.findById(id).orElse(null);
		if (notaFiscal != null) {
			notaFiscal.setIdAtivo(newData.getIdAtivo());
			notaFiscal.setEmpresa(newData.getEmpresa());
			notaFiscal.setRazaoSocial(newData.getRazaoSocial());
			notaFiscal.setCnpj(newData.getCnpj());
			notaFiscal.setDescricao(newData.getDescricao());
			notaFiscal.setNotaFiscal(newData.getNotaFiscal());
			repo.save(notaFiscal);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		NotaFiscal notaFiscal = repo.findById(id).orElse(null);
		if(notaFiscal != null) {
			repo.delete(notaFiscal);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
