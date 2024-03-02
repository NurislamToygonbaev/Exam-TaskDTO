package lms.service.impl;

import jakarta.transaction.Transactional;
import lms.dto.request.SaveInstructorRequest;
import lms.dto.request.UpdateInstructorRequest;
import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.*;
import lms.repository.CompanyRepository;
import lms.repository.InstructorRepository;
import lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepo;
    private final CompanyRepository companyRepo;

    private Instructor checkId(Long inId){
        return instructorRepo.findById(inId)
                .orElseThrow(() ->
                        new NoSuchElementException("Instructor with id: "+inId+" not found"));
    }
    @Override
    public List<GetAllInstructorsResponse> findAll() {
        return instructorRepo.findAllInstructors();
    }

    @Override
    public SimpleResponse saveInstructor(SaveInstructorRequest saveInstructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setLastName(saveInstructorRequest.lastName());
        instructor.setFirstName(saveInstructorRequest.firstName());
        instructor.setPhoneNumber(saveInstructorRequest.phoneNumber());
        instructor.setSpecialization(saveInstructorRequest.specialization());
        instructorRepo.save(instructor);
        return new SimpleResponse(HttpStatus.OK, "Instructor Successfully saved");
    }

    @Override
    public GetAllInstructorsResponse findById(Long inId) {
        checkId(inId);
        return instructorRepo.findInstructorById(inId);
    }

    @Override
    public SimpleResponse updateInstructor(Long inId, UpdateInstructorRequest updateInstructor) {
        Instructor instructor = checkId(inId);
        instructor.setLastName(updateInstructor.lastName());
        instructor.setFirstName(updateInstructor.firstName());
        instructor.setPhoneNumber(updateInstructor.phoneNumber());
        instructor.setSpecialization(updateInstructor.specialization());
        instructorRepo.save(instructor);
        return new SimpleResponse(HttpStatus.OK, "successfully updated");
    }

    @Override
    public SimpleResponse deleteById(Long inId) {
        Instructor instructor = checkId(inId);
        instructorRepo.delete(instructor);
        return new SimpleResponse(HttpStatus.OK, "successfully deleted");
    }

    @Override @Transactional
    public SimpleResponse assignInstructorToCompany(Long companyId, Long instructorId) {
        Instructor instructor = checkId(instructorId);
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + " not found"));
        instructor.addCompany(company);
        company.addInstructor(instructor);
        return new SimpleResponse(HttpStatus.OK, "successfully assigned");
    }

    @Override
    public Integer countStudentsOfGroup(Long inId) {
        return instructorRepo.countStudentsOfGroup(inId);
    }

    @Override
    public InstructorInfosResponse instructorWIthInfos(Long inId) {
        Instructor instructor = checkId(inId);
        InstructorInfosResponse instructorInfosResponse = instructorRepo.instructorWIthInfos(inId);

        Map<String, Integer> groupNameWithStudents = instructorInfosResponse.getGroupNameWithStudent();

        List<Course> courses = instructor.getCourses();

        for (Course course : courses) {
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    groupNameWithStudents.put(group.getGroupName(), Math.toIntExact(student.getId()));
                }
            }
        }
        return instructorInfosResponse;
    }
}
