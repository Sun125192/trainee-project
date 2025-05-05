package fa.fpt.MockProject.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity đại diện cho bảng SCHEDULE trong cơ sở dữ liệu.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SCHEDULE") // Ánh xạ tới bảng "SCHEDULE"
public class Schedule {

    /**
     * ID chính, tự động tăng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Cột "ID"
    private Long id;

    /**
     * Ngày bắt đầu dự kiến.
     */
    @Column(name = "EXPECTED_START_DATE") // Cột "EXPECTED_START_DATE"
    private LocalDate expectedStartDate;

    // Getters và Setters tự động tạo bởi Lombok.
}
