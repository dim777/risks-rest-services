package ru.techlab.risks.rest.risksrestservices.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityResult;

import java.util.stream.Stream;

/**
 * Created by dim777 on 05.10.17.
 */
@Repository
public interface LoanQualityResultRepository extends CassandraRepository<LoanQualityResult> {
    @Query("SELECT * FROM SRRU WHERE RRABD = ?0 AND RRAND = ?1 AND RRASD = ?2")
    Stream<LoanQualityResult> findSimpleDelayByLoan(String branch, String loanAccountNumber, String loanAccountSuffix);
}
