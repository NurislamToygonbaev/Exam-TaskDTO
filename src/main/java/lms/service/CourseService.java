package lms.service;

import lms.dto.request.EditCourseRequest;
import lms.dto.request.SaveCourseRequest;
import lms.dto.response.FindAllCoursesResponse;
import lms.dto.response.GetCourseResponse;
import lms.dto.response.SimpleResponse;

import java.util.List;

public interface CourseService {
    List<FindAllCoursesResponse> findAll(Long companyId);

    SimpleResponse saveCourseTOCompany(Long companyId, SaveCourseRequest saveCourseRequest);

    GetCourseResponse findCourseById(Long courseId);

    SimpleResponse updateCourse(Long courseId, EditCourseRequest editCourse);

    SimpleResponse deleteCourseById(Long courseId);

    List<GetCourseResponse> softCourseByDate(String ascOrDesc);
}
