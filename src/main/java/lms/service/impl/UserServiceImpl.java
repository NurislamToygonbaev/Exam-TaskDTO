package lms.service.impl;

import jakarta.annotation.PostConstruct;
import lms.config.jwt.JwtService;
import lms.dto.request.SignRequest;
import lms.dto.response.SignResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.Instructor;
import lms.entities.Student;
import lms.entities.User;
import lms.entities.enums.Role;
import lms.repository.InstructorRepository;
import lms.repository.StudentRepository;
import lms.repository.UserRepository;
import lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

//    @PostConstruct
//    private void initMethod() {
//        userRepo.save(
//                User.builder()
//                        .email("admin@gmail.com")
//                        .password(passwordEncoder.encode("admin"))
//                        .role(Role.ADMIN)
//                        .build()
//        );
//    }

    @Override
    public SignResponse signIn(SignRequest signRequest) {
        User user = userRepo.getByEmail(signRequest.email());
        boolean matches = passwordEncoder.matches(signRequest.password(), user.getPassword());
        if (!matches) throw new RuntimeException("Invalid password");
        return SignResponse.builder()
                .token(jwtService.createToken(user))
                .email(user.getEmail())
                .simpleResponse(SimpleResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("success")
                        .build())
                .build();
    }
}
