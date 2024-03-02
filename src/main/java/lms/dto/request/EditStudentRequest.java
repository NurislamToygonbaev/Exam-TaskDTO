package lms.dto.request;

import lms.entities.enums.StudyFormat;

public record EditStudentRequest(
        String lastName,
        String firstName,
        String phoneNumber,
        String email,
        StudyFormat studyFormat
) {
}
