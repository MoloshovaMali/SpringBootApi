package peaksoft.springbootapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "course_name")
        private String courseName;
        @Column(name = "duration_month")
        private String durationMonth;
        @CreatedDate
        private LocalDate localDate;
        @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
        @JoinColumn(name = "company_id")
        private Company company;
        private Boolean isActive = true;
        private Boolean isDeleted = false;
        //    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinTable(name = "courses_groups",
//            joinColumns = @JoinColumn(name ="courses_id"),
//            inverseJoinColumns = @JoinColumn(name = "groups_id"))
//    private List<Group> groups;
        @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
        @JsonIgnore
        private User teacher;
}