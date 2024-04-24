package bananaScript.SGA.roles;

public enum RoleUsuario {
    ADMIN("admin"),
    USER("user");

    private String role;

    RoleUsuario(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
