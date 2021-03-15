package com.infognc.Administrator.Modules.Contract;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor @Table(name = "contract_list")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contType = "00";

    private String agentNum;

    private String custNum;

    private LocalDateTime contDate;
}
