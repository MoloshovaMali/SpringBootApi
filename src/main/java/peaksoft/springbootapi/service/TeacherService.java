package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.TeacherRequest;
import peaksoft.springbootapi.dto.TeacherResponse;
import peaksoft.springbootapi.dto.UserResponse;
import peaksoft.springbootapi.entity.Role;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final UserRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(TeacherRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.INSTRUCTOR);
        user.setEmail(request.getEmail());
        user.setCreatedDate(LocalDate.now());
        teacherRepository.save(user);
        return mapToResponse(user);
    }

    public TeacherResponse mapToResponse(User user) {
        return TeacherResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getCreatedDate()).build();

    }

    public TeacherResponse changeRole(Long userId, ChangeRoleRequest request) {
        User user = teacherRepository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        teacherRepository.save(user);
        return mapToResponse(user);
    }

    public List<TeacherResponse> getAll() {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (User user : teacherRepository.findAll()) {
            teacherResponses.add(mapToResponse(user));
        }
        return teacherResponses;
    }

    public TeacherResponse getUserById(Long userId) {
        User user = teacherRepository.findById(userId).get();
        return mapToResponse(user);
    }

    public TeacherResponse update(Long userId, TeacherRequest request) {
        User user = teacherRepository.findById(userId).get();
        user.setUserName(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRoleName()));
        teacherRepository.save(user);
        return mapToResponse(user);
    }

    public String delete(Long teacherId) {
        teacherRepository.deleteById(teacherId);
        return "Successfully deleted user with id: " + teacherId;
    }

}
