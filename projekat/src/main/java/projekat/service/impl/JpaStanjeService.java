package projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.model.Stanje;
import projekat.model.Zadatak;
import projekat.repository.StanjeRepository;
import projekat.repository.ZadatakRepository;
import projekat.service.StanjeService;
@Service
public class JpaStanjeService implements StanjeService {
	@Autowired
	private StanjeRepository stanjeService;
	


	@Override
	public Stanje findOne(Long id) {
		
		return stanjeService.findOne(id);
	}

	@Override
	public List<Stanje> findAll() {
		
		return stanjeService.findAll();
	}

	@Override
	public Stanje save(Stanje stanje) {
		
		return stanjeService.save(stanje);
	}


}
