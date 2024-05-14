package bananaScript.SGA.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bananaScript.SGA.entidades.Email;
import bananaScript.SGA.servicos.EmailService;

@RestController
@RequestMapping("/email")
public class EmailControle {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/enviar")
	public void enviarEmail(@RequestBody Email email){
		emailService.enviarEmail(email.getDestino(), email.getAssunto(), email.getMensagem());
		System.out.println("Email enviado!");
	}
}
