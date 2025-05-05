package fa.fpt.MockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.entities.Priority;
import fa.fpt.MockProject.repository.PriorityRepository;

/**
 * Lớp dịch vụ để quản lý các thao tác liên quan đến mức độ ưu tiên (Priority).
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class PriorityService {
	
	@Autowired
	private PriorityRepository priorityRepository;

	/**
	 * Phương thức lấy danh sách tất cả các mức độ ưu tiên từ cơ sở dữ liệu, được
	 * sắp xếp theo tên mức độ ưu tiên theo thứ tự tăng dần.
	 * 
	 * @return List<Priority> Danh sách các mức độ ưu tiên đã được sắp xếp.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Priority> findAll() {
		return priorityRepository.findAllByOrderByNameAsc();
	}

}
