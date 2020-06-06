package projekat.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.dto.ZadatakDTO;
import projekat.model.Sprint;
import projekat.model.Stanje;
import projekat.model.Zadatak;
import projekat.service.SprintService;
import projekat.service.StanjeService;
import projekat.service.ZadatakService;

@Component
public class ZadatakDTOtoZadatak implements Converter<ZadatakDTO, Zadatak> {
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private StanjeService stanjeService;
	
	@Autowired
	private SprintService sprintService;

	@Override
	public Zadatak convert(ZadatakDTO source) {
		Stanje stanje = stanjeService.findOne(source.getStanjeId());
		Sprint sprint = sprintService.findOne(source.getSprintId());
		
		if(stanje != null && sprint != null) {
			Zadatak zadatak= null;
		if(source.getId() != null) {
			zadatak = zadatakService.findOne(source.getId());
		} else {
			zadatak = new Zadatak();
		}
		zadatak.setBodovi(source.getBodovi());
		zadatak.setIme(source.getIme());
		zadatak.setZaduzeni(source.getZaduzeni());
		zadatak.setSprint(sprint);
		zadatak.setStanje(stanje);
		return zadatak;
		} else {
		throw new IllegalStateException("Greska");
		}
		
	}
	public List<Zadatak> convert(List<ZadatakDTO> source){
		List<Zadatak> zad = new ArrayList<>();
		
		for(ZadatakDTO dto : source) {
			zad.add(convert(dto));
		}
		return zad;
	}

}
