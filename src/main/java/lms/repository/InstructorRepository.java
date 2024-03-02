package lms.repository;

import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select new lms.dto.response.GetAllInstructorsResponse(i.id, i.lastName, i.firstName, i.phoneNumber, i.specialization) from Instructor i")
    List<GetAllInstructorsResponse> findAllInstructors();

    @Query("select new lms.dto.response.GetAllInstructorsResponse(i.id, i.lastName, i.firstName, i.phoneNumber, i.specialization) from Instructor i where i.id =:inId")
    GetAllInstructorsResponse findInstructorById(Long inId);

    @Query("select count(s) from Instructor i join i.courses c join c.groups g join g.students s where i.id =:inId")
    Integer countStudentsOfGroup(Long inId);

    @Query("select new lms.dto.response.InstructorInfosResponse(i.id, i.lastName, i.firstName, i.phoneNumber, i.specialization) from Instructor i where i.id =:inId")
    InstructorInfosResponse instructorWIthInfos(Long inId);
}
