package agency.amazon.dto;

import lombok.Data;

@Data
public class EmployeeLoginRequestDto {
    private String login;
    private String password;
}
