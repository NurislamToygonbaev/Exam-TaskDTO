package lms.service.impl;

import lms.dto.request.EditCourseRequest;
import lms.dto.request.SaveCourseRequest;
import lms.dto.response.FindAllCoursesResponse;
import lms.dto.response.GetCourseResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.Company;
import lms.entities.Course;
import lms.repository.CompanyRepository;
import lms.repository.CourseRepository;
import lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepo;
    private final CompanyRepository companyRepo;

    private Course checkCourseId(Long courseId){
        return courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new NoSuchElementException("Course with id: "+courseId+" not found"));
    }

    @Override
    public List<FindAllCoursesResponse> findAll() {
        return courseRepo.findAllCourses();
    }

    @Override
    public SimpleResponse saveCourseTOCompany(Long companyId, SaveCourseRequest saveCourseRequest) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + " not found"));
        Course course = new Course();
        course.setCourseName(saveCourseRequest.courseName());
        course.setDateOfStart(saveCourseRequest.dateOfStart());
        course.setDescription(saveCourseRequest.description());
        course.setCompany(company);
        company.addCourses(course);
        courseRepo.save(course);
        return new SimpleResponse(HttpStatus.OK, "Course successfully saved");
    }

    @Override
    public GetCourseResponse findCourseById(Long courseId) {
        checkCourseId(courseId);
        return courseRepo.findCourseById(courseId);
    }

    @Override
    public SimpleResponse updateCourse(Long courseId, EditCourseRequest editCourse) {
        Course course = checkCourseId(courseId);
        course.setCourseName(editCourse.courseName());
        course.setDateOfStart(editCourse.dateOfStart());
        course.setDescription(editCourse.description());
        courseRepo.save(course);
        return new SimpleResponse(HttpStatus.OK, "successfully updated");
    }

    @Override
    public SimpleResponse deleteCourseById(Long courseId) {
        Course course = checkCourseId(courseId);
        courseRepo.delete(course);
        return new SimpleResponse(HttpStatus.OK, "successfully deleted");
    }

    @Override
    public List<GetCourseResponse> softCourseByDate(String ascOrDesc) {
        if (ascOrDesc.contains("a%")){
            return courseRepo.sortCourseByDate();
        }else {
            return courseRepo.sortCourseByDateDesc();
        }
    }
}
