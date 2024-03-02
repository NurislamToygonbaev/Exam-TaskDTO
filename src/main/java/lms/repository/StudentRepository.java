package lms.repository;

import lms.dto.response.AllStudentsResponse;
import lms.dto.response.GetStudentResponse;
import lms.entities.Student;
import lms.entities.enums.StudyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select new lms.dto.response.AllStudentsResponse(s.id, s.lastName, s.firstName, s.email, s.phoneNumber, s.studyFormat) from Student s")
    List<AllStudentsResponse> findStudents();

    boolean existsByEmail(String email);

    @Query("select new lms.dto.response.GetStudentResponse(s.id, s.lastName, s.firstName, s.email, s.phoneNumber, s.studyFormat) from Student s where s.id =:studentId")
    GetStudentResponse findStudentById(Long studentId);

    @Query("select new lms.dto.response.AllStudentsResponse(s.id, s.lastName, s.firstName, s.email, s.phoneNumber, s.studyFormat) from Student s where s.studyFormat =:studyFormat")
    List<AllStudentsResponse> sortByFormat(StudyFormat studyFormat);
}
