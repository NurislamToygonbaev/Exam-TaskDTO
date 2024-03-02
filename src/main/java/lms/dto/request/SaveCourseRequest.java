package lms.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SaveCourseRequest(
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
