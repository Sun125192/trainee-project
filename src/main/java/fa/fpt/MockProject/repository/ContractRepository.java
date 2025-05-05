package fa.fpt.MockProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.fpt.MockProject.entities.Contract;

/**
 * Repository interface cho thực thể Contract.
 * 
 * @author Ngo Minh Nhat
 * @birthDay 2001-05-12
 * @class DN24_FRF_FJW_05
 */
public interface ContractRepository extends JpaRepository<Contract, String>, JpaSpecificationExecutor<Contract> {

	/**
	 * Lấy danh sách Contract Numbers dựa vào Market và Airline.
	 * 
	 * @param market  Mã quốc gia (Market).
	 * @param airline Mã hãng hàng không (Airline).
	 * @return Danh sách Contract Numbers.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@Query("SELECT DISTINCT c.contractNumber FROM Contract c WHERE c.country.countryCode = :market AND c.airline.code = :airline")
	List<String> findContractNumbersByMarketAndAirline(@Param("market") String market,
			@Param("airline") String airline);

	/**
	 * Lấy danh sách Fare Types dựa vào Market và Airline.
	 * 
	 * @param market  Mã quốc gia (Market).
	 * @param airline Mã hãng hàng không (Airline).
	 * @return Danh sách Fare Types.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@Query("SELECT DISTINCT c.fareType.code FROM Contract c WHERE c.country.countryCode = :market AND c.airline.code = :airline")
	List<String> findFareTypesByMarketAndAirline(@Param("market") String market, @Param("airline") String airline);

	/**
	 * Lọc danh sách Contract theo các tiêu chí Market, Airline, Contract Number và
	 * Fare Type.
	 * 
	 * @param country        Mã quốc gia (country).
	 * @param airline        Mã hãng hàng không (Airline).
	 * @param contractNumber Mã hợp đồng (Contract Number).
	 * @param fareType       Mã loại giá (Fare Type).
	 * @param pageable       Thông tin phân trang.
	 * @return Danh sách các Contract phù hợp với các tiêu chí lọc.
	 * @author Ngo Minh Nhat
	 * @birthDay 2001-05-12
	 * @class DN24_FRF_FJW_05
	 */
	@Query("SELECT c FROM Contract c " + "WHERE c.country.countryCode = :country " + "AND c.airline.code = :airline "
			+ "AND (:contractNumber IS NULL OR c.contractNumber = :contractNumber) "
			+ "AND (:fareType IS NULL OR c.fareType.code = :fareType)")
	Page<Contract> filterContractsByCriteria(@Param("country") Long country, @Param("airline") Long airline,
			@Param("contractNumber") String contractNumber, @Param("fareType") String fareType, Pageable pageable);
}
