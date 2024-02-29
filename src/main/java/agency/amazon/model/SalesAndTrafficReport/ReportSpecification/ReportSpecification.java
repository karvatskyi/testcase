package agency.amazon.model.SalesAndTrafficReport.ReportSpecification;

import lombok.Data;

import java.util.List;

@Data
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
}
