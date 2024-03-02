package lms.dto.response;

import lms.entities.enums.Specialization;
import lombok.Builder;

@Builder
public record GetAllInstructorsResponse(
        Long id,
        String lastName,
        String firstName,
        String phoneNumber,
        Specialization specialization
) {
}
