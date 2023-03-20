package cinema.controller;

import cinema.dto.request.UserLoginRequestDto;
import cinema.dto.request.UserRegisterRequestDto;
import cinema.dto.response.UserResponseDto;
import cinema.model.User;
import cinema.security.jwt.JwtTokenProvider;
import cinema.service.AuthenticationService;
import cinema.service.mapper.ResponseDtoMapper;
import java.util.Map;
import java.util.stream.Collectors;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);
    private final AuthenticationService authService;
    private final ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authService,
                                    ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.userDtoResponseMapper = userDtoResponseMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegisterRequestDto requestDto) {
        logger.info("Register request was called. Email: " + requestDto.getEmail());
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword());
        return userDtoResponseMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequestDto requestDto)
            throws AuthenticationException {
        logger.info("Login request was called. Email: " + requestDto.getEmail());
        User login = authService.login(requestDto.getEmail(), requestDto.getPassword());
        String token = jwtTokenProvider.generateToken(login.getEmail(), login.getRoles().stream()
                .map(r -> r.getRoleName().name())
                .collect(Collectors.toList()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
