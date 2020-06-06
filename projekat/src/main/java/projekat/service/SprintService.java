package projekat.service;

import java.util.List;

import projekat.model.Sprint;

public interface SprintService {
	List<Sprint> findAll();
	Sprint findOne(Long id);
	Sprint save(Sprint sprint);

}
