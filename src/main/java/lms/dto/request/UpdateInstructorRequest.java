package lms.dto.request;

import lms.entities.enums.Specialization;

public record UpdateInstructorRequest(
        String lastName,
        String firstName,
        String phoneNumber,
        Specialization specialization
) {
}
