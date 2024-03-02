package lms.dto.request;

import java.time.LocalDate;

public record EditTaskRequest(
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
