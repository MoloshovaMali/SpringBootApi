package peaksoft.springbootapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootapi.dto.GroupRequest;
import peaksoft.springbootapi.dto.GroupResponse;
import peaksoft.springbootapi.entity.Group;
import peaksoft.springbootapi.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public List<GroupResponse> getAllGroups(){
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            groupResponses.add(mapToResponse(group));
        }
        return groupResponses;
    }
    public GroupResponse getGroupById(Long groupId){
        Group group = groupRepository.findById(groupId).get();
        return mapToResponse(group);
    }
    public GroupResponse create(GroupRequest request){
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setStudents(group.getStudents());
        groupRepository.save(group);
        return mapToResponse(group);
    }
    public GroupResponse updateGroup(Long id,GroupRequest request){
        Group group = groupRepository.findById(id).get();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setStudents(group.getStudents());
        groupRepository.save(group);
        return mapToResponse(group);
    }
    public void deleteGroup(Long groupId){
        groupRepository.deleteById(groupId);
    }

    //    public Course mapToEntity(CourseRequest request){
//        Course course = new Course();
//        course.setCourseName(request.getCourseName());
//        course.setCompany(companyRepository.findById(request.getCompanyId()).get());
//        course.setDurationMonth(request.getDurationMonth());
//
//        return course;
//    }
    public GroupResponse mapToResponse(Group group){
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setDateOfFinish(group.getDateOfFinish());

        return groupResponse;
    }
//    public List<CourseResponse> view (List<Course>courses){
//        List<CourseResponse> courseResponses = new ArrayList<>();
//        for (Course course:courses){
//            courseResponses.add(mapToResponse(course));
//        }
//        return courseResponses;
//    }
//    public CourseResponseView searchAndPagination(String text, int page, int size){
//        Pageable pageable= PageRequest.of(page-1,size);
//        CourseResponseView courseResponseView = new CourseResponseView();
//        courseResponseView.setCourseResponses(view(search(text,pageable)));
//        return courseResponseView;
//    }
//
//    private List<Course> search(String text, Pageable pageable){
//        String name = text ==null?"": text;
//        return courseRepository.searchAndPagination(name.toUpperCase(), pageable);
//    }
}