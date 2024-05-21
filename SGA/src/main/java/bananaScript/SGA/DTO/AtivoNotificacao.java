package bananaScript.SGA.DTO;

import java.util.Date;

public class AtivoNotificacao {
    private Long idAtivo;
    private String nome;
    private String descricao;
    private String responsavel;
    private String valor;
    private String numAtivo;
    private Date dataManutencao;
    private Date dataExpiracao;
    private Long dias;

    // Getters and Setters
    public Long getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(Long idAtivo) {
        this.idAtivo = idAtivo;
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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

    public Date getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
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
