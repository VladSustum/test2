package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projekat.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
