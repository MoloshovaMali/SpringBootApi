package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.*;
import peaksoft.springbootapi.entity.Company;
import peaksoft.springbootapi.entity.Role;
import peaksoft.springbootapi.entity.User;
import peaksoft.springbootapi.repository.UserRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        System.out.println("I'm user service");
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRoleName() == null) {
            user.setRole(Role.STUDENT);
        } else {
            user.setRole(Role.valueOf(request.getRoleName()));
        }
        user.setLocalDate(LocalDate.now());
        repository.save(user);
        System.out.println("I'm done in user service");
        return mapToResponse(user);
    }

    public UserResponse mapToResponse(User user) {
        System.out.println("I'm in response method");
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .localDate(user.getLocalDate()).build();
    }

    public List<UserResponse> getAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : repository.findAll()) {
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }

    public UserResponse getUserById(Long id) {
        User user = repository.findById(id).get();
        return mapToResponse(user);
    }

    public UserResponse update(Long userId, UserRequest request) {
        User user = repository.findById(userId).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(request.getRoleName()));
        repository.save(user);
        return mapToResponse(user);
    }

    public void delete(Long userId) {
        User user = repository.findById(userId).get();
        if (!userId.equals(user.getId())) {
            log.error("User not found!");
        } else {
            repository.delete(repository.findById(userId).get());

        }
    }
    public UserResponse changeRole(Long userId, ChangeRoleRequest request){
        User user = repository.findById(userId).get();
        user.setRole(Role.valueOf(request.getRoleName()));
        repository.save(user);
        return mapToResponse(user);
    }
    public UserResponseView searchAndPagination(String text, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, size);//1
        UserResponseView userResponseView=new UserResponseView();
        userResponseView.setUserResponses(view(search(text,pageable)));
        return userResponseView;
    }
    public List<UserResponse> view(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }


    private List<User> search(String text, Pageable pageable) {
        String name = text == null ? "" : text;
        return repository.searchAndPagination(name.toUpperCase(),pageable);
    }
}