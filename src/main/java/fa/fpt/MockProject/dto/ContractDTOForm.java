package fa.fpt.MockProject.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ContractDTOForm - DTO Form đại diện cho dữ liệu hợp đồng.
 * Lớp này được sử dụng để truyền tải dữ liệu giữa controller và view.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Getter
@Setter
@NoArgsConstructor
public class ContractDTOForm {

    /**
     * Số hợp đồng - Được yêu cầu nhập, phải có định dạng alpha-numeric và có tối đa 20 ký tự.
     */
    @NotBlank(message = "Contract Number must be entered.")
    @Size(max = 20)
    @Pattern(regexp = "^[A-Za-z0-9\\-]+$", message = "Contract Number must be in the format of alpha numeric.")
    private String contractNumber;

    /**
     * Tên hợp đồng - Được yêu cầu nhập, tối đa 100 ký tự.
     */
    @NotBlank(message = "Contract Name must be entered.")
    @Size(max = 100)
    private String contractName;

    /**
     * Mã hãng hàng không.
     */
    private Long airlineCode;

    /**
     * Mã quốc gia.
     */
    private Long countryCode;

    /**
     * Mức độ quan trọng của hợp đồng.
     */
    private String importantLevel;

    /**
     * Mức độ ưu tiên của hợp đồng.
     */
    private Long priority;

    /**
     * Vùng địa lý liên quan đến hợp đồng.
     */
    private String region;

    /**
     * Loại fare (loại giá vé).
     */
    private String fareType;

    /**
     * Ngày nhận hợp đồng - Ngày phải được nhập và phải có định dạng đúng.
     */
    @NotNull(message = "Date Received must be entered.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReceived;

    /**
     * Ngày hiệu lực hợp đồng - Ngày phải được nhập và phải có định dạng đúng.
     */
    @NotNull(message = "Effective Date must be entered.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    /**
     * Ngày hết hiệu lực hợp đồng - Ngày phải được nhập và phải có định dạng đúng.
     */
    @NotNull(message = "Discontinue Date must be entered.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate discontinueDate;

    /**
     * Ngày xác nhận đã làm rõ thông tin hợp đồng - Ngày phải được nhập và phải có định dạng đúng.
     */
    @NotNull(message = "Date Clarification Cleared must be entered.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateClarificationCleared;

    /**
     * Số lượng bảng giá (Fare Sheets) - Phải là số nguyên lớn hơn 0 và nhỏ hơn 1000.
     */
    @NotNull(message = "Number of Fare Sheets must be entered.")
    @Min(value = 1, message = "Number of Fare Sheets must be a number and > 0.")
    @Max(value = 999, message = "")
    private Integer numberOfFareSheets;

    /**
     * Số lượng giá vé (Fares) - Phải là số nguyên lớn hơn 0 và nhỏ hơn 100000.
     */
    @NotNull(message = "Number of Fares must be entered.")
    @Min(value = 1, message = "Number of Fare must be a number and > 0.")
    @Max(value = 99999, message = "")
    private Integer numberOfFares;

    /**
     * Số lượng RTGS thực tế - Phải là số nguyên lớn hơn 0 và nhỏ hơn 100000.
     */
    @NotNull(message = "Rtgs Actual must be entered.")
    @Min(value = 1, message = "Rtgs Actual must be a number and > 0.")
    @Max(value = 99999, message = "")
    private Integer rtgsActual;

    /**
     * Số lượng bản ghi RTGS - Phải là số nguyên lớn hơn 0 và nhỏ hơn 100000.
     */
    @NotNull(message = "Rtgs Records must be entered.")
    @Min(value = 1, message = "Rtgs Records must be a number and > 0.")
    @Max(value = 99999, message = "")
    private Integer rtgsRecords;

    /**
     * Số lượng quy tắc - Phải là số nguyên lớn hơn 0 và nhỏ hơn 1000.
     */
    @NotNull(message = "Number of Rules must be entered.")
    @Min(value = 1, message = "Number of Rules must be a number and > 0.")
    @Max(value = 999, message = "")
    private Integer numberOfRules;

    /**
     * Tổng số bản ghi liên quan đến hợp đồng.
     */
    private Integer totalRecords;

    /**
     * Ngày bắt đầu hàng đợi - Ngày phải có định dạng đúng.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queuingStartDate;

    /**
     * Ngày kết thúc hàng đợi - Ngày phải có định dạng đúng, mặc định là ngày hiện tại.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queuingEndDate = LocalDate.now();

    /**
     * Phương thức toString - Trả về chuỗi biểu diễn các trường của ContractDTOForm.
     * 
     * @return Chuỗi biểu diễn thông tin của đối tượng ContractDTOForm.
     */
	@Override
	public String toString() {
		return "ContractDTOForm [contractNumber=" + contractNumber + ", contractName=" + contractName + ", airlineCode="
				+ airlineCode + ", countryCode=" + countryCode + ", importantLevel=" + importantLevel + ", priority="
				+ priority + ", region=" + region + ", fareType=" + fareType + ", dateReceived=" + dateReceived
				+ ", effectiveDate=" + effectiveDate + ", discontinueDate=" + discontinueDate
				+ ", dateClarificationCleared=" + dateClarificationCleared + ", numberOfFareSheets="
				+ numberOfFareSheets + ", numberOfFares=" + numberOfFares + ", rtgsActual=" + rtgsActual
				+ ", rtgsRecords=" + rtgsRecords + ", numberOfRules=" + numberOfRules + ", totalRecords=" + totalRecords
				+ ", queuingStartDate=" + queuingStartDate + ", queuingEndDate=" + queuingEndDate + "]";
	}
	
}
