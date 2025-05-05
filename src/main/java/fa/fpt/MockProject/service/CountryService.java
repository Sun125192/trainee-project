package fa.fpt.MockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.entities.Country;
import fa.fpt.MockProject.repository.CountryRepository;

/**
 * Lớp dịch vụ (Service) để quản lý các thao tác liên quan đến đối tượng Quốc Gia (Country).
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 * Phương thức tìm tất cả các quốc gia từ cơ sở dữ liệu
	 * và sắp xếp theo tên quốc gia theo thứ tự tăng dần.
	 * 
	 * @return Danh sách các quốc gia đã được sắp xếp theo tên
	 */
	public List<Country> findAll() {
		// Gọi phương thức findAllByOrderByNameAsc() từ CountryRepository để lấy tất cả quốc gia đã sắp xếp theo tên
		return countryRepository.findAllByOrderByNameAsc();
	}
}
