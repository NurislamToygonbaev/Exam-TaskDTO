package lms.api;

import lms.dto.request.EditGroupRequest;
import lms.dto.request.SaveGroupRequest;
import lms.dto.response.FindAllGroups;
import lms.dto.response.SimpleResponse;
import lms.service.GroupService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupApi {
    private final GroupService groupService;

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/{companyId}")
    public List<FindAllGroups> findAllGroup(@PathVariable Long companyId){
        return groupService.findAll(companyId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/save")
    public SimpleResponse saveGroup(@RequestBody SaveGroupRequest saveGroupRequest){
        return groupService.saveGroup(saveGroupRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/findById/{groupId}")
    public FindAllGroups getById(@PathVariable Long groupId){
        return groupService.getById(groupId);
    }

    @Secured({"ADMIN"})
    @PutMapping("/update/{groupId}")
    public SimpleResponse updateGroup(@RequestBody EditGroupRequest editGroupRequest,
                                      @PathVariable Long groupId){
        return groupService.updateGroup(groupId, editGroupRequest);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/delete/{groupId}")
    public SimpleResponse deleteById(@PathVariable Long groupId){
        return groupService.deleteById(groupId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/count/{groupId}")
    public Integer countStudentsOfGroup(@PathVariable Long groupId){
        return groupService.countStudentsOfGroup(groupId);
    }
}
