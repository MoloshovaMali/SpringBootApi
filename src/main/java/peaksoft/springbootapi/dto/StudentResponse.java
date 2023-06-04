package peaksoft.springbootapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String groupName;
    private String roleName;
}
