package fa.fpt.MockProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.fpt.MockProject.dto.ContractDTOForm;
import fa.fpt.MockProject.entities.Contract;
import fa.fpt.MockProject.service.AirlineService;
import fa.fpt.MockProject.service.ContractService;
import fa.fpt.MockProject.service.CountryService;
import fa.fpt.MockProject.service.FareTypeService;
import fa.fpt.MockProject.service.PriorityService;
import fa.fpt.MockProject.service.RegionService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/contract")
public class UpdateController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private PriorityService priorityService;

	@Autowired
	private FareTypeService fareTypeService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private ContractService contractService;

	/**
	 * Hiển thị trang cập nhật hợp đồng.
	 * 
	 * @param contractNumber: Số hợp đồng cần cập nhật.
	 * @param model:          Model để truyền dữ liệu ra view.
	 * @return Trang cập nhật hợp đồng.
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@GetMapping("/update")
	public String showUpdate(@RequestParam(required = false) String contractNumber, Model model) {
		loadFormData(model); // Tải dữ liệu cần thiết cho form

		// Chuyển dữ liệu từ entity sang DTO và đưa vào model
		ContractDTOForm contractDTOForm = contractService.mapEntityToDtoForm(contractService.findById(contractNumber));

		model.addAttribute("contractForm", contractDTOForm);

		return "projectContractInfo/update-project-contract"; // Trả về view cập nhật hợp đồng
	}

	/**
	 * Xử lý cập nhật hợp đồng sau khi người dùng nhập dữ liệu.
	 * 
	 * @param contractDTOForm: Dữ liệu hợp đồng từ form.
	 * @param result:          Kết quả kiểm tra tính hợp lệ của dữ liệu.
	 * @param model:           Model để truyền dữ liệu ra view.
	 * @return Chuyển hướng trang sau khi cập nhật hợp đồng thành công.
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@PostMapping("/update")
	public String doUpdate(@Valid @ModelAttribute("contractForm") ContractDTOForm contractDTOForm, BindingResult result,
			Model model) {
		
		// Kiểm tra tính hợp lệ của dữ liệu đầu vào
		if (result.hasErrors()) {
			// Tải lại dữ liệu cho form nếu có lỗi
			loadFormData(model); 
			
			return "projectContractInfo/update-project-contract";
		}
		

		// Kiểm tra nếu hợp đồng đã bị thay đổi bởi người khác
		if (contractService.isContractModified(contractDTOForm)) {
			model.addAttribute("errorMessage",
					"The selected record has been changed by another user! Please refresh to get the latest data for your update.");

			ContractDTOForm latestContractForm = contractService
					.mapEntityToDtoForm(contractService.findById(contractDTOForm.getContractNumber()));

			model.addAttribute("contractForm", latestContractForm);
			
			// Tải lại dữ liệu nếu có thay đổi
			loadFormData(model);

			return "projectContractInfo/update-project-contract";
		}

		// Chuyển dữ liệu từ DTO sang entity
		Contract contract = contractService.mapDtoFormToEntity(contractDTOForm);

		Contract savedContract = contractService.create(contract);
	    
	    // Kiểm tra nếu hợp đồng được lưu thành công
	    if (savedContract != null) {
			return "redirect:/list"; // Chuyển hướng nếu cập nhật thành công
		} else {
			model.addAttribute("errorMessage", "Database connection error.");
			 // Tải lại dữ liệu nếu có lỗi kết nối
			loadFormData(model);
			return "projectContractInfo/update-project-contract";
		}
	}

	/**
	 * Tải các dữ liệu cần thiết cho form cập nhật hợp đồng.
	 * 
	 * @param model: Model để truyền dữ liệu ra view.
	 *
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	private void loadFormData(Model model) {
		
		// Tải dữ liệu từ các service để hiển thị trên form
		// Danh sách quốc gia
		model.addAttribute("listCountries", countryService.findAll()); 
		
		// Danh sách hãng hàng không
		model.addAttribute("listAirlines", airlineService.findAll()); 
		
		// Danh sách mức ưu tiên
		model.addAttribute("listPriorities", priorityService.findAll()); 
		
		 // Danh sách loại giá vé
		model.addAttribute("listFareTypes", fareTypeService.findAll());
		
		// Danh sách khu vực
		model.addAttribute("listRegions", regionService.findAll()); 
	}

}
