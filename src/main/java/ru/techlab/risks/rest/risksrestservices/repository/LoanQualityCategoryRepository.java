package ru.techlab.risks.rest.risksrestservices.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityCategory;

import java.util.stream.Stream;

/**
 * Created by rb052775 on 05.10.2017.
 */
@Repository
@Cacheable("loanQualityCategoryRepository")
public interface LoanQualityCategoryRepository extends CassandraRepository<LoanQualityCategory> {
}