package projekat.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projekat.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long> {
	
	@Query("SELECT z from Zadatak z WHERE "
			+ "(:ime IS NULL OR z.ime LIKE :ime) AND"
			+ "(:sprint IS NULL OR z.sprint.id LIKE :sprint)"
			)
	Page<Zadatak> search(
			@Param("ime") String ime,
			@Param("sprint") Long sprint,
			Pageable pageRequest
			);

}
