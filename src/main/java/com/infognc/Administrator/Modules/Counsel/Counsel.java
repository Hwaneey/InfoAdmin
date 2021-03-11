package com.infognc.Administrator.Modules.Counsel;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor @Table(name = "Counsel_list")
public class Counsel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idxCounDB;

    private String agentNum;

    private String recNum;

    private String custNum;

    private String custName;

    private String counStep = "00";

    private String counMemo;

    private int idxContlog;

    private String contType = "00";

    private LocalDateTime startTime;
}
