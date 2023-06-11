package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {
    private String jwt;
    private String username;
    private String password;
    private String email;

}
