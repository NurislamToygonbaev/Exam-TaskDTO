package lms.dto.response;

import lombok.Builder;

@Builder
public record SignResponse(
        String token,
        String email,
        SimpleResponse simpleResponse
) {
}
