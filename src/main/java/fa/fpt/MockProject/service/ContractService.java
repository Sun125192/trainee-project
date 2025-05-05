package fa.fpt.MockProject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.fpt.MockProject.dto.ContractDTOForm;
import fa.fpt.MockProject.dto.ContractDTOList;
import fa.fpt.MockProject.entities.Contract;
import fa.fpt.MockProject.entities.FareType;
import fa.fpt.MockProject.repository.AirlineRepository;
import fa.fpt.MockProject.repository.ContractRepository;
import fa.fpt.MockProject.repository.CountryRepository;
import fa.fpt.MockProject.repository.FareTypeRepository;
import fa.fpt.MockProject.repository.PriorityRepository;
import fa.fpt.MockProject.repository.RegionRepository;
import fa.fpt.MockProject.specification.ContractSpecifications;

/**
 * Service class cung cấp các phương thức để xử lý hợp đồng.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
@Service
@Transactional
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private FareTypeRepository fareTypeRepository;

	@Autowired
	private PriorityRepository priorityRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private FareTypeService fareTypeService;

	/**
	 * Tạo một hợp đồng mới và lưu vào cơ sở dữ liệu.
	 * 
	 * @param contract đối tượng hợp đồng cần lưu.
	 * @return đối tượng hợp đồng sau khi lưu.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public Contract create(Contract contract) {
		return contractRepository.save(contract);
	}


	/**
	 * Tìm kiếm hợp đồng theo ID.
	 * 
	 * @param id mã hợp đồng cần tìm.
	 * @return đối tượng hợp đồng tương ứng hoặc null nếu không tìm thấy.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public Contract findById(String id) {
		return contractRepository.findById(id).orElse(null);
	}

	/**
	 * Lấy danh sách hợp đồng theo phân trang.
	 * 
	 * @param start    chỉ số trang bắt đầu.
	 * @param pageSize số lượng hợp đồng trong mỗi trang.
	 * @return danh sách hợp đồng theo trang.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Contract> findAllByPage(int start, int pageSize) {
		Pageable pageable = PageRequest.of(start - 1, pageSize);
		
		Page<Contract> listPage = contractRepository.findAll(pageable);
		
		return listPage.getContent();
	}

	/**
	 * Lấy tổng số hợp đồng trong cơ sở dữ liệu.
	 * 
	 * @return tổng số hợp đồng.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public long totalrecord() {
		return contractRepository.count();
	}

	/**
	 * Lấy danh sách các số hợp đồng theo thị trường và hãng hàng không.
	 * 
	 * @param market  thị trường.
	 * @param airline hãng hàng không.
	 * @return danh sách các số hợp đồng dưới dạng Map.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Map<String, String>> getContractNumbersByMarketAndAirline(String market, String airline) {
		
		List<String> contractNumbers = contractRepository.findContractNumbersByMarketAndAirline(market, airline);
		
		return contractNumbers.stream().map(contractNumber -> Map.of("value", contractNumber, "text", contractNumber))
				.collect(Collectors.toList());
	}

	/**
	 * Lấy danh sách các loại giá vé theo thị trường và hãng hàng không.
	 * 
	 * @param market  thị trường.
	 * @param airline hãng hàng không.
	 * @return danh sách các loại giá vé dưới dạng Map.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Map<String, String>> getFareTypesByMarketAndAirline(String market, String airline) {
		
		List<String> fareTypes = contractRepository.findFareTypesByMarketAndAirline(market, airline);
		
		List<FareType> fareTypeList = fareTypeService.findAll();
		
		List<FareType> fareTypeShow = new ArrayList<>();

		for (String string : fareTypes) {
			for (FareType fareType : fareTypeList) {
				if (string.equals(fareType.getCode())) {
					fareTypeShow.add(fareType);
				}
			}
		}

		return fareTypeShow.stream().map(fareType -> Map.of("value", fareType.getCode(), "text",
				fareType.getCode() + " - " + fareType.getName())).collect(Collectors.toList());
	}

	/**
	 * Đếm số lượng hợp đồng sau khi áp dụng các bộ lọc.
	 * 
	 * @param market          thị trường.
	 * @param airline         hãng hàng không.
	 * @param contractNumber  mã hợp đồng.
	 * @param fareType        loại giá vé.
	 * @param discontinueDate ngày hết hiệu lực.
	 * @param effectiveDate   ngày bắt đầu hiệu lực.
	 * @param dateReceived    ngày nhận hợp đồng.
	 * @param start           chỉ số trang bắt đầu.
	 * @param pageSize        số lượng hợp đồng trong mỗi trang.
	 * @return tổng số hợp đồng sau khi lọc.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public long countFilteredRecords(Long market, Long airline, String contractNumber, String fareType,
			LocalDate discontinueDate, LocalDate effectiveDate, LocalDate dateReceived, int start, int pageSize) {
		
		Specification<Contract> spec = ContractSpecifications.byCriteria(market, airline, contractNumber, fareType,
				discontinueDate, effectiveDate, dateReceived);
		
		Pageable pageable = PageRequest.of(start - 1, pageSize);
		
		Page<Contract> page = contractRepository.findAll(spec, pageable);
		
		return page.getTotalElements();
	}

	/**
	 * Lọc các hợp đồng theo các tiêu chí tìm kiếm và phân trang.
	 * 
	 * @param market          thị trường.
	 * @param airline         hãng hàng không.
	 * @param contractNumber  mã hợp đồng.
	 * @param fareType        loại giá vé.
	 * @param discontinueDate ngày hết hiệu lực.
	 * @param effectiveDate   ngày bắt đầu hiệu lực.
	 * @param dateReceived    ngày nhận hợp đồng.
	 * @param start           chỉ số trang bắt đầu.
	 * @param pageSize        số lượng hợp đồng trong mỗi trang.
	 * @return danh sách hợp đồng sau khi lọc.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<Contract> filterContracts(Long market, Long airline, String contractNumber, String fareType,
			LocalDate discontinueDate, LocalDate effectiveDate, LocalDate dateReceived, int start, int pageSize) {
		
		Specification<Contract> spec = ContractSpecifications.byCriteria(market, airline, contractNumber, fareType,
				discontinueDate, effectiveDate, dateReceived);
		
		Pageable pageable = PageRequest.of(start - 1, pageSize);
		
		Page<Contract> page = contractRepository.findAll(spec, pageable);
		
		return page.getContent();
	}

	/**
	 * Kiểm tra xem hợp đồng đã được chỉnh sửa hay chưa.
	 * 
	 * @param contractDTOForm đối tượng DTO chứa thông tin hợp đồng.
	 * @return true nếu hợp đồng đã được chỉnh sửa, false nếu không.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public boolean isContractModified(ContractDTOForm contractDTOForm) {
		
		Contract contractInDb = findById(contractDTOForm.getContractNumber());
		
		if (contractInDb != null) {
			return !contractInDb.getEffectiveDate().equals(contractDTOForm.getEffectiveDate());
		}
		
		return false;
	}

	/**
	 * Chuyển đổi danh sách hợp đồng thành danh sách DTO.
	 * 
	 * @param listContract danh sách hợp đồng cần chuyển đổi.
	 * @return danh sách DTO.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public List<ContractDTOList> formDtoMapEntity(List<Contract> listContract) {
		List<ContractDTOList> listContractDTOList = new ArrayList<>();
		
		for (Contract contract : listContract) {
			
			ContractDTOList contractDTOList = new ContractDTOList();
			
			contractDTOList.setContractNumber(contract.getContractNumber());
			contractDTOList.setAirlineCode(contract.getAirline().getName());
			contractDTOList.setMarket(contract.getCountry().getName());
			contractDTOList.setRegion(contract.getRegion().getName());
			contractDTOList.setFareType(contract.getFareType().getName());
			contractDTOList.setDateReceived(contract.getDateReceived());
			contractDTOList.setEffectiveDate(contract.getEffectiveDate());
			contractDTOList.setDiscontinueDate(contract.getDiscontinueDate());
			contractDTOList.setPriority(contract.getPriority().getName());

			listContractDTOList.add(contractDTOList);
		}
		return listContractDTOList;
	}

	/**
	 * Chuyển đổi hợp đồng entity thành DTO.
	 * 
	 * @param contract đối tượng hợp đồng cần chuyển đổi.
	 * @return DTO chứa thông tin hợp đồng.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public ContractDTOForm mapEntityToDtoForm(Contract contract) {
		
		ContractDTOForm contractDTOForm = new ContractDTOForm();

		contractDTOForm.setContractNumber(contract.getContractNumber());
		contractDTOForm.setContractName(contract.getName());
		contractDTOForm.setAirlineCode(contract.getAirline().getCode());
		contractDTOForm.setCountryCode(contract.getCountry().getCountryCode());
		contractDTOForm.setImportantLevel(contract.getImportantLevel());
		contractDTOForm.setPriority(contract.getPriority().getCode());
		contractDTOForm.setRegion(contract.getRegion().getCode());
		contractDTOForm.setFareType(contract.getFareType().getCode());
		contractDTOForm.setDateReceived(contract.getDateReceived());
		contractDTOForm.setEffectiveDate(contract.getEffectiveDate());
		contractDTOForm.setDiscontinueDate(contract.getDiscontinueDate());
		contractDTOForm.setDateClarificationCleared(contract.getDateClarificationCleared());
		contractDTOForm.setNumberOfFareSheets(contract.getNumberOfFareSheets());
		contractDTOForm.setNumberOfFares(contract.getNumberOfFares());
		contractDTOForm.setRtgsActual(contract.getRtgsActual());
		contractDTOForm.setRtgsRecords(contract.getRtgsRecords());
		contractDTOForm.setNumberOfRules(contract.getNumberOfRules());
		contractDTOForm.setTotalRecords(contract.getTotalRecords());
		contractDTOForm.setQueuingStartDate(contract.getQueuingStartDate());
		contractDTOForm.setQueuingEndDate(contract.getQueuingEndDate());

		return contractDTOForm;
	}

	/**
	 * Chuyển đổi DTO thành đối tượng hợp đồng.
	 * 
	 * @param contractDTOForm DTO chứa thông tin hợp đồng.
	 * @return đối tượng hợp đồng.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	public Contract mapDtoFormToEntity(ContractDTOForm contractDTOForm) {
		Contract contract = new Contract();

		contract.setContractNumber(contractDTOForm.getContractNumber());
		contract.setName(contractDTOForm.getContractName());
		contract.setAirline(airlineRepository.findById(contractDTOForm.getAirlineCode()).orElse(null));
		contract.setCountry(countryRepository.findById(contractDTOForm.getCountryCode()).orElse(null));
		contract.setImportantLevel(contractDTOForm.getImportantLevel());
		contract.setPriority(priorityRepository.findById(contractDTOForm.getPriority()).orElse(null));
		contract.setRegion(regionRepository.findById(contractDTOForm.getRegion()).orElse(null));
		contract.setFareType(fareTypeRepository.findById(contractDTOForm.getFareType()).orElse(null));
		contract.setDateReceived(contractDTOForm.getDateReceived());
		contract.setEffectiveDate(contractDTOForm.getEffectiveDate());
		contract.setDiscontinueDate(contractDTOForm.getDiscontinueDate());
		contract.setDateClarificationCleared(contractDTOForm.getDateClarificationCleared());
		contract.setNumberOfFareSheets(contractDTOForm.getNumberOfFareSheets());
		contract.setNumberOfFares(contractDTOForm.getNumberOfFares());
		contract.setRtgsActual(contractDTOForm.getRtgsActual());
		contract.setRtgsRecords(contractDTOForm.getRtgsRecords());
		contract.setNumberOfRules(contractDTOForm.getNumberOfRules());
		contract.setTotalRecords(contractDTOForm.getTotalRecords());
		contract.setQueuingStartDate(contractDTOForm.getQueuingStartDate());
		contract.setQueuingEndDate(contractDTOForm.getQueuingEndDate());

		return contract;
	}
	
}
