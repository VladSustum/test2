package projekat.service;

import org.springframework.data.domain.Page;

import projekat.model.Stanje;
import projekat.model.Zadatak;

public interface ZadatakService {
	Page<Zadatak> findAll(int pageNum);
	Zadatak findOne(Long id);
	Zadatak save(Zadatak zadatak);
	Zadatak delete(Long id);
	Page<Zadatak> search(String ime, Long sprint , int pageNum);
	Stanje predji(Long id);
	

}
