package projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.SprintDTO;
import projekat.model.Sprint;
import projekat.service.SprintService;
import projekat.support.SprintToSprintDTO;

@RestController
@RequestMapping(value="/api/sprintovi")
public class JpaSprintController {
	
	@Autowired
	private SprintService ss;
	@Autowired
	private SprintToSprintDTO toDto;
	
	
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<SprintDTO>> getSprintovi(){
		List<Sprint> sprint = ss.findAll();
		
		return new ResponseEntity<>(toDto.convert(sprint), HttpStatus.OK);
	}

}
