package lms.dto.request;

import java.time.LocalDate;

public record EditCourseRequest(
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
