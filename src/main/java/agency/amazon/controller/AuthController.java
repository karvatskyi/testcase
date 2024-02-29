package agency.amazon.controller;

import agency.amazon.dto.EmployeeLoginRequestDto;
import agency.amazon.dto.EmployeeLoginResponseDto;
import agency.amazon.dto.EmployeeRegistrationRequestDto;
import agency.amazon.dto.EmployeeResponseDto;
import agency.amazon.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public EmployeeResponseDto register(@RequestBody EmployeeRegistrationRequestDto requestDto) {
        return authenticationService.register(requestDto);
    }

    @PostMapping("/login")
    public EmployeeLoginResponseDto login(@RequestBody EmployeeLoginRequestDto loginRequestDto) {
        return authenticationService.authenticate(loginRequestDto);
    }
}
