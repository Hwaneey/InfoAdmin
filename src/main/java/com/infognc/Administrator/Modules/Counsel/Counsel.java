package com.infognc.Administrator.Modules.Counsel;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counsel {

    @Id @GeneratedValue
    private Long id;

    private int idxCounlog;

    private String agentNum;

    private String recNum;

    private String custNum;

    private String idxCounDB;

    private String custName;

    private String counStep = "00";

    private String counMemo;

    private int idxContlog;

    private String contType = "00";

    private LocalDateTime startTime;

    private int ringDuration = 0;
}
