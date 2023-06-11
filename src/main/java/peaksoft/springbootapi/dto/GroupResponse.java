package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootapi.entity.Course;

import java.time.LocalDate;


@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    private LocalDate localDate;
    private  Boolean isActivity=true;
    private  Boolean isDelete=false;
    private Course courses;
}
