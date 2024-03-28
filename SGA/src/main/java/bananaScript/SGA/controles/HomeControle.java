package bananaScript.SGA.controles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControle {

	@GetMapping("/")
	public String Home() {
		return "Bem-vindo";
	}
}
