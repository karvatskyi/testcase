package agency.amazon.controller;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin.SalesAndTrafficByAsin;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate.SalesAndTrafficByDate;
import agency.amazon.service.SalesAndTrafficReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final SalesAndTrafficReportService salesAndTrafficReportService;

    @GetMapping
    public Page<SalesAndTrafficByDate> getReportsByDates(@RequestParam("fromDate") String fromDate,
                                                         @RequestParam("toDate") String toDate,
                                                         Pageable pageable) {
        return salesAndTrafficReportService.getReportsByDates(fromDate, toDate, pageable);
    }

    @GetMapping("/date/{date}")
    public SalesAndTrafficByDate getReportsByDate(@PathVariable String date) {
        return salesAndTrafficReportService.getReportByDate(date);
    }

    @GetMapping("/asins")
    public Page<SalesAndTrafficByAsin> getReportsByAsins(@RequestBody List<String> asins,
                                                         Pageable pageable) {
        return salesAndTrafficReportService.getReportsByAsins(asins, pageable);
    }

    @GetMapping("/asins/statistic")
    public Page<SalesAndTrafficByAsin> getAsinsStatistic(Pageable pageable) {
        return salesAndTrafficReportService.getAsinsStatistic(pageable);
    }

    @GetMapping("/dates/statistic")
    public Page<SalesAndTrafficByDate> getDatesStatistic(Pageable pageable) {
        return salesAndTrafficReportService.getDatesStatistic(pageable);
    }
}
