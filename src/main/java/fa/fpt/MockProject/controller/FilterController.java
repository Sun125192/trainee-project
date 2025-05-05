package fa.fpt.MockProject.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.fpt.MockProject.dto.ContractDTOList;
import fa.fpt.MockProject.dto.FilterConTractDTO;
import fa.fpt.MockProject.entities.Airline;
import fa.fpt.MockProject.entities.Country;
import fa.fpt.MockProject.service.AirlineService;
import fa.fpt.MockProject.service.ContractService;
import fa.fpt.MockProject.service.CountryService;
import fa.fpt.MockProject.utils.Constant;
import fa.fpt.MockProject.utils.Pagination;

@Controller
public class FilterController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private AirlineService airlineService;

	/**
	 * Lấy danh sách hợp đồng và áp dụng bộ lọc nếu có.
	 * 
	 * @param model Model để truyền dữ liệu vào view
	 * @param action Loại hành động, nếu là "filter" thì thực hiện lọc dữ liệu
	 * @param market Thị trường
	 * @param airline Hãng hàng không
	 * @param contractNumber Số hợp đồng
	 * @param fareType Loại giá vé
	 * @param discontinueDate Ngày hết hiệu lực
	 * @param effectiveDate Ngày bắt đầu
	 * @param dateReceived Ngày nhận hợp đồng
	 * @param page Trang hiện tại
	 * @return Tên view để trả về cho client
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@GetMapping("/list")
	public String listOrFilter(Model model, @RequestParam(required = false, defaultValue = "") String action,
			@RequestParam(required = false) String market, @RequestParam(required = false) String airline,
			@RequestParam(required = false) String contractNumber, @RequestParam(required = false) String fareType,
			@RequestParam(required = false) String discontinueDate,
			@RequestParam(required = false) String effectiveDate, @RequestParam(required = false) String dateReceived,
			@RequestParam(required = false, defaultValue = "1") int page) {
		
		// Số bản ghi mỗi trang
		int recordsPerPage = Constant.SIZE_PAGE; 
		long totalRecords;

		// Chuyển các tham số chuỗi thành các kiểu dữ liệu phù hợp
		Long marketId = parseLongValue(market, model, "Invalid market value");
		Long airlineId = parseLongValue(airline, model, "Invalid airline value");
		LocalDate parsedDiscontinueDate = parseDateValue(discontinueDate);
		LocalDate parsedEffectiveDate = parseDateValue(effectiveDate);
		LocalDate parsedDateReceived = parseDateValue(dateReceived);

		// Lấy danh sách quốc gia và hãng hàng không
		List<Country> countrylist = countryService.findAll();
		List<Airline> airlinelist = airlineService.findAll();

		// Định nghĩa đối tượng phân trang
		Pagination pagination = new Pagination();
		List<ContractDTOList> contractList;

		// Kiểm tra nếu là action "filter" (lọc dữ liệu)
		if ("filter".equals(action) && marketId != null && airlineId != null) {
			// Lọc danh sách hợp đồng theo các tiêu chí đã chọn
			contractList = filterContracts(marketId, airlineId, contractNumber, fareType, parsedDiscontinueDate, 
					parsedEffectiveDate, parsedDateReceived, page, recordsPerPage);

			// Lấy tổng số bản ghi qua service (cho bộ lọc)
	        totalRecords = contractService.countFilteredRecords(marketId, airlineId, contractNumber, fareType,
	        		parsedDiscontinueDate, parsedEffectiveDate, parsedDateReceived, page, recordsPerPage);
			pagination = Pagination.calculatePagination((int) totalRecords, page, recordsPerPage);

			// Đưa các thông tin lọc vào Model để hiển thị trên giao diện
			model.addAttribute("market", marketId);
			model.addAttribute("airline", airlineId);
			model.addAttribute("filter", action);
		} else {
			// Trường hợp không lọc: Lấy tất cả hợp đồng mặc định
			totalRecords = contractService.totalrecord();
			contractList = contractService.formDtoMapEntity(contractService.findAllByPage(page, recordsPerPage));
			pagination = Pagination.calculatePagination((int) totalRecords, page, recordsPerPage);
		}

		// Đưa dữ liệu vào model để hiển thị trên giao diện
		model.addAttribute("contractList", contractList); // Danh sách hợp đồng
		model.addAttribute("pagination", pagination); // Phân trang
		model.addAttribute("countryList", countrylist); // Danh sách quốc gia
		model.addAttribute("airlinelist", airlinelist); // Danh sách airline

		return "projectContractInfo/filter-project-contract";
	}

	/**
	 * Hàm chuyển đổi giá trị từ String sang Long, nếu không hợp lệ sẽ thêm thông báo lỗi vào model.
	 * 
	 * @param value Giá trị đầu vào từ request
	 * @param model Model để truyền thông báo lỗi
	 * @param errorMessage Thông báo lỗi nếu giá trị không hợp lệ
	 * @return Giá trị Long hoặc null nếu không hợp lệ
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	private Long parseLongValue(String value, Model model, String errorMessage) {
		if (value != null && !value.isEmpty() && !"null".equals(value)) {
			try {
				return Long.parseLong(value);
			} catch (NumberFormatException e) {
				model.addAttribute("error", errorMessage);
				return null;
			}
		}
		return null;
	}

	/**
	 * Hàm chuyển đổi giá trị String thành LocalDate.
	 * 
	 * @param value Giá trị đầu vào từ request
	 * @return Giá trị LocalDate hoặc null nếu không hợp lệ
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	private LocalDate parseDateValue(String value) {
		if (value != null && !value.isEmpty()) {
			return LocalDate.parse(value);
		}
		return null;
	}

	/**
	 * Lọc các hợp đồng theo các tiêu chí đã chọn.
	 * 
	 * @param marketId ID thị trường
	 * @param airlineId ID hãng hàng không
	 * @param contractNumber Số hợp đồng
	 * @param fareType Loại giá vé
	 * @param parsedDiscontinueDate Ngày hết hiệu lực
	 * @param parsedEffectiveDate Ngày bắt đầu
	 * @param parsedDateReceived Ngày nhận hợp đồng
	 * @param page Trang hiện tại
	 * @param recordsPerPage Số bản ghi mỗi trang
	 * @return Danh sách hợp đồng đã lọc
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	private List<ContractDTOList> filterContracts(Long marketId, Long airlineId, String contractNumber, 
			String fareType, LocalDate parsedDiscontinueDate, LocalDate parsedEffectiveDate, 
			LocalDate parsedDateReceived, int page, int recordsPerPage) {
		// Gọi service để thực hiện logic lọc hợp đồng
		return contractService.formDtoMapEntity(contractService.filterContracts(marketId, airlineId, contractNumber, 
				fareType, parsedDiscontinueDate, parsedEffectiveDate, parsedDateReceived, page, recordsPerPage));
	}

	/**
	 * Xử lý yêu cầu lọc hợp đồng qua form và chuyển tiếp kết quả.
	 * 
	 * @param request Đối tượng chứa thông tin lọc
	 * @param redirectAttributes Chuyển tiếp các tham số tới /list
	 * @return Đường dẫn chuyển hướng tới /list
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@PostMapping("/filter")
	public String filterContracts(@ModelAttribute FilterConTractDTO request, RedirectAttributes redirectAttributes) {
		// Kiểm tra đầu vào bắt buộc: Market và Airline
		if (request.getMarket() == null || request.getAirline() == null) {
			throw new IllegalArgumentException("Market and Airline are required fields.");
		}

		// Thêm các tham số vào redirect attributes để chuyển tiếp (truyền thông tin qua /list)
		redirectAttributes.addAttribute("market", request.getMarket());
		redirectAttributes.addAttribute("airline", request.getAirline());
		redirectAttributes.addAttribute("contractNumber", request.getContractNumber());
		redirectAttributes.addAttribute("fareType", request.getFareType());
		redirectAttributes.addAttribute("discontinueDate", request.getDiscontinueDate());
		redirectAttributes.addAttribute("effectiveDate", request.getEffectiveDate());
		redirectAttributes.addAttribute("dateReceived", request.getDateReceived());
		redirectAttributes.addAttribute("action", "filter"); // Xác định đây là logic lọc

		// Chuyển hướng (redirect) đến /list để xử lý và hiển thị dữ liệu
		return "redirect:/list";
	}

	/**
	 * Lọc hợp đồng theo thị trường và hãng hàng không, trả về danh sách hợp đồng và các loại giá vé.
	 * @param market Thị trường
	 * @param airline Hãng hàng không
	 * @return Dữ liệu JSON chứa danh sách số hợp đồng và loại giá vé
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@GetMapping("/market-and-airline")
	public ResponseEntity<Map<String, List<Map<String, String>>>> filterContractsByMarketAndAirline(
			@RequestParam(required = false) String market, @RequestParam(required = false) String airline) {

		// Kiểm tra đầu vào hợp lệ
		if (market == null || airline == null || market.isEmpty() || airline.isEmpty()) {
			// Trả về danh sách rỗng nếu không có điều kiện lọc
			Map<String, List<Map<String, String>>> emptyResponse = new HashMap<>();
			emptyResponse.put("contractNumbers", new ArrayList<>());
			emptyResponse.put("fareTypes", new ArrayList<>());
			return ResponseEntity.ok(emptyResponse);
		}

		// Gọi service để lấy danh sách Contract Numbers và Fare Types đã lọc
		List<Map<String, String>> contractNumbers = contractService.getContractNumbersByMarketAndAirline(market, airline);
		List<Map<String, String>> fareTypes = contractService.getFareTypesByMarketAndAirline(market, airline);

		// Đảm bảo không trả về null (mặc dù contractService đã xử lý tốt, luôn kiểm tra lại)
		if (contractNumbers == null) {
			contractNumbers = new ArrayList<>();
		}

		if (fareTypes == null) {
			fareTypes = new ArrayList<>();
		}

		// Chuẩn bị JSON response
		Map<String, List<Map<String, String>>> response = new HashMap<>();
		response.put("contractNumbers", contractNumbers);
		response.put("fareTypes", fareTypes);

		// Trả về dữ liệu JSON cho client
		return ResponseEntity.ok(response);
	}
	
}
