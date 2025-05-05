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
 * Model đại diện cho bảng PRIORITY
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PRIORITY")
public class Priority {

    /**
     * Khóa chính của bảng PRIORITY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE")
    private Long code; // Mã ưu tiên, sử dụng kiểu Long và tự động tăng giá trị

    /**
     * Tên của mức độ ưu tiên
     */
    @Column(name = "NAME", columnDefinition = "varchar(50)")
    private String name;

    /**
     * Quan hệ một-nhiều với bảng CONTRACT
     */
    @OneToMany(mappedBy = "priority")
    private Set<Contract> contracts; // Tập hợp các hợp đồng liên quan đến mức độ ưu tiên này

    // Getters và Setters tự động tạo bởi Lombok
}
