package fa.fpt.MockProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO dùng để lưu trữ các tham số lọc hợp đồng.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Getter
@Setter
@NoArgsConstructor
public class FilterConTractDTO {
    
    /**
     * Thị trường áp dụng hợp đồng.
     */
    private String market;
    
    /**
     * Hãng hàng không liên kết với hợp đồng.
     */
    private String airline;
    
    /**
     * Số hợp đồng (mặc định "NULL").
     */
    private String contractNumber;
    
    /**
     * Loại vé của hợp đồng (mặc định "NULL").
     */
    private String fareType;
    
    /**
     * Ngày hết hạn hợp đồng.
     */
    private String discontinueDate;
    
    /**
     * Ngày có hiệu lực hợp đồng.
     */
    private String effectiveDate;
    
    /**
     * Ngày nhận hợp đồng.
     */
    private String dateReceived;
    
}
