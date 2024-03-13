package lms.api;

import lms.dto.request.SignRequest;
import lms.dto.response.SignResponse;
import lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApi {
    private final UserService userService;
    @PostMapping
    public SignResponse signIn(@RequestBody SignRequest signRequest){
        return userService.signIn(signRequest);
    }
}
