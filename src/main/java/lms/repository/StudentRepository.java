package lms.repository;

import lms.dto.response.AllStudentsResponse;
import lms.dto.response.GetStudentResponse;
import lms.entities.Student;
import lms.entities.User;
import lms.entities.enums.StudyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select new lms.dto.response.AllStudentsResponse(s.id, s.lastName, s.firstName, u.email, s.phoneNumber, s.studyFormat) from Student s join s.user u")
    List<AllStudentsResponse> findStudents();

    @Query("select new lms.dto.response.GetStudentResponse(s.id, s.lastName, s.firstName, u.email, s.phoneNumber, s.studyFormat) from Student s join s.user u where s.id =:studentId")
    GetStudentResponse findStudentById(Long studentId);

    @Query("select new lms.dto.response.AllStudentsResponse(s.id, s.lastName, s.firstName, u.email, s.phoneNumber, s.studyFormat) from Student s join s.user u where s.studyFormat =:studyFormat")
    List<AllStudentsResponse> sortByFormat(StudyFormat studyFormat);

}
