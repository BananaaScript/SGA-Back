package bananaScript.SGA.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.DTO.AuthenticationDTO;
import bananaScript.SGA.DTO.LoginResponseDTO;
import bananaScript.SGA.DTO.RegisterDTO;
import bananaScript.SGA.entidades.Usuario;
import bananaScript.SGA.repositorios.UsuarioRepositorio;
import bananaScript.SGA.roles.RoleUsuario;
import bananaScript.SGA.segurança.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepositorio repositorio;
    @Autowired
    private TokenService tokenService;
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.nome(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Usuario usuario = (Usuario) auth.getPrincipal();
        RoleUsuario role = usuario.getRole();
        
        var token = tokenService.generateToken(usuario, role);
        
        return ResponseEntity.ok(new LoginResponseDTO(token, role));
    }

    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if(this.repositorio.findByNome(data.nome()) != null) return ResponseEntity.badRequest().build();

        Usuario newUser = new Usuario(data.nome(), data.senha(), data.role());

        this.repositorio.save(newUser);

        return ResponseEntity.ok().build();
    }
    
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "otário 💀";
    }
   
    
}