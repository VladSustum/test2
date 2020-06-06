package projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.StanjeDTO;
import projekat.model.Stanje;
import projekat.service.StanjeService;
import projekat.support.StanjeToStanjeDTO;

@RestController
@RequestMapping(value="/api/stanja")
public class JpaStanjeController {
	
	@Autowired
	private StanjeService ss;
	
	@Autowired
	private StanjeToStanjeDTO toDto;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StanjeDTO>> getStanja(){
		List<Stanje> stanje = ss.findAll();
		
		return new ResponseEntity<>(toDto.convert(stanje), HttpStatus.OK);
	}

}
