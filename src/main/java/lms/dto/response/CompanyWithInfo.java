package lms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class CompanyWithInfo {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private List<String> coursesName;
    private List<String> groupsName;
    private List<String> instructorsName;
    private Integer countOfStudents ;

    public CompanyWithInfo(Long id, String name, String country, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void addCourseNames(String courseName){
        if (this.coursesName == null) this.coursesName = new ArrayList<>();
        this.coursesName.add(courseName);
    }
    public void addGroupName(String groupName){
        if (this.groupsName == null) this.groupsName = new ArrayList<>();
        this.groupsName.add(groupName);
    }public void addInstructorsName(String instructorsName){
        if (this.instructorsName == null) this.instructorsName = new ArrayList<>();
        this.instructorsName.add(instructorsName);
    }

}
