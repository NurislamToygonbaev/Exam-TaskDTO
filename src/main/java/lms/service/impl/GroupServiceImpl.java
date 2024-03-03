package lms.service.impl;

import lms.dto.request.EditGroupRequest;
import lms.dto.request.SaveGroupRequest;
import lms.dto.response.FindAllGroups;
import lms.dto.response.SimpleResponse;
import lms.entities.Course;
import lms.entities.Group;
import lms.repository.CourseRepository;
import lms.repository.GroupRepository;
import lms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepo;
    private final CourseRepository courseRepo;

    private Group checkId(Long groupId){
        return groupRepo.findById(groupId)
                .orElseThrow(() ->
                        new NoSuchElementException("Group with Id: "+groupId+" not found"));
    }
    @Override
    public List<FindAllGroups> findAll() {
        return groupRepo.findAllGroups();
    }

    @Override
    public SimpleResponse saveGroup(SaveGroupRequest saveGroupRequest) {
        List<Long> courseIds = saveGroupRequest.courseIds();
        List<Course> courses = courseRepo. findCoursesWithIds(courseIds);
        Group group = new Group();
        group.setGroupName(saveGroupRequest.groupName());
        group.setImageLink(saveGroupRequest.imageLink());
        group.setDescription(saveGroupRequest.description());
        for (Course course : courses) {
            course.addGroup(group);
            group.addCourse(course);
        }
        groupRepo.save(group);
        return new SimpleResponse(HttpStatus.OK, "Group Successfully saved");
    }

    @Override
    public FindAllGroups getById(Long groupId) {
        checkId(groupId);
        return groupRepo.getGroupById(groupId);
    }

    @Override
    public SimpleResponse updateGroup(Long groupId, EditGroupRequest editGroupRequest) {
        try {
            Group group = checkId(groupId);
            List<Long> courseIds = editGroupRequest.courseIds();
            List<Course> courses = courseRepo.findCoursesWithIds(courseIds);
            group.setGroupName(editGroupRequest.groupName());
            group.setImageLink(editGroupRequest.imageLink());
            group.setDescription(editGroupRequest.description());
            group.setCourses(courses);
            for (Course course : courses) {
                course.addGroup(group);
            }
            groupRepo.save(group);
            return new SimpleResponse(HttpStatus.OK, "successfully updated");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long groupId) {
        try {
            Group group = checkId(groupId);
            groupRepo.delete(group);
            return new SimpleResponse(HttpStatus.OK, "successfully deleted");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public Integer countStudentsOfGroup(Long groupId) {
        return groupRepo.countStudentsOfGroup(groupId);
    }


}
