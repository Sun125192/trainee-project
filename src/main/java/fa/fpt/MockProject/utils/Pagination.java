package fa.fpt.MockProject.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lớp quản lý thông tin phân trang.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Getter
@Setter
@NoArgsConstructor
public class Pagination {

	// Trang hiện tại
	private int currentPage; 
	
	// Trang trước
	private int previousPage; 
	
	// Trang sau
	private int nextPage; 
	
	// Tổng số trang
	private int totalPages; 

	/**
	 * Constructor khởi tạo phân trang.
	 * 
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public Pagination(int currentPage, int previousPage, int nextPage, int totalPages) {
		this.currentPage = currentPage;
		this.previousPage = previousPage;
		this.nextPage = nextPage;
		this.totalPages = totalPages;
	}

	/**
	 * Tính toán phân trang dựa trên tổng số bản ghi, trang hiện tại và số bản ghi
	 * mỗi trang.
	 * 
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public static Pagination calculatePagination(int totalRecords, int currentPage, int recordsPerPage) {
		int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > totalPages)
			currentPage = totalPages;

		int previousPage = (currentPage > 1) ? currentPage - 1 : 1;
		int nextPage = (currentPage < totalPages) ? currentPage + 1 : totalPages;

		return new Pagination(currentPage, previousPage, nextPage, totalPages);
	}

	/**
	 * Trả về thông tin phân trang dưới dạng chuỗi.
	 * 
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", previousPage=" + previousPage + ", nextPage=" + nextPage
				+ ", totalPages=" + totalPages + "]";
	}
	
}
