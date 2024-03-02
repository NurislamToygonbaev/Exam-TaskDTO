package lms.api;

import lms.dto.request.EditCourseRequest;
import lms.dto.request.SaveCourseRequest;
import lms.dto.response.FindAllCoursesResponse;
import lms.dto.response.GetCourseResponse;
import lms.dto.response.SimpleResponse;
import lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<FindAllCoursesResponse> findAll(){
        return courseService.findAll();
    }

    @PostMapping("/save/{companyId}")
    public SimpleResponse saveCourse(@PathVariable Long companyId,
                                     @RequestBody SaveCourseRequest saveCourseRequest){
        return courseService.saveCourseTOCompany(companyId, saveCourseRequest);
    }

    @GetMapping("/getById/{courseId}")
    public GetCourseResponse findCourseById(@PathVariable Long courseId){
        return courseService.findCourseById(courseId);
    }

    @PutMapping("/update/{courseId}")
    public SimpleResponse updateCourseById(@RequestBody EditCourseRequest editCourse,
                                           @PathVariable Long courseId){
        return courseService.updateCourse(courseId, editCourse);
    }

    @DeleteMapping("/delete/{courseId}")
    public SimpleResponse deleteCourseById(@PathVariable Long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @GetMapping("/sortByDate")
    public List<GetCourseResponse> sortByDate(@RequestParam String ascOrDesc){
        return courseService.softCourseByDate(ascOrDesc);
    }
}
