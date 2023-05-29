package peaksoft.springbootapi.dto;

import org.springframework.stereotype.Component;
import peaksoft.springbootapi.entity.User;
@Component
public class LoginMapper {

    public LoginResponse loginView(String token, String message, User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage(message);
        loginResponse.setJwt(token);
        if (user != null){
            loginResponse.setAuthorities(user.getRole().getAuthority());
        }
        return loginResponse;
    }
}
