package lms.dto.response;

import java.time.LocalDate;

public record ALlTasksResponse(
        Long id,
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
