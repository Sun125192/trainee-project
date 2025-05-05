package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.fpt.MockProject.entities.Priority;

/**
 * Repository interface cho thực thể Priority.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public interface PriorityRepository extends JpaRepository<Priority, Long> {

	/**
	 * Lấy danh sách tất cả các mức độ ưu tiên (Priority) theo thứ tự tên tăng dần.
	 * 
	 * @return Danh sách các mức độ ưu tiên (Priority) đã sắp xếp theo tên.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	List<Priority> findAllByOrderByNameAsc();
}
