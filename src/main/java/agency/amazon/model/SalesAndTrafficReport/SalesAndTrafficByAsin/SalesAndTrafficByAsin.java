package agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficByAsin.SalesByAsin.SalesByAsin;
import lombok.Data;

import java.util.List;

@Data
public class SalesAndTrafficByAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}
