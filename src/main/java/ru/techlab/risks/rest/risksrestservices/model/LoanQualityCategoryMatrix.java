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
@Data
@Table("loanqualitycategorymatrix")
public class LoanQualityCategoryMatrix implements Serializable {
    private static final long serialVersionUID = 3375159358757648792L;

    @PrimaryKeyColumn(name = "kod", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Integer loanServCoeffId;

    @Column("fs1")
    private Integer finState1;
    @Column("fs2")
    private Integer finState2;
    @Column("fs3")
    private Integer finState3;
}
