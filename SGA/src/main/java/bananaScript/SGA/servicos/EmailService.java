package bananaScript.SGA.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviarEmail(String toEmail, String subject, String body) {
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom("codeapi23@gmail.com");
		mensagem.setTo(toEmail);
		mensagem.setText(body);
		mensagem.setSubject(subject);
		
		mailSender.send(mensagem);
	}

}
