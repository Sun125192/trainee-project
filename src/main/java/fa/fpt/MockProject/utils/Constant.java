package fa.fpt.MockProject.utils;

/**
 * Lớp này chứa các hằng số (constant) dùng chung trong toàn bộ dự án. Các giá
 * trị này có thể được sử dụng ở nhiều nơi trong ứng dụng để đảm bảo tính nhất
 * quán và dễ dàng quản lý.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public class Constant {

	/**
	 * Hằng số SIZE_PAGE xác định số lượng bản ghi trên mỗi trang khi phân trang.
	 * Giá trị này có thể được thay đổi trong tương lai nếu cần điều chỉnh số lượng
	 * bản ghi hiển thị mỗi trang.
	 * 
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public static final int SIZE_PAGE = 3;

	/**
	 * Hằng số FILTER_ACTION dùng để xác định hành động lọc trong các thao tác của
	 * người dùng. Giá trị này có thể được sử dụng để kiểm tra hoặc xử lý các yêu
	 * cầu lọc trong hệ thống.
	 * 
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public static final String FILTER_ACTION = "filter";

}
