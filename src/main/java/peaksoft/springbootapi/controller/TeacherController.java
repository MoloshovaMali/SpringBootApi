package peaksoft.springbootapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.ChangeRoleRequest;
import peaksoft.springbootapi.dto.TeacherRequest;
import peaksoft.springbootapi.dto.TeacherResponse;
import peaksoft.springbootapi.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Teacher Auth",description = "We can create new Teacher")
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;
    @PostMapping
    @Operation(summary = "Create",description = "Admin can create new Teacher")
    public TeacherResponse create(@RequestBody TeacherRequest request){
        return service.creatTeacher(request);
    }
    @GetMapping("all")
    @Operation(summary = "Get all teachers",description = "Only Admin get all Teachers")
    public List<TeacherResponse> getAll(){
        return service.getAllTeachers();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get by id",description = "Admin can get Teacher by id")
    public TeacherResponse getTeacherById(@PathVariable("id") Long id){
        return service.getTeacherById(id);
    }
    @PutMapping("{id}")
    @Operation(summary = "Update",description = "Admin can update Teacher")
    public TeacherResponse update(@PathVariable("id")Long id, @RequestBody TeacherRequest request){
        return service.updateTeacher(id,request);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete",description = "Admin can delete Teacher by id")
    public String delete(@PathVariable("id")Long id){
        service.deleteTeacher(id);
        return "SUCCESSFULLY DELETE TEACHER WITH ID--" +id;
    }
    @PutMapping("change-role/{id}")
    public TeacherResponse changeRole(@PathVariable("id") Long id, @RequestBody ChangeRoleRequest request){
        return service.changeRole(id,request);
    }
}
