package bananaScript.SGA.entidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ativos{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer id_modelo;
	
	@Column
	private String nome;
	
	@Column(unique = true)
	private Integer numero_ativo;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dataManutencao;
	
	@Column
	private String rua;
	
	@Column
	private String bairro;
	
	@Column
	private String complemento;
	
	@Column
	private Integer numero;
	
	@Column
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero_ativo() {
		return numero_ativo;
	}

	public void setNumero_ativo(Integer numero_ativo) {
		this.numero_ativo = numero_ativo;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
