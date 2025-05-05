package fa.fpt.MockProject.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractDTOList {

	/**
	 *  Mã hợp đồng
	 */
	private String contractNumber;

	/**
	 * Mã hãng hàng không
	 */
	private String airlineCode;

	/**
	 *  Thị trường
	 */
	private String market;

	/**
	 *  Khu vực
	 */
	private String region;

	/**
	 *  Loại giá vé
	 */
	private String fareType;
	
	/**
	 *  Ngày nhận hợp đồng, định dạng dd/MM/yyyy
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateReceived;

	/**
	 *  Ngày có hiệu lực hợp đồng, định dạng dd/MM/yyyy
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate effectiveDate;

	/**
	 *  Ngày hết hạn hợp đồng, định dạng dd/MM/yyyy
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate discontinueDate;

	/**
	 *  Ưu tiên hợp đồng
	 */
	private String priority;

	/**
	 *  Phương thức toString để hiển thị thông tin đối tượng hợp đồng dưới dạng chuỗi
	 */
	@Override
	public String toString() {
		return "ContractDTOList [contractNumber=" + contractNumber + ", airlineCode=" + airlineCode + ", market="
				+ market + ", region=" + region + ", fareType=" + fareType + ", dateReceived=" + dateReceived
				+ ", effectiveDate=" + effectiveDate + ", discontinueDate=" + discontinueDate + ", priority=" + priority
				+ "]";
	}

	/**
	 * Constructor khởi tạo đối tượng ContractDTOList với tất cả các tham số
	 * 
	 * @param contractNumber     Mã hợp đồng
	 * @param airlineCode        Mã hãng hàng không
	 * @param market             Thị trường
	 * @param region             Khu vực
	 * @param fareType           Loại giá vé
	 * @param dateReceived       Ngày nhận hợp đồng
	 * @param effectiveDate      Ngày có hiệu lực hợp đồng
	 * @param discontinueDate    Ngày hết hạn hợp đồng
	 * @param priority           Ưu tiên hợp đồng
	 */
	public ContractDTOList(String contractNumber, String airlineCode, String market, String region, String fareType,
			LocalDate dateReceived, LocalDate effectiveDate, LocalDate discontinueDate, String priority) {
		super();
		this.contractNumber = contractNumber;
		this.airlineCode = airlineCode;
		this.market = market;
		this.region = region;
		this.fareType = fareType;
		this.dateReceived = dateReceived;
		this.effectiveDate = effectiveDate;
		this.discontinueDate = discontinueDate;
		this.priority = priority;
	}
	
}
