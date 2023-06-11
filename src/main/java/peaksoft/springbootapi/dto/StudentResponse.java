package peaksoft.springbootapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootapi.entity.Group;

import java.time.LocalDate;


@Getter
@Setter
@Builder

public class StudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private LocalDate localDate;
    private String groupName;

}
