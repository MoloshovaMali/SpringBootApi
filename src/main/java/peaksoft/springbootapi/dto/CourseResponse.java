package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {
    private String courseName;
    private String durationMonth;
    private LocalDate localDate;
    private Boolean isActive;
    private Boolean isDeleted;
    private String companyName;

}
