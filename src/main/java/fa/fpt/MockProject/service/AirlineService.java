package fa.fpt.MockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.entities.Airline;
import fa.fpt.MockProject.repository.AirlineRepository;

/**
 * Service class cho thực thể Airline.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	/**
	 * Lấy tất cả các hãng hàng không và sắp xếp theo tên theo thứ tự tăng dần.
	 * 
	 * @return Danh sách các hãng hàng không.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Airline> findAll() {
		return airlineRepository.findAllByOrderByNameAsc();
	}
	
}
