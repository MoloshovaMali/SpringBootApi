package peaksoft.springbootapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String  jwt;
    public String message;
    private String authorities;

}
