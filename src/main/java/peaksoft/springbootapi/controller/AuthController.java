package peaksoft.springbootapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springbootapi.dto.*;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.UserRepository;
import peaksoft.springbootapi.security.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
@Tag(name = "Auth Api")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("signup")
    @Operation(summary = "Sign up",description = "User can register")
    public UserResponse signUp(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);

    }
    @PostMapping("sign-in")
    @Operation(summary = "Sign in",description = "User can sign in")
    public LoginResponse signInI(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        authenticationManager.authenticate(token);
        User user=userRepository.findByUserName(token.getName()).get();
        return loginMapper.loginView(jwtTokenUtil.generateToken(user),"Successfully",user);

    }

}
