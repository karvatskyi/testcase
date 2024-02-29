package agency.amazon.service.impl;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin.SalesAndTrafficByAsin;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate.SalesAndTrafficByDate;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficReport;
import agency.amazon.repository.SalesAndTrafficReportRepository;
import agency.amazon.service.SalesAndTrafficReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficReportServiceImpl implements SalesAndTrafficReportService {

    private final SalesAndTrafficReportRepository salesAndTrafficReportRepository;

    @Override
    @Cacheable("reportCache")
    public List<SalesAndTrafficReport> getReports() {
        return salesAndTrafficReportRepository.findAll();
    }

    @CachePut("reportCache")
    public void updateReports() {
        salesAndTrafficReportRepository.findAll();
    }

    @Override
    public Page<SalesAndTrafficByDate> getReportsByDates(String fromDate, String toDate, Pageable pageable) {
        List<SalesAndTrafficByDate> result = new ArrayList<>();
        List<SalesAndTrafficReport> reports = getReports();
        int length = reports.get(0).getSalesAndTrafficByDate().size();
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        for (int i = 0; i < length; i++) {
            SalesAndTrafficByDate salesAndTrafficByDate = reports.get(0).getSalesAndTrafficByDate().get(i);
            LocalDate currentDate = LocalDate.parse(salesAndTrafficByDate.getDate());
            if (currentDate.isEqual(from) || (currentDate.isAfter(from)
                    && currentDate.isBefore(to)) || currentDate.isEqual(to)) {
                result.add(salesAndTrafficByDate);
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), result.size());
        return new PageImpl<>(result.subList(start, end), pageable, result.size());
    }

    @Override
    public SalesAndTrafficByDate getReportByDate(String date) {
        List<SalesAndTrafficReport> reports = getReports();
        int length = reports.get(0).getSalesAndTrafficByDate().size();
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = reports.get(0).getSalesAndTrafficByDate();
        for (int i = 0; i < length; i++) {
            SalesAndTrafficByDate salesAndTrafficByDate = salesAndTrafficByDateList.get(i);
            if (salesAndTrafficByDate.getDate().equals(date)) {
                return salesAndTrafficByDate;
            }
        }
        return null;
    }

    @Override
    public Page<SalesAndTrafficByAsin> getReportsByAsins(List<String> asin, Pageable pageable) {
        List<SalesAndTrafficByAsin> result = new ArrayList<>();
        List<SalesAndTrafficReport> reports = getReports();
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = reports.get(0).getSalesAndTrafficByAsin();
        for (SalesAndTrafficByAsin salesAndTrafficByAsin : salesAndTrafficByAsinList) {
            if (asin.contains(salesAndTrafficByAsin.getParentAsin())) {
                result.add(salesAndTrafficByAsin);
            }
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SalesAndTrafficByAsin> pageList;
        if (result.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, result.size());
            pageList = result.subList(startItem, toIndex);
        }
        return new PageImpl<>(pageList, PageRequest.of(currentPage, pageSize), result.size());
    }

    @Override
    public Page<SalesAndTrafficByDate> getDatesStatistic(Pageable pageable) {
        List<SalesAndTrafficReport> reports = getReports();
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = reports.get(0).getSalesAndTrafficByDate();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SalesAndTrafficByDate> pageContent;
        if (salesAndTrafficByDateList.size() < startItem) {
            pageContent = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, salesAndTrafficByDateList.size());
            pageContent = salesAndTrafficByDateList.subList(startItem, toIndex);
        }
        return new PageImpl<>(pageContent, PageRequest.of(currentPage, pageSize), salesAndTrafficByDateList.size());

    }

    @Override
    public Page<SalesAndTrafficByAsin> getAsinsStatistic(Pageable pageable) {
        List<SalesAndTrafficReport> reports = getReports();
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = reports.get(0).getSalesAndTrafficByAsin();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<SalesAndTrafficByAsin> pageContent;
        if (salesAndTrafficByAsinList.size() < startItem) {
            pageContent = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, salesAndTrafficByAsinList.size());
            pageContent = salesAndTrafficByAsinList.subList(startItem, toIndex);
        }
        return new PageImpl<>(pageContent, PageRequest.of(currentPage, pageSize), salesAndTrafficByAsinList.size());
    }

    @Override
    public void updateDatabase(SalesAndTrafficReport report) {
        SalesAndTrafficReport reportFromDb = salesAndTrafficReportRepository.findAll().get(0);
        if (!reportFromDb.getReportSpecification().equals(report.getReportSpecification())
                || !reportFromDb.getSalesAndTrafficByAsin().equals(report.getSalesAndTrafficByAsin())
                || !reportFromDb.getSalesAndTrafficByDate().equals(report.getSalesAndTrafficByDate())
        ) {
            salesAndTrafficReportRepository.deleteAll();
            salesAndTrafficReportRepository.save(report);
            updateReports();
        }
    }
}
