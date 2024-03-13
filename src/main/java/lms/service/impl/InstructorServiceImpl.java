package lms.service.impl;

import jakarta.transaction.Transactional;
import lms.config.jwt.JwtService;
import lms.dto.request.SaveInstructorRequest;
import lms.dto.request.UpdateInstructorRequest;
import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.dto.response.SignResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.*;
import lms.entities.enums.Role;
import lms.repository.CompanyRepository;
import lms.repository.CourseRepository;
import lms.repository.InstructorRepository;
import lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepo;
    private final CompanyRepository companyRepo;
    private final CourseRepository courseRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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
    public SignResponse saveInstructor(SaveInstructorRequest saveInstructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setLastName(saveInstructorRequest.lastName());
        instructor.setFirstName(saveInstructorRequest.firstName());
        instructor.setPhoneNumber(saveInstructorRequest.phoneNumber());
        instructor.setSpecialization(saveInstructorRequest.specialization());

        User user = new User();
        user.setEmail(saveInstructorRequest.email());
        user.setPassword(passwordEncoder.encode(saveInstructorRequest.password()));
        user.setRole(Role.INSTRUCTOR);

        instructor.setUser(user);
        instructorRepo.save(instructor);

        return SignResponse.builder()
                .token(jwtService.createToken(user))
                .email(user.getEmail())
                .simpleResponse(SimpleResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Instructor successfully saved")
                        .build())
                .build();
    }

    @Override
    public GetAllInstructorsResponse findById(Long inId) {
        checkId(inId);
        return instructorRepo.findInstructorById(inId);
    }

    @Override
    public SimpleResponse updateInstructor(Long inId, UpdateInstructorRequest updateInstructor) {
        try {
            Instructor instructor = checkId(inId);
            instructor.setLastName(updateInstructor.lastName());
            instructor.setFirstName(updateInstructor.firstName());
            instructor.setPhoneNumber(updateInstructor.phoneNumber());
            instructor.setSpecialization(updateInstructor.specialization());
            instructorRepo.save(instructor);
            return new SimpleResponse(HttpStatus.OK, "successfully updated");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long inId) {
        try {
            Instructor instructor = checkId(inId);
            instructorRepo.delete(instructor);
            return new SimpleResponse(HttpStatus.OK, "successfully deleted");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override @Transactional
    public SimpleResponse assignInstructorToCompany(Long companyId, Long instructorId) {
        try {
            Instructor instructor = checkId(instructorId);
            Company company = companyRepo.findById(companyId)
                    .orElseThrow(() ->
                            new NoSuchElementException("Company with id: " + companyId + " not found"));
            instructor.addCompany(company);
            company.addInstructor(instructor);
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
                int count = 0;
                count += group.getStudents().size();
                groupNameWithStudents.put(group.getGroupName(), count);
            }
        }
        return instructorInfosResponse;
    }

    @Override @Transactional
    public SimpleResponse assignInToCourse(Long courseId, Long inId) {
        try {
            Instructor instructor = checkId(inId);
            Course course = courseRepo.findById(courseId)
                    .orElseThrow(() ->
                            new NoSuchElementException("Course with id: " + courseId + " not found"));

            List<Company> companies = instructor.getCompanies();
            for (Company company : companies) {
                if (company.getId().equals(course.getCompany().getId())){
                    instructor.addCourse(course);
                    course.setInstructor(instructor);
                    return new SimpleResponse(HttpStatus.OK,
                            "successfully assigned Instructor to course");
                }
            }
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }


        return new SimpleResponse(HttpStatus.OK,
                "failed assign Instructor to course");
    }
}
