package lms.dto.response;

import java.time.LocalDate;

public record GetCourseResponse(
        Long id,
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
