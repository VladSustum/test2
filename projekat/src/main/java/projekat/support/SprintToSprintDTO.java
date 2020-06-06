package projekat.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.dto.SprintDTO;
import projekat.model.Sprint;

@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint source) {
		SprintDTO dto = new SprintDTO();
		dto.setBodovi(source.getBodovi());
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		return dto;
	}
	public List<SprintDTO> convert(List<Sprint> source){
		List<SprintDTO> dto = new ArrayList<>();
		for(Sprint sp : source) {
			dto.add(convert(sp));
		}
		return dto;
	}

}
