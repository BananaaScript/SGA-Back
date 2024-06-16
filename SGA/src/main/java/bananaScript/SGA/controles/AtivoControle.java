	package bananaScript.SGA.controles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.DTO.AtivoNotificacao;
import bananaScript.SGA.entidades.Ativos;
import bananaScript.SGA.entidades.HistoricoAtivo;
import bananaScript.SGA.entidades.Notificacao;
import bananaScript.SGA.entidades.Usuario;
import bananaScript.SGA.repositorios.AtivosRepositorio;
import bananaScript.SGA.repositorios.HistoricoAtivoRepositorio;
import bananaScript.SGA.repositorios.NotificacaoRepositorio;
import bananaScript.SGA.repositorios.UsuarioRepositorio;
import bananaScript.SGA.servicos.NotificationService;

@RestController
@RequestMapping("/ativo")
public class AtivoControle {
	
	@Autowired
	private AtivosRepositorio repositorio;
	
	@Autowired 
	private HistoricoAtivoRepositorio repositorioHistoricoAtivo;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private NotificacaoRepositorio notificacaoRepositorio;
	
	@Autowired
	private NotificationService notifica;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarAtivos(@RequestBody Ativos ativo) {
		try {
			notifica.salvarNotificacao(ativo);
			repositorio.save(ativo);
			
			HistoricoAtivo historicoAtivo = new HistoricoAtivo();
	        historicoAtivo.setNomeAtivo(ativo.getNome());
	        historicoAtivo.setModelo(ativo.getNome_modelo());
	        historicoAtivo.setNumeroSerie(ativo.getNumAtivo());
	        historicoAtivo.setDataTransacao(LocalDate.now());
	        repositorioHistoricoAtivo.save(historicoAtivo);
	        
			return ResponseEntity.status(HttpStatus.CREATED).body("Ativo cadastrado com sucesso!");
		} catch (DataIntegrityViolationException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número do ativo já está cadastrado");
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listar")
	public List<Ativos> obterAtivos(){
		return repositorio.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/listarHistorico")
	public List<HistoricoAtivo> obterHistorico(){
		return repositorioHistoricoAtivo.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/atualizar/{id}")
	public void atualizarAtivo (@PathVariable Long id, @RequestBody Ativos novoAtivo) {
		Ativos ativo = repositorio.findById(id).orElse(null);
		if (ativo != null) {}
			ativo.setNome(novoAtivo.getNome());
			ativo.setDescricao(novoAtivo.getDescricao());
			ativo.setComplementoAtivo(novoAtivo.getComplementoAtivo());
			ativo.setEstado(novoAtivo.getEstado());
			ativo.setValor(novoAtivo.getValor());
			ativo.setDataTransacao(novoAtivo.getDataTransacao());
			ativo.setDataManutencao(novoAtivo.getDataManutencao());
			ativo.setRua(novoAtivo.getRua());
			ativo.setBairro(novoAtivo.getBairro());
			ativo.setComplemento(novoAtivo.getComplemento());
			ativo.setNumero(novoAtivo.getNumero());
			ativo.setCep(novoAtivo.getCep());
			ativo.setId_modelo(novoAtivo.getId_modelo());
			ativo.setNome_modelo(novoAtivo.getNome_modelo());
			ativo.setId_categoria(novoAtivo.getId_categoria());
			ativo.setNome_categoria(novoAtivo.getNome_categoria());
			ativo.setId_responsavel(novoAtivo.getId_responsavel());
			ativo.setResponsavel(novoAtivo.getResponsavel());		
			repositorio.save(ativo);
			HistoricoAtivo historicoAtivo = new HistoricoAtivo();
	        historicoAtivo.setNomeAtivo(novoAtivo.getNome());
	        historicoAtivo.setModelo(novoAtivo.getNome_modelo());
	        historicoAtivo.setNumeroSerie(novoAtivo.getNumAtivo());
	        historicoAtivo.setDataTransacao(LocalDate.now());
	        repositorioHistoricoAtivo.save(historicoAtivo);
			notifica.atualizarNotificacao(ativo.getId());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/deletar/{id}")
	public void deletarAtivo (@PathVariable Long id) {
		notifica.deletarNotificacao(id);
		repositorio.deleteById(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativos")
	public Double obterValorTodosAtivos() {
	    List<Ativos> ativos = repositorio.findAll();
	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	            soma += Double.parseDouble(ativo.getValor());
	        }
	    }
	    return soma;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativospormodelo/{id_modelo}")
	public Double obterValorTotalAtivosPorModelo(@PathVariable Long id_modelo) {
	    List<Ativos> ativos = repositorio.findAll();

	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_modelo().equals(id_modelo)) {
	            if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	            	
	                soma += Double.parseDouble(ativo.getValor());
	            }
	        }
	    }
	    return soma;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/totalvalorativosporcategoria/{id_categoria}")
	public Double obterValorTotalAtivosPorCategoria(@PathVariable Long id_categoria) {
	    List<Ativos> ativos = repositorio.findAll();

	    double soma = 0.0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_categoria().equals(id_categoria)) {
	            if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
	                soma += Double.parseDouble(ativo.getValor());
	            }
	        }
	    }
	    return soma;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/quantidadeativosporcategoria/{id_categoria}")
	public Long obterQuantidadeAtivosPorCategoria(@PathVariable Long id_categoria) {
	    List<Ativos> ativos = repositorio.findAll();
	    
	    Long quantidade = 0L;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_categoria().equals(id_categoria)) {
	            quantidade++;
	        }
	    }
	    return quantidade;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/quantidadeativospormodelo/{id_modelo}")
	public Long obterQuantidadeAtivosPorModelo(@PathVariable Long id_modelo) {
	    List<Ativos> ativos = repositorio.findAll();
	    
	    Long quantidade = 0L;
	    for (Ativos ativo : ativos) {
	        if (ativo.getId_modelo().equals(id_modelo)) {
	            quantidade++;
	        }
	    }
	    return quantidade;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/quantidadeativos")
	public Long obterQuantidadeTotalAtivos() {
	    List<Ativos> ativos = repositorio.findAll();
	    return (long) ativos.size();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/valorativosantigo")
	public Map<String, Double> obterValoresAtivos() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataUmMesAtras = dataAtual.minusMonths(1);
        LocalDate dataDoisMesesAtras = dataAtual.minusMonths(2);
        LocalDate dataTresMesesAtras = dataAtual.minusMonths(3);
        LocalDate dataQuatroMesesAtras = dataAtual.minusMonths(4);
        LocalDate dataCincoMesesAtras = dataAtual.minusMonths(5);
        LocalDate dataSeisMesesAtras = dataAtual.minusMonths(6);
        LocalDate dataSeteMesesAtras = dataAtual.minusMonths(7);
        LocalDate dataOitoMesesAtras = dataAtual.minusMonths(8);
        LocalDate dataNoveMesesAtras = dataAtual.minusMonths(9);
        LocalDate dataDezMesesAtras = dataAtual.minusMonths(10);
        LocalDate dataOnzeMesesAtras = dataAtual.minusMonths(11);
        LocalDate dataDozeMesesAtras = dataAtual.minusMonths(12);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Ativos> ativos = repositorio.findAll();

        double somaUmMes = 0.0;
        double somaDoisMeses = 0.0;
        double somaTresMeses = 0.0;
        double somaQuatroMeses = 0.0;
        double somaCincoMeses = 0.0;
        double somaSeisMeses = 0.0;
        double somaSeteMeses = 0.0;
        double somaOitoMeses = 0.0;
        double somaNoveMeses = 0.0;
        double somaDezMeses = 0.0;
        double somaOnzeMeses = 0.0;
        double somaDozeMeses = 0.0;

        for (Ativos ativo : ativos) {
            if (ativo.getDataTransacao() != null) {
                LocalDate dataTransacao = LocalDate.parse(ativo.getDataTransacao(), formatter);

                if (ativo.getValor() != null && !ativo.getValor().isEmpty()) {
                    double valor = Double.parseDouble(ativo.getValor());

                    if (dataTransacao.isBefore(dataUmMesAtras)) {
                        somaUmMes += valor;
                    }
                    if (dataTransacao.isBefore(dataDoisMesesAtras)) {
                        somaDoisMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataTresMesesAtras)) {
                        somaTresMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataQuatroMesesAtras)) {
                        somaQuatroMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataCincoMesesAtras)) {
                        somaCincoMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataSeisMesesAtras)) {
                        somaSeisMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataSeteMesesAtras)) {
                        somaSeteMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataOitoMesesAtras)) {
                        somaOitoMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataNoveMesesAtras)) {
                        somaNoveMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataDezMesesAtras)) {
                        somaDezMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataOnzeMesesAtras)) {
                        somaOnzeMeses += valor;
                    }
                    if (dataTransacao.isBefore(dataDozeMesesAtras)) {
                        somaDozeMeses += valor;
                    }
                }
            }
        }

        Map<String, Double> resultado = new HashMap<>();
        resultado.put("valorAtivosAteUmMesAtras", somaUmMes);
        resultado.put("valorAtivosAteDoisMesesAtras", somaDoisMeses);
        resultado.put("valorAtivosAteTresMesesAtras", somaTresMeses);
        resultado.put("valorAtivosAteQuatroMesesAtras", somaQuatroMeses);
        resultado.put("valorAtivosAteCincoMesesAtras", somaCincoMeses);
        resultado.put("valorAtivosAteSeisMesesAtras", somaSeisMeses);
        resultado.put("valorAtivosAteSeteMesesAtras", somaSeteMeses);
        resultado.put("valorAtivosAteOitoMesesAtras", somaOitoMeses);
        resultado.put("valorAtivosAteNoveMesesAtras", somaNoveMeses);
        resultado.put("valorAtivosAteDezMesesAtras", somaDezMeses);
        resultado.put("valorAtivosAteOnzeMesesAtras", somaOnzeMeses);
        resultado.put("valorAtivosAteDozeMesesAtras", somaDozeMeses);

        return resultado;
    }
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/quantidadeativosseismesesatras")
	public Long obterQuantidadeAtivosAteSeisMesesAtras() {
	    LocalDate dataAtual = LocalDate.now();
	    LocalDate dataSeisMesesAtras = dataAtual.minusMonths(6);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    List<Ativos> ativos = repositorio.findAll();

	    long quantidade = 0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getDataTransacao() != null) {
	            LocalDate dataTransacao = LocalDate.parse(ativo.getDataTransacao(), formatter);
	            if (dataTransacao.isBefore(dataSeisMesesAtras)) {
	                quantidade++;
	            }
	        }
	    }
	    return quantidade;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/quantidadeativosdozemesesatras")
	public Long obterQuantidadeAtivosAteDozeMesesAtras() {
	    LocalDate dataAtual = LocalDate.now();
	    LocalDate dataDozeMesesAtras = dataAtual.minusMonths(12);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    List<Ativos> ativos = repositorio.findAll();

	    long quantidade = 0;
	    for (Ativos ativo : ativos) {
	        if (ativo.getDataTransacao() != null) {
	            LocalDate dataTransacao = LocalDate.parse(ativo.getDataTransacao(), formatter);
	            if (dataTransacao.isBefore(dataDozeMesesAtras)) {
	                quantidade++;
	            }
	        }
	    }
	    return quantidade;
	}




	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/usuario")
	public List<AtivoNotificacao> obeterAtivos() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuario = authentication.getName();
	    Usuario usuario = usuarioRepositorio.findByNome(nomeUsuario);

	    List<Ativos> ativosDoUsuario = repositorio.findAll().stream()
	            .filter(ativo -> ativo.getId_responsavel().equals(usuario.getId()))
	            .collect(Collectors.toList());

	    List<AtivoNotificacao> resultado = ativosDoUsuario.stream().map(ativo -> {
	        List<Notificacao> notificacoes = notificacaoRepositorio.findByAtivoNumero(ativo.getNumAtivo());
	        AtivoNotificacao dto = new AtivoNotificacao();
	        dto.setIdAtivo(ativo.getId());
	        dto.setNome(ativo.getNome());
	        dto.setDescricao(ativo.getDescricao());
	        dto.setResponsavel(ativo.getResponsavel());
	        dto.setValor(ativo.getValor());
	        dto.setNumAtivo(ativo.getNumAtivo());
	        dto.setDataManutencao(ativo.getDataManutencao());
	        if (!notificacoes.isEmpty()) {
	            Notificacao notificacao = notificacoes.get(0);
	            dto.setDataExpiracao(notificacao.getDataExpiracao());
	            dto.setDias(notificacao.getDias());
	        } else {
	            dto.setDataExpiracao(null);
	            dto.setDias(null);
	        }
	        return dto;
	    }).collect(Collectors.toList());

	    return resultado;
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void ativosEmManutencao() {
		List<Ativos> ativos = repositorio.findAll();
		Date dataAtual = new Date();
		for(Ativos ativo : ativos) {
			if(ativo.getDataManutencao().before(dataAtual)) {
				ativo.setEstado("EM MANUTENÇÃO");
				ativo.setResponsavel("admteste");
				ativo.setId_responsavel(98L);
				repositorio.save(ativo);
			}
		}
	}
	
}
