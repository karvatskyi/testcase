package agency.amazon.model.SalesAndTrafficReport;

import agency.amazon.model.SalesAndTrafficReport.ReportSpecification.ReportSpecification;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin.SalesAndTrafficByAsin;
import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate.SalesAndTrafficByDate;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "salesAndTrafficReports")
public class SalesAndTrafficReport {

    public SalesAndTrafficReport() {
        this.salesAndTrafficByDate = new ArrayList<>();
        this.salesAndTrafficByAsin = new ArrayList<>();
    }

    private String id;
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
