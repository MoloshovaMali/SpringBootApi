package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Long courseId;
    private Boolean isActive = true;
    private Boolean isDeleted = false;

}
