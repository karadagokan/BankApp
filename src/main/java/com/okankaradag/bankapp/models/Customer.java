package com.okankaradag.bankapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
public class Customer implements IEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerFirstName;
    private String customerLastName;
    private Double salary;

    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Account account;

    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "creditId")
    private Credit credit;

    @Column(updatable = false)
    private String username;
    @Column(updatable = false)
    private String password;

    //0 Default - 1 Send Request - 2 Approve Request
    @Column(length = 1)
    private Integer accountRequest = 0;

    @Column(length = 1)
    private Integer creditRequest = 0;

    private Double creditRequestAmount;


}
