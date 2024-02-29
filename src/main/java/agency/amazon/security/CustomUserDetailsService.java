package agency.amazon.security;

import agency.amazon.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return employeeRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find employee by email"));
    }
}
