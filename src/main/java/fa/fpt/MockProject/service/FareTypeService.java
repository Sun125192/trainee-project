package fa.fpt.MockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.entities.FareType;
import fa.fpt.MockProject.repository.FareTypeRepository;

/**
 * Lớp dịch vụ để quản lý các thao tác liên quan đến loại giá (FareType).
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class FareTypeService {
	
	@Autowired
	private FareTypeRepository fareTypeRepository;
	
	/**
     * Phương thức lấy danh sách tất cả các loại giá từ cơ sở dữ liệu, 
     * được sắp xếp theo tên loại giá theo thứ tự tăng dần.
     * 
     * @return List<FareType> Danh sách các loại giá đã được sắp xếp.
     * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<FareType> findAll() {
		return fareTypeRepository.findAllByOrderByNameAsc();
	}
	
}
