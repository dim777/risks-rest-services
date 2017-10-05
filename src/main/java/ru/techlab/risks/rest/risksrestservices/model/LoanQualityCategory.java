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
@Table("loanqualitycategory")
@Data
public class LoanQualityCategory implements Serializable {
    private static final long serialVersionUID = 3375159358757648792L;

    @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Integer id;

    @Column("type")
    private String type;

    @Column("pmin")
    private Double pMin;

}
