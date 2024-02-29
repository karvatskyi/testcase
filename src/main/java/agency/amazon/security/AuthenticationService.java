package agency.amazon.security;

import agency.amazon.dto.EmployeeLoginRequestDto;
import agency.amazon.dto.EmployeeLoginResponseDto;
import agency.amazon.dto.EmployeeRegistrationRequestDto;
import agency.amazon.dto.EmployeeResponseDto;
import agency.amazon.mapper.EmployeeMapper;
import agency.amazon.model.Employee;
import agency.amazon.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;

    private final EmployeeMapper employeeMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    private final AuthenticationManager authenticationManager;

    public EmployeeLoginResponseDto authenticate(EmployeeLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getLogin(), requestDto.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        EmployeeLoginResponseDto employeeLoginResponseDto = new EmployeeLoginResponseDto();
        employeeLoginResponseDto.setToken(token);
        return employeeLoginResponseDto;
    }

    public EmployeeResponseDto register(EmployeeRegistrationRequestDto requestDto) {
        if (employeeRepository.findByLogin(requestDto.getLogin()).isPresent()) {
            throw new RuntimeException("Employee already registered");
        }
        Employee employee = new Employee();
        employee.setLogin(requestDto.getLogin());
        employee.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        employeeRepository.save(employee);
        return employeeMapper.toResponseFromDocument(employee);
    }
}
