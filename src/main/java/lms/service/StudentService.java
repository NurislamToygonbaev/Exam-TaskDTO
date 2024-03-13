package lms.service;

import lms.dto.request.EditStudentRequest;
import lms.dto.request.SaveStudentRequest;
import lms.dto.response.AllStudentsResponse;
import lms.dto.response.GetStudentResponse;
import lms.dto.response.SignResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    List<AllStudentsResponse> findAll();

    SignResponse saveStudent(SaveStudentRequest student);

    GetStudentResponse findById(Long studentId);

    SimpleResponse updateStudent(Long studentId, EditStudentRequest editStudentRequest);

    SimpleResponse deleteById(Long studentId);

    SimpleResponse assignStudentToGroup(Long groupId, Long studentId);

    List<AllStudentsResponse> sortByFormat(StudyFormat studyFormat);

    SimpleResponse blockStudent(Long studentId);
}
