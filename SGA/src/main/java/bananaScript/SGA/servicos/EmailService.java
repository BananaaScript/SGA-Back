package bananaScript.SGA.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String remetente;
	
	public String enviarEmailTexto(String destinatario, String assunto, String mensagem) {
		
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setSubject(assunto);
			simpleMailMessage.setTo(destinatario);
			simpleMailMessage.setText(mensagem);
			javaMailSender.send(simpleMailMessage);
			return "Email Enviado com Sucesso";
		}catch(Exception e) {
			return "Erro ao tentar enviar email " + e.getLocalizedMessage();		}
	}
	

}
