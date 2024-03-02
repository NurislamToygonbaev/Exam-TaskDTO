package lms.dto.response;

import lms.entities.enums.StudyFormat;

public record AllStudentsResponse(
        Long id,
        String lastName,
        String firstName,
        String phoneNumber,
        String email,
        StudyFormat studyFormat
) {
}
