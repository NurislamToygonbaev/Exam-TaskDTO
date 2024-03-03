package lms.service;

import lms.dto.request.SaveInstructorRequest;
import lms.dto.request.UpdateInstructorRequest;
import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.dto.response.SimpleResponse;

import java.util.List;

public interface InstructorService {
    List<GetAllInstructorsResponse> findAll();

    SimpleResponse saveInstructor(SaveInstructorRequest saveInstructorRequest);

    GetAllInstructorsResponse findById(Long inId);

    SimpleResponse updateInstructor(Long inId, UpdateInstructorRequest updateInstructor);

    SimpleResponse deleteById(Long inId);

    SimpleResponse assignInstructorToCompany(Long companyId, Long instructorId);

    Integer countStudentsOfGroup(Long inId);

    InstructorInfosResponse instructorWIthInfos(Long inId);

    SimpleResponse assignInToCourse(Long courseId, Long inId);
}
