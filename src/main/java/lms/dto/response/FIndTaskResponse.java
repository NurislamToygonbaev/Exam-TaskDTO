package lms.dto.response;

import java.time.LocalDate;

public record FIndTaskResponse(
        Long id,
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
