package fa.fpt.MockProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.entities.Region;
import fa.fpt.MockProject.repository.RegionRepository;

/**
 * Lớp dịch vụ quản lý các thao tác liên quan đến khu vực (Region).
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;

	/**
	 * Phương thức lấy danh sách tất cả các khu vực từ cơ sở dữ liệu, được sắp xếp
	 * theo tên khu vực theo thứ tự tăng dần.
	 * 
	 * @return List<Region> Danh sách các khu vực đã được sắp xếp.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Region> findAll() {
		return regionRepository.findAllByOrderByNameAsc();
	}

}
