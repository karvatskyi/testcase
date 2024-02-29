package agency.amazon.repository;

import agency.amazon.model.SalesAndTrafficReport.SalesAndTrafficReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAndTrafficReportRepository extends MongoRepository<SalesAndTrafficReport, String> {
}
