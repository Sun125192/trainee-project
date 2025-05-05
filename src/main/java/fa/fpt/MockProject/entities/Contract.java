package fa.fpt.MockProject.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lớp đại diện cho thông tin Contract.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CONTRACT")
public class Contract {

	/**
	 * Mã Contract (định danh duy nhất).
	 */
	@Id
	@NotBlank(message = "Contract Number must be entered.")
	@Pattern(regexp = "^[A-Za-z0-9\\-]+$", message = "Contract Number must be in the format of alpha numeric.")
	@Column(name = "CONTRACT_NUMBER", columnDefinition = "VARCHAR(20)", nullable = false)
	private String contractNumber;

	/**
	 * Tên Contract.
	 */
	@NotBlank(message = "Contract Name must be entered.")
	@Column(name = "NAME", columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;

	/**
	 * Airline liên kết với Contract.
	 */
	@ManyToOne
	@JoinColumn(name = "AIRLINE_ID", referencedColumnName = "code", nullable = false)
	private Airline airline;

	/**
	 * Country liên kết với Contract.
	 */
	@ManyToOne
	@JoinColumn(name = "MARKET", referencedColumnName = "COUNTRY_CODE", nullable = false)
	private Country country;

	/**
	 * Mức độ quan trọng của Contract.
	 */
	@Column(name = "IMPORTANT_LEVEL", nullable = false)
	private String importantLevel;

	/**
	 * Độ ưu tiên của Contract.
	 */
	@ManyToOne
	@JoinColumn(name = "PRIORITY_CODE", referencedColumnName = "CODE", nullable = false)
	private Priority priority;

	/**
	 * Khu vực liên kết với hợp đồng.
	 */
	@ManyToOne
	@JoinColumn(name = "REGION_ID", referencedColumnName = "CODE", nullable = false)
	private Region region;

	/**
	 * Loại giá vé.
	 */
	@ManyToOne
	@JoinColumn(name = "FARE_TYPE", referencedColumnName = "CODE", nullable = false)
	private FareType fareType;

	/**
	 * Ngày nhận hợp đồng.
	 */
	@NotNull(message = "Date Received must be entered.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATE_RECEIVED", nullable = false)
	private LocalDate dateReceived;

	/**
	 * Ngày ngừng hợp đồng.
	 */
	@NotNull(message = "Discontinue Date must be entered.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DISCONTINUE_DATE", nullable = false)
	private LocalDate discontinueDate;

	/**
	 * Ngày hiệu lực hợp đồng.
	 */
	@NotNull(message = "Effective Date must be entered.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "EFFECTIVE_DATE", nullable = false)
	private LocalDate effectiveDate;

	/**
	 * Ngày làm rõ thông tin hợp đồng.
	 */
	@NotNull(message = "Date Clarification Cleared must be entered.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATE_CLARIFICATION_CLEARED", nullable = false)
	private LocalDate dateClarificationCleared;

	/**
	 * Số lượng bảng giá vé.
	 */
	@NotNull(message = "Number of Fare Sheets must be entered.")
	@Min(value = 1, message = "Number of Fare Sheets must be a number and > 0.")
	@Max(value = 999, message = "")
	@Column(name = "FARE_SHEETS", nullable = false)
	private Integer numberOfFareSheets;

	/**
	 * Số lượng giá vé.
	 */
	@NotNull(message = "Number of Fares must be entered.")
	@Min(value = 1, message = "Number of Fare must be a number and > 0.")
	@Max(value = 99999, message = "")
	@Column(name = "NUMBER_OF_FARES", nullable = false)
	private Integer numberOfFares;

	/**
	 * Số lượng RTGS thực tế.
	 */
	@NotNull(message = "Rtgs Actual must be entered.")
	@Min(value = 1, message = "Rtgs Actual must be a number and > 0.")
	@Max(value = 99999, message = "")
	@Column(name = "RTGS_ACTUAL", nullable = false)
	private Integer rtgsActual;

	/**
	 * Số bản ghi RTGS.
	 */
	@NotNull(message = "Rtgs Records must be entered.")
	@Min(value = 1, message = "Rtgs Records must be a number and > 0.")
	@Max(value = 99999, message = "")
	@Column(name = "RTGS_RECORDS", nullable = false)
	private Integer rtgsRecords;

	/**
	 * Số lượng quy tắc áp dụng.
	 */
	@NotNull(message = "Number of Rules must be entered.")
	@Min(value = 1, message = "Number of Rules must be a number and > 0.")
	@Max(value = 999, message = "")
	@Column(name = "RULES", nullable = false)
	private Integer numberOfRules;

	/**
	 * Tổng số bản ghi.
	 */
	@Column(name = "TOTAL_RECORDS", nullable = false)
	private Integer totalRecords;

	/**
	 * Ngày bắt đầu xếp hàng xử lý.
	 */
	@Column(name = "QUEUING_START_DATE", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate queuingStartDate;

	/**
	 * Ngày kết thúc xếp hàng xử lý.
	 */
	@Column(name = "QUEUING_END_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate queuingEndDate;

	// Getters và Setters tự động tạo bởi Lombok
}
