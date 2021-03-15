package com.infognc.Administrator.Modules.Call;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor @Table(name = "call_list")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentNum;

    private String recNum;

    private String callType= "0";

    private String callNum;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int duration = 0;

    private int ringDuration = 0;

    private String outCallNum;

    private String inCallNum;

    private String recYn="N";
}
