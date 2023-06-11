package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseView {
    private List<UserResponse>userResponses;
}
