package fa.fpt.MockProject.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import fa.fpt.MockProject.entities.Contract;
import jakarta.persistence.criteria.Predicate;

/**
 * Lớp này chứa các Specification để tìm kiếm hợp đồng (Contract) theo các tiêu
 * chí khác nhau.
 */
public class ContractSpecifications {

	  /**
     * Phương thức tạo Specification để tìm kiếm hợp đồng (Contract) theo các tiêu chí cho trước.
     * Các tiêu chí bao gồm:
     * - Market (Quốc gia) - yêu cầu
     * - Airline (Hãng hàng không) - yêu cầu
     * - Số hợp đồng (Contract Number) - tùy chọn, tìm kiếm bằng cách sử dụng LIKE
     * - Loại giá (Fare Type) - tùy chọn
     * - Ngày ngừng áp dụng (Discontinue Date) - tùy chọn
     * - Ngày hiệu lực (Effective Date) - tùy chọn
     * - Ngày nhận hợp đồng (Date Received) - tùy chọn
	 * 
	 * @param market          Mã quốc gia, yêu cầu.
	 * @param airline         Mã hãng hàng không, yêu cầu.
	 * @param contractNumber  Số hợp đồng, tùy chọn.
	 * @param fareType        Loại giá, tùy chọn.
	 * @param discontinueDate Ngày ngừng áp dụng, tùy chọn.
	 * @param effectiveDate   Ngày hiệu lực, tùy chọn.
	 * @param dateReceived    Ngày nhận hợp đồng, tùy chọn.
	 * @return Specification<Contract> Đối tượng Specification để thực hiện tìm kiếm
	 *         hợp đồng.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public static Specification<Contract> byCriteria(Long market, Long airline, String contractNumber, String fareType,
			LocalDate discontinueDate, LocalDate effectiveDate, LocalDate dateReceived) {

		// Tạo đối tượng Predicate để lưu trữ tất cả các điều kiện tìm kiếm.
		return (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();

			// Yêu cầu: Market (Quốc gia)
			if (market == null) {
				throw new IllegalArgumentException("Market (country) is required.");
			}
			// Thêm điều kiện tìm kiếm theo mã quốc gia (market)
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(root.get("country").get("countryCode"), market));

			// Yêu cầu: Airline (Hãng hàng không)
			if (airline == null) {
				throw new IllegalArgumentException("Airline is required.");
			}
			// Thêm điều kiện tìm kiếm theo mã hãng hàng không (airline)
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("airline").get("code"), airline));

			// Tùy chọn: Contract Number (Số hợp đồng) - tìm kiếm theo kiểu LIKE (partial
			// match)
			if (contractNumber != null && !contractNumber.isEmpty()) {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.like(root.get("contractNumber"), "%" + contractNumber + "%"));
			}

			// Tùy chọn: Fare Type (Loại giá)
			if (fareType != null && !fareType.isEmpty()) {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("fareType").get("code"), fareType));
			}

			// Tùy chọn: Discontinue Date (Ngày ngừng áp dụng)
			if (discontinueDate != null) {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("discontinueDate"), discontinueDate));
			}

			// Tùy chọn: Effective Date (Ngày hiệu lực)
			if (effectiveDate != null) {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("effectiveDate"), effectiveDate));
			}

			// Tùy chọn: Date Received (Ngày nhận hợp đồng)
			if (dateReceived != null) {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.equal(root.get("dateReceived"), dateReceived));
			}

			// Trả về Predicate để sử dụng trong truy vấn.
			return predicate;
		};
	}

}
