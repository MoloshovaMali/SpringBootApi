package peaksoft.springbootapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.ChangeRoleRequest;
import peaksoft.springbootapi.dto.StudentRequest;
import peaksoft.springbootapi.dto.StudentResponse;
import peaksoft.springbootapi.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Student Auth",description = "We can create new Student")
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    @PostMapping
    @Operation(summary = "Create",description = "Admin can create new Student")
    public StudentResponse create(@RequestBody StudentRequest request){
        return service.registration(request);
    }
    @PostMapping
    @Operation(summary = "Get all students",description = "Only Admin get all Students")
    public List<StudentResponse> getAllStudents(){
        return service.getAllStudents();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get by id", description = "Admin can get Student by id")
    public StudentResponse getStudentById(@PathVariable("id") Long id){
        return service.getStudentById(id);
    }
    @PutMapping("{id}")
    @Operation(summary = "Update", description = "Admin can update Student")
    public StudentResponse update(@PathVariable("id")Long id, @RequestBody StudentRequest request){
        return service.updateStudent(id,request);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete",description = "Admin can delete Student by id")
    public String delete(@PathVariable("id")Long id){
        service.delete(id);
        return "SUCCESSFULLY DELETED STUDENT WITH ID--" +id;
    }
    @PutMapping("change-role/{id}")
    public StudentResponse changeRole(@PathVariable("id") Long id, @RequestBody ChangeRoleRequest request){
        return service.changeRole(id,request);
    }}
