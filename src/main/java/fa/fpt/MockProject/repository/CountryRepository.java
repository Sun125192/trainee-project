package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.fpt.MockProject.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	List<Country> findAllByOrderByNameAsc();
}
