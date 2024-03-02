package lms.repository;

import lms.dto.response.CompanyWithInfo;
import lms.dto.response.FindAllCompaniesResponse;
import lms.dto.response.FindCompanyByIdResponse;
import lms.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new lms.dto.response.FindAllCompaniesResponse(c.id, c.name, c.country, c.address, c.phoneNumber) from Company c")
    List<FindAllCompaniesResponse> findAllCompanies();

    boolean existsByName(String name);

    @Query("select new lms.dto.response.FindCompanyByIdResponse(c.id, c.name, c.country, c.address, c.phoneNumber) from Company c where c.id =:companyId")
    FindCompanyByIdResponse findCompanyById(Long companyId);

    @Query("select new lms.dto.response.CompanyWithInfo(c.id, c.name, c.country, c.address, c.phoneNumber) from Company c where c.id =:companyId")
    CompanyWithInfo companyWithInfo(Long companyId);
}
