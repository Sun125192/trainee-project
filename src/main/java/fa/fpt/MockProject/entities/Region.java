package fa.fpt.MockProject.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model đại diện cho bảng REGION
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "REGION")
public class Region {

	/**
	 * Khóa chính của bảng REGION
	 */
	@Id
	@Column(name = "CODE", columnDefinition = "varchar(10)")
	private String code; // Mã khu vực (dạng chuỗi, tối đa 10 ký tự)

	/**
	 * Tên của khu vực
	 */
	@Column(name = "NAME", columnDefinition = "varchar(50)")
	private String name; // Tên khu vực (dạng chuỗi, tối đa 50 ký tự)

	/**
	 * Quan hệ một-nhiều với bảng CONTRACT
	 */
	@OneToMany(mappedBy = "region")
	private Set<Contract> contracts; // Tập hợp các hợp đồng liên quan đến khu vực này

	// Getters và Setters tự động tạo bởi Lombok.
}
