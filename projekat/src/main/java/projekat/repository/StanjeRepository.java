package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projekat.model.Stanje;

@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long> {

}
