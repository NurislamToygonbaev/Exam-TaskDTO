package lms.service;

import lms.dto.request.EditGroupRequest;
import lms.dto.request.SaveGroupRequest;
import lms.dto.response.FindAllGroups;
import lms.dto.response.SimpleResponse;

import java.util.List;

public interface GroupService {
    List<FindAllGroups> findAll();

    SimpleResponse saveGroup(SaveGroupRequest saveGroupRequest);

    FindAllGroups getById(Long groupId);

    SimpleResponse updateGroup(Long groupId, EditGroupRequest editGroupRequest);

    SimpleResponse deleteById(Long groupId);

    Integer countStudentsOfGroup(Long groupId);

}
