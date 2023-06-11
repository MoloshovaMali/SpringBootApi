package peaksoft.springbootapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TeacherResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private LocalDate localDate;
    private Boolean isActive = true;
    private Boolean isDeleted = false;

}
