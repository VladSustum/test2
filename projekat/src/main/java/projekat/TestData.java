package projekat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projekat.model.Sprint;
import projekat.model.Stanje;
import projekat.model.Zadatak;
import projekat.service.SprintService;
import projekat.service.StanjeService;
import projekat.service.ZadatakService;

@Component
public class TestData {
	
	@Autowired
	private StanjeService ss;
	@Autowired
	private SprintService sp;
	@Autowired
	private ZadatakService zs;
	
	@PostConstruct
	public void innit() {
		Sprint s = new Sprint();
		Stanje sta = new Stanje();
		Zadatak z = new Zadatak();
		
		s.setIme("z1");
		s.setIme("Tuzan");
		s.setBodovi("2");
		ss.save(sta);
		
		//sta.setId(1l);
		
		
		
		
		sp.save(s);
		
		
		
		
		z.setBodovi(5);
		z.setIme("zad1");
		z.setZaduzeni("Gari");
		z.setSprint(s);
		z.setStanje(sta);
		zs.save(z);
	}

}
