package lms.repository;

import lms.dto.response.FindAllCoursesResponse;
import lms.dto.response.GetCourseResponse;
import lms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new lms.dto.response.FindAllCoursesResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c")
    List<FindAllCoursesResponse> findAllCourses();

    @Query("select new lms.dto.response.GetCourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.id = :courseId")
    GetCourseResponse findCourseById(Long courseId);

    @Query("select c from Course c where c.id in (:courseIds)")
    List<Course> findCoursesWithIds(List<Long> courseIds);

    @Query("select new lms.dto.response.GetCourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c order by c.dateOfStart")
    List<GetCourseResponse> sortCourseByDate();

    @Query("select new lms.dto.response.GetCourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c order by c.dateOfStart desc")
    List<GetCourseResponse> sortCourseByDateDesc();
}
