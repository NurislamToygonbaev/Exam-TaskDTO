package lms.dto.response;

public record FindAllGroups(
        Long id,
        String groupName,
        String imageLink,
        String description
) {
}
