package peaksoft.springbootapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springbootapi.dto.CourseRequest;
import peaksoft.springbootapi.dto.CourseResponse;
import peaksoft.springbootapi.service.CourseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAuthority('ADMIN')")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request){
        return courseService.create(request);
    }
}
