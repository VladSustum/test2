package projekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import projekat.model.Stanje;
import projekat.model.Zadatak;
import projekat.repository.StanjeRepository;
import projekat.repository.ZadatakRepository;
import projekat.service.ZadatakService;
@Service 
public class JpaZadatakService implements ZadatakService {
	@Autowired
	private ZadatakRepository zadatakService;
	
	@Autowired
	private StanjeRepository stanjeService;

	@Override
	public Page<Zadatak> findAll(int pageNum) {
		
		return zadatakService.findAll(new PageRequest(pageNum,5));
	}

	@Override
	public Zadatak findOne(Long id) {
		
		return zadatakService.findOne(id);
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		
		return zadatakService.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Zadatak toDelete = zadatakService.findOne(id);
		if(toDelete != null) {
			zadatakService.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprint, int pageNum) {
		if(ime != null) {
			ime = '%' + ime + '%';
		}
		
		return zadatakService.search(ime, sprint, new PageRequest(pageNum ,5));
	}

	@Override
	public Stanje predji(Long id) {
		Zadatak zadatak = findOne(id);
		Stanje s = null;
		if(zadatak != null) {
			s = zadatak.getStanje();
			if(s.getIme().equalsIgnoreCase("U toku")){
				s.setIme("Zavrsen");
				zadatak.setFinalnoStanje(true);
				zadatak.setStanje(s);
				stanjeService.save(s);
				zadatakService.save(zadatak);
				return s;
			} 
			if(s.getIme().equals("Nov")) {
				s.setIme("U toku");
				zadatak.setStanje(s);
				stanjeService.save(s);
				zadatakService.save(zadatak);
				return s;
			}
			
		return s;
		} else {
			throw new IllegalStateException("Neuspesno menjanje stanja");
		}
	
		
		
	
	}

}
