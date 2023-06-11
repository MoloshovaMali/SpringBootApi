package peaksoft.springbootapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.ChangeRoleRequest;
import peaksoft.springbootapi.dto.UserRequest;
import peaksoft.springbootapi.dto.UserResponse;
import peaksoft.springbootapi.dto.UserResponseView;
import peaksoft.springbootapi.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @GetMapping()
    public List<UserResponse> getAll(){
        return userService.getAll();
    }
    @PostMapping
    public UserResponse create(@RequestBody UserRequest request){
        return userService.create(request);
    }
    @GetMapping("{id}")
    public UserResponse getById(@PathVariable("id")Long userId){
        return userService.getUserById(userId);
    }
    @PutMapping("{id}")
    public UserResponse update(@PathVariable("id")Long userId,@RequestBody UserRequest request){
        return userService.update(userId,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return  "Successfully User delete with id - "+ userId;
    }
    @PutMapping("change-role/{id}")
    public UserResponse changeRole(@PathVariable("id") Long id,@RequestBody ChangeRoleRequest request){
        return userService.changeRole(id,request);
    }
    @GetMapping("all")
    public UserResponseView getAllUsers(@RequestParam(name = "text",required = false)String text,
                                        @RequestParam int page,
                                        @RequestParam int size){
        return userService.searchAndPagination(text,page,size);
    }
}
