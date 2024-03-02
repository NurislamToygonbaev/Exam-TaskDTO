package lms.dto.request;

import java.util.List;

public record EditGroupRequest(
        String groupName,
        String imageLink,
        String description,
        List<Long> courseIds
) {
}
