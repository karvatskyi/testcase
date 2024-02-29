package agency.amazon.service;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficReport;
import agency.amazon.service.impl.JsonReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Scheduler {
    private final SalesAndTrafficReportService reportService;

    private final JsonReaderService jsonReaderService;

    @Scheduled(fixedRateString = "${update.interval}")
    public void updateData() {
        String addressFile = "src/main/resources/test_report.json";
        SalesAndTrafficReport report = jsonReaderService.readJsonFile(addressFile);
        reportService.updateDatabase(report);
        System.out.println("Database was updated by file");
    }
}
