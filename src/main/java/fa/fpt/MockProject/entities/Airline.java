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
 * Lớp đại diện cho thông tin Airline.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AIRLINE")
public class Airline {

    /** 
     * Mã định danh duy nhất của Airline. 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE")
	private Long code;

	  /** 
     * Tên Airline (tối đa 5 ký tự). 
     */
	@Column(name = "NAME", columnDefinition = "VARCHAR(5)")
	private String name;
	
	/**
     * Danh sách hợp đồng liên quan đến Airline.
     */
	@OneToMany(mappedBy = "airline")
	private Set<Contract> contracts;

	// Getters và Setters tự động tạo bởi Lombok
}
