package lms.service.impl;

import jakarta.transaction.Transactional;
import lms.dto.request.EditStudentRequest;
import lms.dto.request.SaveStudentRequest;
import lms.dto.response.AllStudentsResponse;
import lms.dto.response.GetStudentResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.Group;
import lms.entities.Student;
import lms.entities.enums.StudyFormat;
import lms.exceptions.MyException;
import lms.repository.GroupRepository;
import lms.repository.StudentRepository;
import lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepo;
    private final GroupRepository groupRepo;

    private void checkEmail(String email) throws MyException {
        boolean exists = studentRepo.existsByEmail(email);
        if (exists) throw new MyException("student with email: " + email + " already exists");
    }

    private Student checkId(Long studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new NoSuchElementException("student with id: " + studentId + " not found"));
    }

    @Override
    public List<AllStudentsResponse> findAll() {
        return studentRepo.findStudents();
    }

    @Override
    public SimpleResponse saveStudent(SaveStudentRequest saveStudentRequest) {
        try {
            checkEmail(saveStudentRequest.email());
            Student student = new Student();
            student.setLastName(saveStudentRequest.lastName());
            student.setFirstName(saveStudentRequest.firstName());
            student.setEmail(saveStudentRequest.email());
            student.setPhoneNumber(saveStudentRequest.phoneNumber());
            student.setStudyFormat(saveStudentRequest.studyFormat());
            studentRepo.save(student);
            return new SimpleResponse(HttpStatus.OK, "successfully saved");
        } catch (MyException e) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("error")
                    .build();
        }
    }

    @Override
    public GetStudentResponse findById(Long studentId) {
        checkId(studentId);
        return studentRepo.findStudentById(studentId);
    }

    @Override
    public SimpleResponse updateStudent(Long studentId, EditStudentRequest editStudentRequest) {
        try {
            checkEmail(editStudentRequest.email());
            Student student = checkId(studentId);
            student.setFirstName(editStudentRequest.firstName());
            student.setEmail(editStudentRequest.email());
            student.setPhoneNumber(editStudentRequest.phoneNumber());
            student.setStudyFormat(editStudentRequest.studyFormat());
            student.setLastName(editStudentRequest.lastName());
            studentRepo.save(student);
            return new SimpleResponse(HttpStatus.OK, "successfully saved");
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("error")
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long studentId) {
        try {
            Student student = checkId(studentId);
            studentRepo.delete(student);
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
    @Transactional
    public SimpleResponse assignStudentToGroup(Long groupId, Long studentId) {
        try {
            Student student = checkId(studentId);
            Group group = groupRepo.findById(groupId)
                    .orElseThrow(() ->
                            new NoSuchElementException("Group with id: " + groupId + " not found"));
            student.setGroup(group);
            group.addStudent(student);
            return new SimpleResponse(HttpStatus.OK, "successfully assigned");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<AllStudentsResponse> sortByFormat(StudyFormat studyFormat) {
        return studentRepo.sortByFormat(studyFormat);
    }

    @Override @Transactional
    public SimpleResponse blockStudent(Long studentId) {
        try {
            Student student = checkId(studentId);
            boolean block = student.isBlock();
            if (block){
                student.setBlock(false);
                return new SimpleResponse(HttpStatus.OK, "successfully blocked");
            }else {
                student.setBlock(true);
                return new SimpleResponse(HttpStatus.OK, "successfully unBlocked");
            }
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }
}
