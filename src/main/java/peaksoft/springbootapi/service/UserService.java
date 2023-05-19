package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.UserRequest;
import peaksoft.springbootapi.dto.UserResponse;
import peaksoft.springbootapi.entity.Role;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request){
        User user=new User();
        user.setUserName(request.getUsername());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRoleName()));
        user.setLocalDate(LocalDate.now());
        userRepository.save(user);
        return mapToResponse(user);
    }
    public UserResponse mapToResponse( User user){
       return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getLocalDate()).build();
    }
    public List<UserResponse>getAll(){
        List<UserResponse>userResponses=new ArrayList<>();
        for(User user:userRepository.findAll()){
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }
    public UserResponse getUserById(Long id){
        User user=userRepository.findById(id).get();
        return mapToResponse(user);
    }
    public UserResponse update(Long userId,UserRequest request){
        User user=userRepository.findById(userId).get();
        user.setUserName(request.getUsername());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(request.getRoleName()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long userId){
        userRepository.deleteById(userId);
        return "Successfully deleted user with id: "+userId;
    }
}
