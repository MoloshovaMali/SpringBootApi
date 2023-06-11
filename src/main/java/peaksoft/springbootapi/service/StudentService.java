package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.ChangeRoleRequest;
import peaksoft.springbootapi.dto.StudentRequest;
import peaksoft.springbootapi.dto.StudentResponse;
import peaksoft.springbootapi.entity.Group;
import peaksoft.springbootapi.entity.Role;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.GroupRepository;
import peaksoft.springbootapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public StudentResponse registration(StudentRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        user.setLocalDate(LocalDate.now());
        userRepository.save(user);
        return mapToResponse(user);
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
                .email(user.getEmail())
                .lastName(user.getLastName())
                .groupName(user.getGroup().getGroupName())
                .roleName(user.getRole().name())
                .localDate(user.getLocalDate())
                .build();

    }
    public List<StudentResponse> getAll(){
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
                studentResponses.add(mapToResponse(user));
        }
        return studentResponses;
    }

    public StudentResponse getStudentById(Long id) {
        User user = userRepository.findById(id).get();
       return mapToResponse(user);
    }

    public StudentResponse updateStudent(Long userId, StudentRequest request){
        User user = userRepository.findById(userId).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }


    public String delete(Long studentId) {
        userRepository.deleteById(studentId);
        return "Successfully deleted student with id : " + studentId;
    }
    public List<StudentResponse> getAllStudents(){
        List<User> users = userRepository.findAll();
        List<User> students = new ArrayList<>();
        for(User user : users){
            if(user.getRole().getAuthority().equals("STUDENT")){
                students.add(user);
            }
        }
        List<StudentResponse> responses = new ArrayList<>();
        for (User user : students){
            responses.add(mapToResponse(user));
        }
        return responses;
    }


}
