package agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByDate.SalesByDate.SalesByDate;
import lombok.Data;

@Data
public class SalesAndTrafficByDate {
    private String date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
