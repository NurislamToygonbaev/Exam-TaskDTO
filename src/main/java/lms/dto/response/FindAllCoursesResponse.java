package lms.dto.response;

import java.time.LocalDate;

public record FindAllCoursesResponse(
        Long id,
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
