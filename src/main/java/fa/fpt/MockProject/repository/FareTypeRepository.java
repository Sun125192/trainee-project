package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.fpt.MockProject.entities.FareType;

/**
 * Repository interface cho thực thể FareType.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public interface FareTypeRepository extends JpaRepository<FareType, String> {

	/**
	 * Lấy danh sách tất cả các loại giá (Fare Type) theo thứ tự tên tăng dần.
	 * 
	 * @return Danh sách các loại giá (Fare Type) đã sắp xếp theo tên.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	List<FareType> findAllByOrderByNameAsc();
}
