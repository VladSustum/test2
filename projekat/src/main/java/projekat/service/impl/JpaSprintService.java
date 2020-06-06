package projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.model.Sprint;
import projekat.repository.SprintRepository;
import projekat.service.SprintService;
@Service
public class JpaSprintService implements SprintService {
	
	@Autowired
	private SprintRepository sprintService;

	@Override
	public List<Sprint> findAll() {
		
		return sprintService.findAll();
	}

	@Override
	public Sprint findOne(Long id) {
	
		return sprintService.findOne(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		
		return sprintService.save(sprint);
	}

}
