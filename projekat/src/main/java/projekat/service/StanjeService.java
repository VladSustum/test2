package projekat.service;

import java.util.List;

import projekat.model.Stanje;

public interface StanjeService {
	Stanje findOne(Long id);
	List<Stanje> findAll();
	Stanje save(Stanje stanje);


}
