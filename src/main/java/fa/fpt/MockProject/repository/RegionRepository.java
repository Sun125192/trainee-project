package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.fpt.MockProject.entities.Region;

/**
 * Repository interface cho thực thể Region.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public interface RegionRepository extends JpaRepository<Region, String> {

	/**
	 * Lấy danh sách tất cả các khu vực (Region) theo thứ tự tên tăng dần.
	 * 
	 * @return Danh sách các khu vực (Region) đã sắp xếp theo tên.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	List<Region> findAllByOrderByNameAsc();
}
