package lms.dto.response;

import lms.entities.enums.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorInfosResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private Specialization specialization;
    private Map<String, Integer> groupNameWithStudent = new HashMap<>();

    public InstructorInfosResponse(Long id, String lastName, String firstName, String phoneNumber, Specialization specialization) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
