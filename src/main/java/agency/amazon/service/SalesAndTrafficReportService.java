package agency.amazon.service;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin.SalesAndTrafficByAsin;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate.SalesAndTrafficByDate;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface SalesAndTrafficReportService {
    List<SalesAndTrafficReport> getReports();
    Page<SalesAndTrafficByDate> getReportsByDates(String fromDate, String toDate, Pageable pageable);
    SalesAndTrafficByDate getReportByDate(String Date);
    Page<SalesAndTrafficByAsin> getReportsByAsins(List<String> asins, Pageable pageable);
    Page<SalesAndTrafficByDate> getDatesStatistic(Pageable pageable);
    Page<SalesAndTrafficByAsin> getAsinsStatistic(Pageable pageable);
    void updateDatabase(SalesAndTrafficReport report);
}
