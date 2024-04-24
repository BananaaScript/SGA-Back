package bananaScript.SGA.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bananaScript.SGA.repositorios.UsuarioRepositorio;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepositorio repostiorio;
    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        return repostiorio.findByNome(nome);
    }
}
