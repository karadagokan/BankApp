package com.okankaradag.bankapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer creditId;
    private Double sum;
    private Float mountlyPayment;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToOne(mappedBy = "credit", cascade = {CascadeType.MERGE})
    private Customer customer;

    private Double paid = 0.0;

}
