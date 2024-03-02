package lms.dto.request;

public record EditCompanyRequest(
        String name,
        String country,
        String address,
        String phoneNumber
) {
}
