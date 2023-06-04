package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Long groupId;
}
