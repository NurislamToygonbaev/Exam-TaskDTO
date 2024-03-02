package lms.dto.request;

import java.time.LocalDate;

public record SaveTaskRequest(
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
