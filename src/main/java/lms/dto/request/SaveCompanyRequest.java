package lms.dto.request;

import lombok.Builder;

@Builder
public record SaveCompanyRequest(
        String name,
        String country,
        String address,
        String phoneNumber
) {
}
