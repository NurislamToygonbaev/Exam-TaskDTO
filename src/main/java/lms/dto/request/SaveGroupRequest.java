package lms.dto.request;

import java.util.List;

public record SaveGroupRequest(
        String groupName,
        String imageLink,
        String description,
        List<Long> courseIds
) {
}
