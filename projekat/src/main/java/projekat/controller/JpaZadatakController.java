package projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.StanjeDTO;
import projekat.dto.ZadatakDTO;
import projekat.model.Stanje;
import projekat.model.Zadatak;
import projekat.service.StanjeService;
import projekat.service.ZadatakService;
import projekat.support.StanjeToStanjeDTO;
import projekat.support.ZadatakDTOtoZadatak;
import projekat.support.ZadatakToZadatakDTO;

@RestController
@RequestMapping(value="/api/zadaci")
public class JpaZadatakController {
	
	@Autowired
	private ZadatakService zs;
	
	@Autowired
	private ZadatakToZadatakDTO toDto;
	
	@Autowired
	private ZadatakDTOtoZadatak toZad;
	
	@Autowired
	private StanjeService ss;
	
	@Autowired
	private StanjeToStanjeDTO stanjeDto;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ZadatakDTO>> getZadaci(@RequestParam(required= false)String ime, @RequestParam(required = false) Long sprint, @RequestParam(defaultValue="0", value="pageNum")int pageNum){
		Page<Zadatak> zad = null;
		
		if(ime != null || sprint != null ) {
			zad = zs.search(ime, sprint, pageNum);
		} else {
			zad = zs.findAll(pageNum);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(zad.getTotalPages()));
		
		return new ResponseEntity<>(toDto.convert(zad.getContent()), headers , HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ZadatakDTO> getZadatak(@PathVariable Long id){
		Zadatak zad = zs.findOne(id);
		if(zad == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(zad), HttpStatus.OK);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ZadatakDTO> delete(@PathVariable Long id){
		Zadatak toDelete = zs.delete(id);
		if(toDelete != null) {
			return new ResponseEntity<>(toDto.convert(toDelete), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(consumes="application/json" , method= RequestMethod.POST)
	public ResponseEntity<ZadatakDTO> add(@Validated @RequestBody ZadatakDTO dto){
		Zadatak toAdd = zs.save(toZad.convert(dto));
		
		return new ResponseEntity<>(toDto.convert(toAdd), HttpStatus.CREATED);
	}
	@RequestMapping(consumes="application/json", value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ZadatakDTO> edit(@Validated @PathVariable Long id , @RequestBody ZadatakDTO dto){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zadatak edit = zs.save(toZad.convert(dto));
		return new ResponseEntity<>(toDto.convert(edit), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<StanjeDTO> promeni(@PathVariable Long id){
		Stanje s = zs.predji(id);
		
		if(s != null) {
			return new ResponseEntity<>(stanjeDto.convert(s), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handler(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
