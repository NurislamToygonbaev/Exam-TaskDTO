package lms.repository;

import lms.dto.response.FindAllGroups;
import lms.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select new lms.dto.response.FindAllGroups(g.id, g.groupName, g.imageLink, g.description) from Group g join g.courses c where c.company.id =:companyId")
    List<FindAllGroups> findAllGroups(Long companyId);

    @Query("select new lms.dto.response.FindAllGroups(g.id, g.groupName, g.imageLink, g.description) from Group g where g.id = :groupId")
    FindAllGroups getGroupById(Long groupId);

    @Query("select count(s) from Group g join g.students s where g.id = :groupId")
    Integer countStudentsOfGroup(Long groupId);
}
