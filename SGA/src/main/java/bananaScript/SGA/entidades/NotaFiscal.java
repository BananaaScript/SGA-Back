package bananaScript.SGA.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class NotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column
	public Long idAtivo;
	@Column
	public String empresa;
	@Column
	public String razaoSocial;
	@Column
	public String cnpj;
	@Column
	public String descricao;
	@Column
	@Lob
	public byte[] notaFiscal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdAtivo() {
		return idAtivo;
	}
	public void setIdAtivo(Long idAtivo) {
		this.idAtivo = idAtivo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(byte[] notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	
	
}
