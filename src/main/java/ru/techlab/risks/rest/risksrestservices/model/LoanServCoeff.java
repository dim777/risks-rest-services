package ru.techlab.risks.rest.risksrestservices.model;

import lombok.Data;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;

/**
 * Created by rb052775 on 05.10.2017.
 */
@Table("loanservcoeff")
@Data
public class LoanServCoeff implements Serializable {
    private static final long serialVersionUID = 3375159358757648792L;

    @PrimaryKeyColumn(name = "type", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String type;

    @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Integer id;

    @Column("isle")
    private Boolean isLegalEntitity;

    @Column("forlastndays")
    private Integer forLastNDays;

    @Column("moreoreqthandays")
    private Integer moreOrEqThanDays;

    @Column("lessthandays")
    private Integer lessThanDays;
}
