package agency.amazon.dto;

import lombok.Data;

@Data
public class EmployeeRegistrationRequestDto {
    private String login;
    private String password;
    private String repeatPassword;
}
