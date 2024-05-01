package bananaScript.SGA.entidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notificacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "numero_ativo")
	private String ativoNumero;
	@Column
	private String usuario;
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dataExpiracao;
	@Column
	private Long dias;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAtivoNumero() {
		return ativoNumero;
	}
	public void setAtivoNumero(String ativoNumero) {
		this.ativoNumero = ativoNumero;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public Long getDias() {
		return dias;
	}
	public void setDias(Long dias) {
		this.dias = dias;
	}
	
	
	
}
