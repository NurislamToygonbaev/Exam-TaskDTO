package lms.api;

import lms.dto.request.EditCompanyRequest;
import lms.dto.request.SaveCompanyRequest;
import lms.dto.response.CompanyWithInfo;
import lms.dto.response.FindAllCompaniesResponse;
import lms.dto.response.FindCompanyByIdResponse;
import lms.dto.response.SimpleResponse;
import lms.exceptions.MyException;
import lms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/company")
public class CompanyApi {
    private final CompanyService companyService;
    @Secured("ADMIN")
    @GetMapping
    public List<FindAllCompaniesResponse> findAll(){
        return companyService.findAll();
    }

    @Secured("ADMIN")
    @PostMapping("/save")
    public SimpleResponse saveCompany(@RequestBody SaveCompanyRequest saveCompanyRequest) {
       return companyService.save(saveCompanyRequest);
    }

    @Secured("ADMIN")
    @GetMapping("/findById/{companyId}")
    public FindCompanyByIdResponse findCompanyById(@PathVariable Long companyId){
        return companyService.findById(companyId);
    }

    @Secured("ADMIN")
    @PutMapping("/update/{companyId}")
    public SimpleResponse updateCompany(@RequestBody EditCompanyRequest editCompany,
                                        @PathVariable Long companyId) {
        return companyService.updateCompany(companyId, editCompany);
    }
    @Secured("ADMIN")
    @DeleteMapping("/delete/{companyId}")
    public SimpleResponse deleteCompanyById(@PathVariable Long companyId){
        return companyService.deleteCompanyById(companyId);
    }

    @Secured({"ADMIN", "INSTRUCTOR", "STUDENT"})
    @GetMapping("/CompanyWithInfo/{companyId}")
    public CompanyWithInfo companyWithInfo(@PathVariable Long companyId){
        return companyService.companyWithInfo(companyId);
    }
}
