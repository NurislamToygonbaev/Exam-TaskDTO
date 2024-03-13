package lms.api;

import lms.dto.request.EditCourseRequest;
import lms.dto.request.SaveCourseRequest;
import lms.dto.response.FindAllCoursesResponse;
import lms.dto.response.GetCourseResponse;
import lms.dto.response.SimpleResponse;
import lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class    CourseApi {
    private final CourseService courseService;

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/{companyId}")
    public List<FindAllCoursesResponse> findAll(@PathVariable Long companyId){
        return courseService.findAll(companyId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/save/{companyId}")
    public SimpleResponse saveCourse(@PathVariable Long companyId,
                                     @RequestBody SaveCourseRequest saveCourseRequest){
        return courseService.saveCourseTOCompany(companyId, saveCourseRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/getById/{courseId}")
    public GetCourseResponse findCourseById(@PathVariable Long courseId){
        return courseService.findCourseById(courseId);
    }

    @Secured({"ADMIN"})
    @PutMapping("/update/{courseId}")
    public SimpleResponse updateCourseById(@RequestBody EditCourseRequest editCourse,
                                           @PathVariable Long courseId){
        return courseService.updateCourse(courseId, editCourse);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/delete/{courseId}")
    public SimpleResponse deleteCourseById(@PathVariable Long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/sortByDate")
    public List<GetCourseResponse> sortByDate(@RequestParam String ascOrDesc){
        return courseService.softCourseByDate(ascOrDesc);
    }
}
