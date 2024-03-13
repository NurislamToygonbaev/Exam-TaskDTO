package lms.dto.request;

import lms.entities.enums.StudyFormat;

public record SaveStudentRequest(
        String lastName,
        String firstName,
        String phoneNumber,
        String email,
        String password,
        StudyFormat studyFormat
) {
}
