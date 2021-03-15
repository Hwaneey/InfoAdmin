package com.infognc.Administrator.Modules.Customer;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor @Table(name = "customer_db")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idxCounDB;

    private String agentNum;

    private String custNum;

    private String counStep ="00";

    private LocalDateTime regDate;

    private LocalDateTime alocDate;

    private String delFlag = "N";

}
