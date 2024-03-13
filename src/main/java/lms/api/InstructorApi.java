package lms.api;

import lms.dto.request.SaveInstructorRequest;
import lms.dto.request.UpdateInstructorRequest;
import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.dto.response.SignResponse;
import lms.dto.response.SimpleResponse;
import lms.service.InstructorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorApi {
    private final InstructorService instructorService;
    @Secured({"ADMIN"})
    @GetMapping
    public List<GetAllInstructorsResponse> findALl(){
        return instructorService.findAll();
    }
    @Secured({"ADMIN"})
    @PostMapping("/save")
    public SignResponse saveInstructor(@RequestBody SaveInstructorRequest saveInstructorRequest){
        return instructorService.saveInstructor(saveInstructorRequest);
    }
    @Secured({"ADMIN"})
    @GetMapping("/getInstructorById/{inId}")
    public GetAllInstructorsResponse findInstructorById(@PathVariable Long inId){
        return instructorService.findById(inId);
    }

    @Secured({"ADMIN"})
    @PutMapping("/update/{inId}")
    public SimpleResponse updateInstructor(@PathVariable Long inId,
                                           @RequestBody UpdateInstructorRequest updateInstructor){
        return instructorService.updateInstructor(inId, updateInstructor);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/delete/{inId}")
    public SimpleResponse deleteById(@PathVariable Long inId){
        return instructorService.deleteById(inId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/assign/{companyId}/{instructorId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long companyId,
                                                    @PathVariable Long instructorId){
        return instructorService.assignInstructorToCompany(companyId, instructorId);
    }
    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/count/{inId}")
    public Integer countStudentsOfGroup(@PathVariable Long inId){
        return instructorService.countStudentsOfGroup(inId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/InstructorWithInfos/{inId}")
    public InstructorInfosResponse instructorWithInfos(@PathVariable Long inId){
        return instructorService.instructorWIthInfos(inId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/assignToCourse/{courseId}/{inId}")
    public SimpleResponse assignIntoCourse(@PathVariable Long courseId,
                                           @PathVariable Long inId){
        return instructorService.assignInToCourse(courseId, inId);
    }
}
