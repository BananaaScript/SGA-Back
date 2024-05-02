package bananaScript.SGA.entidades;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 
 */
@Entity
public class Ativos{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long id_modelo;
	
	@Column
	private String nome_modelo;
	
	@Column
	private Integer id_categoria;
	
	@Column
	private String nome_categoria;
	
	
	
	@Column
	private String nome;

	@Column
	private String descricao;
	
	@Column
	private String complementoAtivo;
	
	@Column
	private String responsavel;
	
	@Column
	private Long id_responsavel;
	
	@Column
	private String emissorNF;
	
	@Column
	private String numeroSerie;
	
	@Column
	private String valor;
	
	@Column(unique = true)
	private String numAtivo;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String dataTransacao;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dataManutencao;
	
	@Column
	private String documento_fiscal;
	
	@Column
	private String garantia;
	
	@Column
	private String rua;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
	
	@Column
	private String pais;
	
	@Column
	private String complemento;
	
	@Column
	private Long numero;
	
	@Column
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Long id_modelo) {
		this.id_modelo = id_modelo;
	}

	public String getNome_modelo() {
		return nome_modelo;
	}

	public void setNome_modelo(String nome_modelo) {
		this.nome_modelo = nome_modelo;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getComplementoAtivo() {
		return complementoAtivo;
	}

	public void setComplementoAtivo(String complementoAtivo) {
		this.complementoAtivo = complementoAtivo;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEmissorNF() {
		return emissorNF;
	}

	public void setEmissorNF(String emissorNF) {
		this.emissorNF = emissorNF;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNumAtivo() {
		return numAtivo;
	}

	public void setNumAtivo(String numAtivo) {
		this.numAtivo = numAtivo;
	}

	public String getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getDocumento_fiscal() {
		return documento_fiscal;
	}

	public void setDocumento_fiscal(String documento_fiscal) {
		this.documento_fiscal = documento_fiscal;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getId_responsavel() {
		return id_responsavel;
	}

	public void setId_responsavel(Long id_responsavel) {
		this.id_responsavel = id_responsavel;
	}
	
	
	
	
	
	
	
}
