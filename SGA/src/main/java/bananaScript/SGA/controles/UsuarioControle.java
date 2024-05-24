package bananaScript.SGA.controles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import bananaScript.SGA.DTO.UsuarioDTO;
import bananaScript.SGA.entidades.Usuario;
import bananaScript.SGA.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/cadastrar")
    public void cadastrarAtivos(@RequestBody Usuario usuario) {
        repositorio.save(usuario);
    }
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listar")
	public List<Usuario> obeterUsuarios(){
		return repositorio.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/atualizar/{id}")
	public void atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
	    Usuario usuario = repositorio.findById(id).orElse(null);
	    if (usuario != null) {
	        usuario.setNome(novoUsuario.getNome());
	        usuario.setSenha(novoUsuario.getSenha());
	        usuario.setCpf(novoUsuario.getCpf());
	        usuario.setRg(novoUsuario.getRg());
	        usuario.setTelefone(novoUsuario.getTelefone());
	        usuario.setEmail(novoUsuario.getEmail());
	        repositorio.save(usuario);
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Transactional
	@DeleteMapping("/deletar/{id}")
	public void deletarUsuario(@PathVariable Long id) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuarioLogado = authentication.getName();
	    Usuario usuarioLogado = repositorio.findByNome(nomeUsuarioLogado);
	    
	    if (usuarioLogado.getId().equals(id)) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode deletar um usuário logado ou um usuário relacionado a outros registros");
	    } else {
	        repositorio.deleteById(id);
	    }
	}

	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/informacoes")
    public List<UsuarioDTO> informacoes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nome = authentication.getName();
        Usuario usuario = repositorio.findByNome(nome);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getCpf(), usuario.getTelefone());
        
        List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
        listaUsuarioDTO.add(usuarioDTO);
        
        return listaUsuarioDTO;
    }
}

