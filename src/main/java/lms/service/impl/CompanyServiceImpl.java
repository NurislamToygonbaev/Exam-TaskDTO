package lms.service.impl;

import lms.dto.request.EditCompanyRequest;
import lms.dto.request.SaveCompanyRequest;
import lms.dto.response.CompanyWithInfo;
import lms.dto.response.FindAllCompaniesResponse;
import lms.dto.response.FindCompanyByIdResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.*;
import lms.exceptions.MyException;
import lms.repository.CompanyRepository;
import lms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepo;

    private void checkName(String name) throws MyException {
        boolean exists = companyRepo.existsByName(name);
        if (exists) throw new MyException("Company with name: " + name + " already exists");
    }

    private Company checkId(Long companyId) {
        return companyRepo.findById(companyId)
                .orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + " not found"));
    }

    @Override
    public List<FindAllCompaniesResponse> findAll() {
        return companyRepo.findAllCompanies();
    }

    @Override
    public SimpleResponse save(SaveCompanyRequest saveCompanyRequest) {
        try {
            checkName(saveCompanyRequest.name());
            Company company = new Company();
            company.setName(saveCompanyRequest.name());
            company.setCountry(saveCompanyRequest.country());
            company.setAddress(saveCompanyRequest.address());
            company.setPhoneNumber(saveCompanyRequest.phoneNumber());
            companyRepo.save(company);
            return new SimpleResponse(HttpStatus.OK, "Successfully saved");
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public FindCompanyByIdResponse findById(Long companyId) {
        checkId(companyId);
        return companyRepo.findCompanyById(companyId);
    }

    @Override
    public SimpleResponse updateCompany(Long companyId, EditCompanyRequest editCompany) {
        try {
            checkName(editCompany.name());
            Company company = checkId(companyId);
            company.setName(editCompany.name());
            company.setCountry(editCompany.country());
            company.setAddress(editCompany.address());
            company.setPhoneNumber(editCompany.phoneNumber());
            companyRepo.save(company);
            return new SimpleResponse(HttpStatus.OK, "Successfully updated");
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteCompanyById(Long companyId) {
        try {
            Company company = checkId(companyId);
            companyRepo.delete(company);
            return new SimpleResponse(HttpStatus.OK, "Successfully deleted");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CompanyWithInfo companyWithInfo(Long companyId) {
        Company company = checkId(companyId);

        CompanyWithInfo companyWithInfo = companyRepo.companyWithInfo(companyId);


        for (Instructor instructor : company.getInstructors()) {
            companyWithInfo.addInstructorsName(instructor.getFirstName());
        }
        int count = 0;
        for (Course course : company.getCourses()) {
            companyWithInfo.addCourseNames(course.getCourseName());
            for (Group group : course.getGroups()) {
                companyWithInfo.addGroupName(group.getGroupName());
                count += group.getStudents().size();
            }
        }
        companyWithInfo.setCountOfStudents(count);
        return companyWithInfo;
    }
}
