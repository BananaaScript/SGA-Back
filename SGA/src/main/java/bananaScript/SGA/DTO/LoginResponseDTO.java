package bananaScript.SGA.DTO;

import bananaScript.SGA.roles.RoleUsuario;

public record LoginResponseDTO(String token, RoleUsuario role) {
}
