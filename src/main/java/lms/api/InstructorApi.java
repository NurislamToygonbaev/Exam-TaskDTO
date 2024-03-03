package lms.api;

import lms.dto.request.SaveInstructorRequest;
import lms.dto.request.UpdateInstructorRequest;
import lms.dto.response.GetAllInstructorsResponse;
import lms.dto.response.InstructorInfosResponse;
import lms.dto.response.SimpleResponse;
import lms.service.InstructorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorApi {
    private final InstructorService instructorService;
    @GetMapping
    public List<GetAllInstructorsResponse> findALl(){
        return instructorService.findAll();
    }
    @PostMapping("/save")
    public SimpleResponse saveInstructor(@RequestBody SaveInstructorRequest saveInstructorRequest){
        return instructorService.saveInstructor(saveInstructorRequest);
    }
    @GetMapping("/getInstructorById/{inId}")
    public GetAllInstructorsResponse findInstructorById(@PathVariable Long inId){
        return instructorService.findById(inId);
    }

    @PutMapping("/update/{inId}")
    public SimpleResponse updateInstructor(@PathVariable Long inId,
                                           @RequestBody UpdateInstructorRequest updateInstructor){
        return instructorService.updateInstructor(inId, updateInstructor);
    }

    @DeleteMapping("/delete/{inId}")
    public SimpleResponse deleteById(@PathVariable Long inId){
        return instructorService.deleteById(inId);
    }

    @PostMapping("/assign/{companyId}/{instructorId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long companyId,
                                                    @PathVariable Long instructorId){
        return instructorService.assignInstructorToCompany(companyId, instructorId);
    }

    @GetMapping("/count/{inId}")
    public Integer countStudentsOfGroup(@PathVariable Long inId){
        return instructorService.countStudentsOfGroup(inId);
    }

    @GetMapping("/InstructorWithInfos/{inId}")
    public InstructorInfosResponse instructorWithInfos(@PathVariable Long inId){
        return instructorService.instructorWIthInfos(inId);
    }

    @PostMapping("/assignToCourse/{courseId}/{inId}")
    public SimpleResponse assignIntoCourse(@PathVariable Long courseId,
                                           @PathVariable Long inId){
        return instructorService.assignInToCourse(courseId, inId);
    }
}
