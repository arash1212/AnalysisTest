package com.example.salehi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TB_CUSTOMER", indexes = {
        @Index(name = "CUSTOMER_IDX_PK", columnList = "ID")
})
public class CustomerEntity {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "customerSequence", sequenceName = "CUSTOMER_SEQUENCE",
            initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    private int id;
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", length = 500)
    private String description;
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;
    @Column(name = "FIRST_NAME", length = 100)
    private String firstName;
    @Column(name = "LAST_NAME", length = 100)
    private String lastName;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;
}
