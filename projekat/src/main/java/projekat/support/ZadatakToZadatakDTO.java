package projekat.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.dto.ZadatakDTO;
import projekat.model.Zadatak;

@Component
public class ZadatakToZadatakDTO implements Converter<Zadatak, ZadatakDTO> {

	@Override
	public ZadatakDTO convert(Zadatak source) {
		ZadatakDTO dto = new ZadatakDTO();
		dto.setBodovi(source.getBodovi());
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setZaduzeni(source.getZaduzeni());
		dto.setFinalnoStanje(source.isFinalnoStanje());
		dto.setSprintId(source.getSprint().getId());
		dto.setSprintIme(source.getSprint().getIme());
		
		dto.setStanjeId(source.getStanje().getId());
		dto.setStanjeIme(source.getStanje().getIme());
		return dto;
	}
	
	public List<ZadatakDTO> convert(List<Zadatak> source){
		List<ZadatakDTO> dto = new ArrayList<>();
		for(Zadatak zad : source) {
			dto.add(convert(zad));
		}
		return dto;
	}
	

}
