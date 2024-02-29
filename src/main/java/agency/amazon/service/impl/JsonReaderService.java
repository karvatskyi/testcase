package agency.amazon.service.impl;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficReport;
import agency.amazon.service.ReaderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class JsonReaderService extends ReaderService {
    public SalesAndTrafficReport readJsonFile(String addressFile) {
        SalesAndTrafficReport report = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            report = objectMapper.readValue(new File(addressFile), SalesAndTrafficReport.class);
        } catch (IOException e) {
            throw new RuntimeException("Can't read json file");
        }
        return report;
    }
}
