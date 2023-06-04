package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.StudentRequest;
import peaksoft.springbootapi.dto.StudentResponse;
import peaksoft.springbootapi.dto.UserRequest;
import peaksoft.springbootapi.dto.UserResponse;
import peaksoft.springbootapi.entity.Group;
import peaksoft.springbootapi.entity.Role;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.GroupRepository;
import peaksoft.springbootapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    public UserResponse registration(UserRequest request){
        User user = new User();
        user.setFirstName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        return UserResponse.builder().id(user.getId())
                .username(user.getUsername())
                .roleName(user.getRole().name()).build();
    }
    public StudentResponse changeRole(Long userId, ChangeRoleRequest request){
        User user = userRepository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public StudentResponse mapToResponse(User user){
        return StudentResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .build();

    }
    public List<StudentResponse> getAll(){
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            studentResponses.add(mapToResponse(user));
        }
        return studentResponses;
    }
    public StudentResponse getUserById(Long userId){
        User user = userRepository.findById(userId).get();
        return mapToResponse(user);
    }

    public StudentResponse update(Long userId, StudentRequest request){
        User user = userRepository.findById(userId).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public String delete(Long studentId){
        userRepository.deleteById(studentId);
        return "Successfully deleted student with id: "+studentId;
    }
}
