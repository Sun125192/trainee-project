package fa.fpt.MockProject.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model đại diện cho bảng COUNTRY
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "COUNTRY")
public class Country {

	/**
	 * Khóa chính tự động tăng
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_CODE")
	private Long countryCode;

	/**
	 * Tên quốc gia
	 */
	@Column(name = "NAME", columnDefinition = "varchar(50)")
	private String name;

	/**
	 *  Liên kết một-nhiều với bảng CONTRACT
	 */
	@OneToMany(mappedBy = "country")
	private Set<Contract> contracts;

	// Getters và Setters tự động tạo bởi Lombok
}
