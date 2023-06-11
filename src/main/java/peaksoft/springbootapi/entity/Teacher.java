package peaksoft.springbootapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @CreatedDate
    private LocalDate localDate;
    private Boolean isActive = true;
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    private Course course;

    @Transient
    private Long CourseId;
}

