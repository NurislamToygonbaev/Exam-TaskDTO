package lms.dto.response;

import lombok.Builder;

@Builder
public record FindAllCompaniesResponse(
        Long id,
        String name,
        String country,
        String address,
        String phoneNumber
) {
}
