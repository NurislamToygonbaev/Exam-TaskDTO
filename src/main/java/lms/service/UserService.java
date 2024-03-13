package lms.service;

import lms.dto.request.SignRequest;
import lms.dto.response.SignResponse;

public interface UserService {
    SignResponse signIn(SignRequest signRequest);
}
