package com.cls;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



import com.cls.repository.CalculRepository;

@SpringBootApplication
public class AgrostroeApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AgrostroeApplication.class, args);
		CalculRepository cr = ctx.getBean(CalculRepository.class);
		String luna="2016-03";
		
		/*List<Calcul> calcule = cr.findByLunaCurenta(luna);
		for (Calcul calcul : calcule) {
			System.out.println(calcul.getAngajat().getNume()+" "+calcul.getSalariuBrut());
		}*/
		
	}
}
