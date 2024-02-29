package agency.amazon.mapper;

import agency.amazon.config.MapperConfig;
import agency.amazon.dto.EmployeeResponseDto;
import agency.amazon.model.Employee;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface EmployeeMapper {
    EmployeeResponseDto toResponseFromDocument(Employee employee);
}
