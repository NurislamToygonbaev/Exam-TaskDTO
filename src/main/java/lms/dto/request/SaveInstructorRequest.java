package lms.dto.request;

import lms.entities.enums.Specialization;
import lombok.Builder;

@Builder
public record SaveInstructorRequest(
        String lastName,
        String firstName,
        String email,
        String password,
        String phoneNumber,
        Specialization specialization
) {
}
