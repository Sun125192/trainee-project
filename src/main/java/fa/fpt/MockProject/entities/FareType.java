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
 * Model đại diện cho bảng FARE_TYPE
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FARE_TYPE")
public class FareType {

    /**
     * Khóa chính của bảng
     */
    @Id
    @Column(name = "CODE", columnDefinition = "varchar(1)")
    private String code; // Mã kiểu giá vé, ví dụ: "A", "B", "C"

    /**
     * Tên kiểu giá vé
     */
    @Column(name = "NAME", columnDefinition = "varchar(50)")
    private String name;

    /**
     * Quan hệ một-nhiều với bảng CONTRACT
     */
    @OneToMany(mappedBy = "fareType")
    private Set<Contract> contracts; // Tập hợp các hợp đồng liên quan đến kiểu giá vé này

	// Getters và Setters tự động tạo bởi Lombok
}
