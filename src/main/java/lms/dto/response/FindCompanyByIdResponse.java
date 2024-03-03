package lms.dto.response;

import lombok.Builder;

@Builder
public record FindCompanyByIdResponse(
        Long id,
        String name,
        String country,
        String address,
        String phoneNumber
) {
}
