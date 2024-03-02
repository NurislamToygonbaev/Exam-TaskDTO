package lms.service;

import lms.dto.request.EditCompanyRequest;
import lms.dto.request.SaveCompanyRequest;
import lms.dto.response.CompanyWithInfo;
import lms.dto.response.FindAllCompaniesResponse;
import lms.dto.response.FindCompanyByIdResponse;
import lms.dto.response.SimpleResponse;
import lms.exceptions.MyException;

import java.util.List;

public interface CompanyService {
    List<FindAllCompaniesResponse> findAll();

    SimpleResponse save(SaveCompanyRequest saveCompanyRequest);

    FindCompanyByIdResponse findById(Long companyId);

    SimpleResponse updateCompany(Long companyId, EditCompanyRequest editCompany);

    SimpleResponse deleteCompanyById(Long companyId);

    CompanyWithInfo companyWithInfo(Long companyId);
}
