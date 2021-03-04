package com.infognc.Administrator.Modules.Account;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = "idxAgent")
@Entity @Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class AccountEntity {

    @Id
    @GeneratedValue @Column
    private int idxAgent;

    @NotNull
    private String agentNum;

    @NotNull
    private String agentId;

    @NotNull
    private String part;

    @NotNull
    private String level;

    @NotNull
    private String status;

    @NotNull
    private String password;

    @NotNull
    private String agentName;

    @NotNull
    private String regDate;

    private LocalDateTime lastDate;

    private LocalDateTime pwDate;

    @NotNull
    private String delFlag;

}
