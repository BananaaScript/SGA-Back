package bananaScript.SGA.DTO;

import bananaScript.SGA.roles.RoleUsuario;

public record RegisterDTO(String nome, String senha, RoleUsuario role) {
}
