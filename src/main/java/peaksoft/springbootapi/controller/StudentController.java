package peaksoft.springbootapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.StudentRequest;
import peaksoft.springbootapi.dto.StudentResponse;
import peaksoft.springbootapi.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    @GetMapping()
    public List<StudentResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public StudentResponse getById(@PathVariable("id") Long userId){
        return service.getUserById(userId);
    }
    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request){
        return service.registration(request);
    }
    @PutMapping("{id}")
    public StudentResponse update(@PathVariable("id")Long userId, @RequestBody StudentRequest request){
        return service.update(userId,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long studentId){
        return service.delete(studentId);
    }
    @PutMapping("change-role/{id}")
    public StudentResponse changeRole(@PathVariable("id") Long id,@RequestBody ChangeRoleRequest request ){
        return service.changeRole(id,request);
    }}
