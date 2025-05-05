package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.fpt.MockProject.entities.Airline;

/**
 * Repository interface cho thực thể Airline.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public interface AirlineRepository extends JpaRepository<Airline, Long>{
	
	/**
	 * Lấy danh sách tất cả các Airline được sắp xếp theo tên tăng dần.
	 *
	 * @return
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	List<Airline> findAllByOrderByNameAsc();
	
}
