package lms.dto.response;

public record FindCompanyByIdResponse(
        Long id,
        String name,
        String country,
        String address,
        String phoneNumber
) {
}
