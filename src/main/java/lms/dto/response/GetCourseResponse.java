package lms.dto.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record GetCourseResponse(
        Long id,
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
