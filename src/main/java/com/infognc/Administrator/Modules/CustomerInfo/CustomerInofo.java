package com.infognc.Administrator.Modules.CustomerInfo;


import com.fasterxml.jackson.databind.ObjectReader;
import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor @Table(name = "customer_info")
public class CustomerInofo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String custNum;

    private String custName;

    private String cellNum;

    private String tellNum;

    private String custBirth;

    private String custSex;

    private String agreeDate;

    private String agreeType;
}
