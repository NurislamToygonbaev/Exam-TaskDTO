package lms.api;

import lms.dto.request.EditStudentRequest;
import lms.dto.request.SaveStudentRequest;
import lms.dto.response.AllStudentsResponse;
import lms.dto.response.GetStudentResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.enums.StudyFormat;
import lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentApi {
    private final StudentService service;

    @GetMapping
    public List<AllStudentsResponse> findAll(){
        return service.findAll();
    }

    @PostMapping("/save")
    public SimpleResponse saveStudent(@RequestBody SaveStudentRequest student){
        return service.saveStudent(student);
    }

    @GetMapping("/findById/{studentId}")
    public GetStudentResponse findById(@PathVariable Long studentId){
        return service.findById(studentId);
    }

    @PutMapping("/update/{studentId}")
    public SimpleResponse updateStudent(@PathVariable Long studentId,
                                        @RequestBody EditStudentRequest editStudentRequest){
        return service.updateStudent(studentId, editStudentRequest);
    }

    @DeleteMapping("/delete/{studentId}")
    public SimpleResponse deleteStudent(@PathVariable Long studentId){
        return service.deleteById(studentId);
    }

    @PostMapping("/assign/{groupId}/{studentId}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long groupId,
                                               @PathVariable Long studentId){
        return service.assignStudentToGroup(groupId, studentId);
    }

    @GetMapping("/sortByFormat")
    public List<AllStudentsResponse> sortByFormat(@RequestParam StudyFormat studyFormat){
        return service.sortByFormat(studyFormat);
    }

    @PostMapping("/block/{studentId}")
    public SimpleResponse blockStudent(@PathVariable Long studentId){
        return service.blockStudent(studentId);
    }
}
