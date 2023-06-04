package peaksoft.springbootapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootapi.dto.TeacherRequest;
import peaksoft.springbootapi.dto.TeacherResponse;
import peaksoft.springbootapi.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;
    @GetMapping()
    public List<TeacherResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    public TeacherResponse getById(@PathVariable("id") Long userId){
        return service.getUserById(userId);
    }
    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request){
        return service.create(request);
    }
    @PutMapping("{id}")
    public TeacherResponse update(@PathVariable("id")Long userId, @RequestBody TeacherRequest request){
        return service.update(userId,request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long teacherId){
        return service.delete(teacherId);
    }
    @PutMapping("change-role/{id}")
    public TeacherResponse changeRole(@PathVariable("id") Long id,@RequestBody ChangeRoleRequest request){
        return service.changeRole(id,request);
    }
}
