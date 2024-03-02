package lms.dto.request;

import lms.entities.enums.Specialization;
import lombok.Builder;

@Builder
public record SaveInstructorRequest(
        String lastName,
        String firstName,
        String phoneNumber,
        Specialization specialization
) {
}
